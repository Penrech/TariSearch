package com.enrech.tarisearch.common_data.mapper

import com.enrech.tarisearch.common_data.room.entity.MarkerRoomEntity
import com.enrech.tarisearch.common_domain.BaseReversibleMapper
import com.enrech.tarisearch.common_domain.entity.Marker
import com.enrech.tarisearch.common_domain.entity.MarkerCoordinates
import javax.inject.Inject

class MarkerRoomToMarkerMapper @Inject constructor(): BaseReversibleMapper<MarkerRoomEntity, Marker>() {
    override fun mapFrom(from: MarkerRoomEntity): Marker =
        Marker(
            id = from.id,
            type = from.type,
            name = from.name,
            address = from.address,
            coordinates = MarkerCoordinates(from.lat, from.lon),
            startDate = from.beginDate,
            lifeSpan = from.lifeSpan
        )

    override fun mapTo(to: Marker): MarkerRoomEntity =
        MarkerRoomEntity(
            id = to.id,
            type = to.type,
            name = to.name,
            address = to.address,
            lat = to.coordinates.lat,
            lon = to.coordinates.lon,
            beginDate = to.startDate,
            lifeSpan = to.lifeSpan
        )
}