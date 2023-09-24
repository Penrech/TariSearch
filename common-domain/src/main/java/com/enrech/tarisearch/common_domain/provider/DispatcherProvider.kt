package com.enrech.tarisearch.common_domain.provider

import kotlinx.coroutines.CoroutineDispatcher

interface DispatcherProvider {
    fun ui(): CoroutineDispatcher
    fun io(): CoroutineDispatcher
    fun computation(): CoroutineDispatcher
}
