package com.enrech.tarisearch.common_data.provider

import com.enrech.tarisearch.common_domain.provider.UrlProvider

import javax.inject.Inject

class UrlProviderImpl @Inject constructor(): UrlProvider {

    private companion object {
        const val apiUrl = "https://musicbrainz.org/ws/2/"
    }

    override fun getApiUrl(): String = apiUrl

}