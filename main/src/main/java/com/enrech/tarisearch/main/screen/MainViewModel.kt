package com.enrech.tarisearch.main.screen

import androidx.lifecycle.viewModelScope
import com.enrech.tarisearch.api_domain.usecase.SearchMarkerByQueryUseCase
import com.enrech.tarisearch.common.viewmodel.Action
import com.enrech.tarisearch.common.viewmodel.BaseViewModel
import com.enrech.tarisearch.common.viewmodel.ClickAction
import com.enrech.tarisearch.common.viewmodel.Effect
import com.enrech.tarisearch.common.viewmodel.ScreenState
import com.enrech.tarisearch.common_domain.TariSearchError
import com.enrech.tarisearch.common_domain.TariSearchResult
import com.enrech.tarisearch.common_domain.entity.Marker
import com.enrech.tarisearch.common_domain.provider.DispatcherProvider
import com.enrech.tarisearch.marker_domain.usecase.ObserveMarkersWithExpirationUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val dispatcherProvider: DispatcherProvider,
    observeMarkersWithExpirationUseCase: ObserveMarkersWithExpirationUseCase,
    private val searchMarkerByQueryUseCase: SearchMarkerByQueryUseCase
) : BaseViewModel<MainScreenState, MainAction, MainEffect>() {

    override fun createInitialScreenState(): MainScreenState = MainScreenState()

    init {
        observeMarkersWithExpirationUseCase.invoke()
            .flowOn(dispatcherProvider.io())
            .onEach {
                setScreenState { currentScreenState.copy(markers = it) }
            }
            .launchIn(viewModelScope)
    }

    override suspend fun handleActions(action: MainAction) {
        when(action) {
            is MainAction.SearchByQuery -> {
                flow { emit(searchMarkerByQueryUseCase(action.query)) }
                    .onStart { setScreenState { currentScreenState.copy(isRequestLoading = true) } }
                    .map{
                        var state = currentScreenState
                        when(it) {
                            is TariSearchResult.Failure -> {
                                setEffect { MainEffect.DisplaySecondaryError(it.error) }
                            }
                            is TariSearchResult.Success -> state = state.copy(markers = it.data)
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
    val markers: List<Marker> = emptyList()) : ScreenState
sealed class MainAction : Action {
    data class SearchByQuery(val query: String): MainAction(), ClickAction
}
sealed class MainEffect : Effect {
    data class DisplaySecondaryError(val error: TariSearchError): MainEffect()
}