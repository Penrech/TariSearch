package com.enrech.tarisearch.api_data.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PlaceResponseDto(
    @SerialName("id") val id: String? = null,
    @SerialName("type") val type: String? = null,
    @SerialName("name") val name: String? = null,
    @SerialName("address") val address: String? = null,
    @SerialName("coordinates") val coordinates: CoordinatesDto? = null,
    @SerialName("life-span") val lifeSpan: LifeSpanDto? = null
)
