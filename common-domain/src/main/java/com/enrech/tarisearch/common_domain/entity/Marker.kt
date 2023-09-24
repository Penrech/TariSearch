package com.enrech.tarisearch.common_domain.entity

import kotlinx.datetime.LocalDateTime

data class Marker(
    val id: String,
    val type: MarkerType,
    val name: String,
    val address: String?,
    val coordinates: MarkerCoordinates,
    val startDate: LocalDateTime,
    val lifeSpan: Long
)
