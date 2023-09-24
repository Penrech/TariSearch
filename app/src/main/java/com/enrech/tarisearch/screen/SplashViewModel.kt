package com.enrech.tarisearch.screen

import androidx.lifecycle.viewModelScope
import com.enrech.tarisearch.common.viewmodel.Action
import com.enrech.tarisearch.common.viewmodel.BaseViewModel
import com.enrech.tarisearch.common.viewmodel.Effect
import com.enrech.tarisearch.common.viewmodel.ScreenState
import com.enrech.tarisearch.common_domain.provider.DispatcherProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    dispatcherProvider: DispatcherProvider
) : BaseViewModel<SplashScreenState, SplashAction, SplashEffect>() {

    override fun createInitialScreenState(): SplashScreenState = SplashScreenState

    init {
        flow { emit(delay(400)) }
            .flowOn(dispatcherProvider.io())
            .onEach { setEffect { SplashEffect.LaunchMain } }
            .launchIn(viewModelScope)
    }

    override suspend fun handleActions(action: SplashAction) {}
}

object SplashScreenState : ScreenState
sealed class SplashAction : Action
sealed class SplashEffect : Effect {
    data object LaunchMain: SplashEffect()
}