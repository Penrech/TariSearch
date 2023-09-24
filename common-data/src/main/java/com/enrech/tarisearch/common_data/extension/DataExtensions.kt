package com.enrech.tarisearch.common_data.extension

import com.enrech.tarisearch.common_domain.TariSearchError
import com.enrech.tarisearch.common_domain.TariSearchResult
import io.ktor.client.network.sockets.SocketTimeoutException
import timber.log.Timber
import java.net.UnknownHostException

suspend fun <T> safeCall(call: suspend () -> T): TariSearchResult<T> {
    return try {
        val result = call()
        TariSearchResult.Success(result)
    } catch (throwable: Throwable) {
        Timber.e(throwable, "http client error")
        if (throwable is UnknownHostException) {
            TariSearchResult.Failure(TariSearchError.Offline)
        } else {
            when (throwable) {
                is SocketTimeoutException -> TariSearchResult.Failure(TariSearchError.Timeout)
                else -> TariSearchResult.Failure(TariSearchError.Unknown)
            }
        }
    }
}