package com.enrech.tarisearch.api_data.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LifeSpanDto(
    @SerialName("begin") val begin: String? = null
)
