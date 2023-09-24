package com.enrech.tarisearch.api_data.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PlacesResponseDto(
    @SerialName("places") val places: List<PlaceResponseDto>? = null
)
