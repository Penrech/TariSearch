package com.enrech.tarisearch.common_domain.utils

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class Looper @Inject constructor() {
    fun count(interval: Long, step: Int = 1): Flow<Int> =
        flow {
            emit(step)
            delay(interval)
            while (true) {
                emit(step)
                delay(interval)
            }
        }
}