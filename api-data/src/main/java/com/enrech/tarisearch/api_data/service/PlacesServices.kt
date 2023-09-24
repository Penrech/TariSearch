package com.enrech.tarisearch.api_data.service

import com.enrech.tarisearch.api_data.response.PlacesResponseDto
import com.enrech.tarisearch.common_domain.provider.UrlProvider
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import javax.inject.Inject

class PlacesServices @Inject constructor(
    private val urlProvider: UrlProvider,
    private val httpClient: HttpClient
) {

    private val baseUrl by lazy { urlProvider.getApiUrl().plus(PLACES) }

    internal suspend fun getPlacesByQuery(query: String): PlacesResponseDto =
        httpClient.get(baseUrl){
            parameter(PARAM_QUERY, query)
            parameter(PARAM_FMT, "json")
            parameter(PARAM_LIMIT, 20)
        }.body()

    private companion object {
        const val PLACES = "places"

        const val PARAM_FMT = "fmt"
        const val PARAM_LIMIT = "limit"
        const val PARAM_QUERY = "query"
    }
}