package com.enrech.tarisearch.common_data.provider

import com.enrech.tarisearch.common_domain.provider.DispatcherProvider
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class DispatcherProviderImpl @Inject constructor() : DispatcherProvider {
    override fun ui(): CoroutineDispatcher = Dispatchers.Main
    override fun io(): CoroutineDispatcher = Dispatchers.IO
    override fun computation(): CoroutineDispatcher = Dispatchers.Default
}
