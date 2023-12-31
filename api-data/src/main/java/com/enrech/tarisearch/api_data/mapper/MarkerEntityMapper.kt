package com.enrech.tarisearch.api_data.mapper

import com.enrech.tarisearch.api_data.response.PlaceResponseDto
import com.enrech.tarisearch.common_domain.BaseMapper
import com.enrech.tarisearch.common_domain.entity.Marker
import com.enrech.tarisearch.common_domain.entity.MarkerCoordinates
import com.enrech.tarisearch.common_domain.entity.MarkerType
import com.enrech.tarisearch.common_domain.extension.ifLet
import javax.inject.Inject
import kotlin.math.abs

class MarkerEntityMapper @Inject constructor(
    private val dateMapper: DateMapper
): BaseMapper<PlaceResponseDto?, Marker?>() {
    override fun mapFrom(from: PlaceResponseDto?): Marker? {
        val coordinate =
            ifLet(from?.coordinates?.lat?.toFloatOrNull(), from?.coordinates?.lon?.toFloatOrNull()) { lat, lon ->
            MarkerCoordinates(lat, lon)
        }

        return ifLet(from?.id, coordinate) { id, coordinates ->

            val startDate = dateMapper.mapFrom(from?.lifeSpan?.begin)
            val lifeSpan = abs(startDate.year.minus(CONTROL_YEAR)).toLong()

            Marker(
                id = id,
                name = from?.name.orEmpty(),
                type = from?.type?.let { MarkerType.getByName(it) } ?: MarkerType.Other,
                address = from?.address,
                coordinates = coordinates,
                startDate = startDate,
                lifeSpan = lifeSpan,
                initialLifeSpan = lifeSpan
            )
        }
    }

    private companion object {
        const val CONTROL_YEAR = 1990
    }
}