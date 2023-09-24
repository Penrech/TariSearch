package com.enrech.tarisearch.common.utils

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.enrech.tarisearch.common.viewmodel.Effect
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow

@Composable
fun <T : Effect> HandleEffects(
    effectFlow: Flow<T>,
    key: Any? = Unit,
    content: CoroutineScope.(effect: T) -> Unit
) {
    LaunchedEffect(key1 = key) {
        effectFlow.collect { this.content(it) }
    }
}