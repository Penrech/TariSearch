package com.enrech.tarisearch.common_data.provider

import io.ktor.client.HttpClient
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.cache.HttpCache
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.logging.SIMPLE
import io.ktor.client.request.header
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

data object HttpClientProvider {
    fun getClient() = HttpClient {
        followRedirects = false
        this.expectSuccess = true

        install(HttpTimeout) {
            requestTimeoutMillis = 15 * 1000L
        }

        install(ContentNegotiation) {
            json(Json {
                isLenient = true
                ignoreUnknownKeys = true
                useAlternativeNames = true
            })
        }

        install(HttpCache)

        val isInDevelopmentMode = developmentMode

        install(Logging) {
            logger = Logger.SIMPLE
            level = if (isInDevelopmentMode) LogLevel.INFO else LogLevel.NONE
        }

        defaultRequest {
            header(HttpHeaders.ContentType, ContentType.Application.Json.toString())
        }
    }
}
