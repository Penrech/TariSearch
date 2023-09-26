package com.enrech.tarisearch.marker.entity

import com.enrech.tarisearch.common_domain.entity.Marker
import com.enrech.tarisearch.common_domain.entity.MarkerType
import com.enrech.tarisearch.marker.R
import com.google.android.gms.maps.model.LatLng

data class MarkerUI(
    private val marker: Marker
) {
    val id = marker.id
    val address = marker.address
    val type = marker.type.name
    val name = marker.name
    val coordinates = LatLng(marker.coordinates.lat.toDouble(), marker.coordinates.lon.toDouble())
    val remaining: Float = 1f.minus((marker.initialLifeSpan - marker.lifeSpan).div(marker.initialLifeSpan.toFloat()))
    val iconRes = when(marker.type) {
        MarkerType.Studio -> R.drawable.ic_studio
        MarkerType.Venue -> R.drawable.ic_venue
        MarkerType.Stadium -> R.drawable.ic_stadium
        MarkerType.IndoorArena -> R.drawable.ic_indoor
        MarkerType.ReligiousBuilding -> R.drawable.ic_church
        MarkerType.EducationalInstitution -> R.drawable.ic_educational
        MarkerType.PressingPlant -> R.drawable.ic_press
        MarkerType.Other -> R.drawable.ic_other
    }

    companion object {
        fun fromDomain(marker: Marker) = MarkerUI(marker)

        fun fromDomainList(markers: List<Marker>) = markers.map(::fromDomain)
    }
}
