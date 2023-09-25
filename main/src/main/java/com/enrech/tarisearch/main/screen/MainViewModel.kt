package com.enrech.tarisearch.main.screen

import androidx.lifecycle.viewModelScope
import com.enrech.tarisearch.api_domain.usecase.SearchMarkerByQueryUseCase
import com.enrech.tarisearch.common.extension.getErrorMessageId
import com.enrech.tarisearch.common.viewmodel.Action
import com.enrech.tarisearch.common.viewmodel.BaseViewModel
import com.enrech.tarisearch.common.viewmodel.ClickAction
import com.enrech.tarisearch.common.viewmodel.Effect
import com.enrech.tarisearch.common.viewmodel.ScreenState
import com.enrech.tarisearch.common_domain.TariSearchResult
import com.enrech.tarisearch.common_domain.extension.now
import com.enrech.tarisearch.common_domain.provider.DispatcherProvider
import com.enrech.tarisearch.common_domain.provider.ResourceProvider
import com.enrech.tarisearch.marker.entity.MarkerUI
import com.enrech.tarisearch.marker_domain.usecase.CheckMarkersExpirationUseCase
import com.enrech.tarisearch.marker_domain.usecase.ObserveMarkersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.datetime.LocalDateTime
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val dispatcherProvider: DispatcherProvider,
    checkMarkersExpirationUseCase: CheckMarkersExpirationUseCase,
    private val searchMarkerByQueryUseCase: SearchMarkerByQueryUseCase,
    private val observeMarkersUseCase: ObserveMarkersUseCase,
    private val resourceProvider: ResourceProvider
) : BaseViewModel<MainScreenState, MainAction, MainEffect>() {

    override fun createInitialScreenState(): MainScreenState = MainScreenState()

    init {
        checkMarkersExpirationUseCase.invoke()
            .flowOn(dispatcherProvider.io())
            .launchIn(viewModelScope)

        observeMarkersUseCase.invoke()
            .flowOn(dispatcherProvider.io())
            .onEach {
                if (currentScreenState.isRequestLoading.not()) {
                    setScreenState {
                        currentScreenState.copy(
                            markers = MarkerUI.fromDomainList(it)
                        )
                    }
                }
            }
            .launchIn(viewModelScope)
    }

    override suspend fun handleActions(action: MainAction) {
        when(action) {
            is MainAction.SearchByQuery -> {
                setEffect { MainEffect.CloseKeyBoard }
                flow { emit(searchMarkerByQueryUseCase(action.query)) }
                    .onStart { setScreenState { currentScreenState.copy(isRequestLoading = true) } }
                    .map{
                        var state = currentScreenState
                        when(it) {
                            is TariSearchResult.Failure -> {
                                setEffect { MainEffect.DisplaySecondaryError(resourceProvider.getString(it.error.getErrorMessageId())) }
                            }
                            is TariSearchResult.Success -> {
                                val markersUI = MarkerUI.fromDomainList(it.data)
                                state = state.copy(
                                    markers = markersUI,
                                    timestamp = LocalDateTime.now()
                                )
                            }
                        }
                        state.copy(isRequestLoading = false)
                    }
                    .flowOn(dispatcherProvider.io())
                    .onEach { setScreenState { it } }
                    .launchIn(viewModelScope)
            }
        }
    }
}

data class MainScreenState(
    val isRequestLoading: Boolean = false,
    val markers: List<MarkerUI> = emptyList(),
    val timestamp: LocalDateTime = LocalDateTime.now()
) : ScreenState
sealed class MainAction : Action {
    data class SearchByQuery(val query: String): MainAction(), ClickAction
}
sealed class MainEffect : Effect {
    data class DisplaySecondaryError(val error: String): MainEffect()
    data object CloseKeyBoard: MainEffect()
}