package com.enrech.tarisearch.common.utils

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver

@Composable
fun HandleLifeCycle(
    key: Any = Unit,
    onCreate: () -> Unit = {},
    onStart: () -> Unit = {},
    onResume: () -> Unit = {},
    onPause: () -> Unit = {},
    onStop: () -> Unit = {},
    onAny: (state: Lifecycle.State) -> Unit = {},
    onDestroy: () -> Unit = {},
    onDispose: () -> Unit = {}
) {
    val lifecycleOwner by rememberUpdatedState(LocalLifecycleOwner.current)

    DisposableEffect(key) {
        val lifecycleObserver = LifecycleEventObserver { _, event ->
            when (event) {
                Lifecycle.Event.ON_CREATE -> onCreate()
                Lifecycle.Event.ON_START -> onStart()
                Lifecycle.Event.ON_RESUME -> onResume()
                Lifecycle.Event.ON_PAUSE -> onPause()
                Lifecycle.Event.ON_STOP -> onStop()
                Lifecycle.Event.ON_DESTROY -> onDestroy()
                Lifecycle.Event.ON_ANY -> onAny(lifecycleOwner.lifecycle.currentState)
            }
        }
        lifecycleOwner.lifecycle.addObserver(lifecycleObserver)
        onDispose {
            lifecycleOwner.lifecycle.removeObserver(lifecycleObserver)
            onDispose()
        }
    }
}