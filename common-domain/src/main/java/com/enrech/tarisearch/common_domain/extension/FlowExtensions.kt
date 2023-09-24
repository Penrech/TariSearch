package com.enrech.tarisearch.common_domain.extension

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow

inline fun <T> repeatFlow(interval: Long, initialDelay: Long = 0, crossinline task: suspend () -> T) = flow {
    delay(initialDelay)
    while (true) {
        emit(task.invoke())
        delay(interval)
    }
}
