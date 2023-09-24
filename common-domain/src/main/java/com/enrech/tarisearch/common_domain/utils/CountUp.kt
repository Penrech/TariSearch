package com.enrech.tarisearch.common_domain.utils

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CountUp @Inject constructor() {
    private val currentValue: MutableStateFlow<Long> = MutableStateFlow(0)

    fun count(interval: Long, step: Int = 1): Flow<Long> =
        flow {
            emit(currentValue.value)
            delay(interval)
            while (true) {
                currentValue.value += step
                emit(currentValue.value)
                delay(interval)
            }
        }
}