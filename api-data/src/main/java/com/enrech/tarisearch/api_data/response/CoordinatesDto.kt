package com.enrech.tarisearch.api_data.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CoordinatesDto(
    @SerialName("latitude") val lat: String? = null,
    @SerialName("longitude") val lon: String? = null
)
