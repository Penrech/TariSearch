package com.enrech.tarisearch.api_data.repository

import com.enrech.tarisearch.api_data.mapper.MarkerEntityMapper
import com.enrech.tarisearch.api_data.service.PlacesServices
import com.enrech.tarisearch.api_domain.repository.PlaceQueryRepository
import com.enrech.tarisearch.common_data.extension.safeCall
import com.enrech.tarisearch.common_domain.TariSearchResult
import com.enrech.tarisearch.common_domain.entity.Marker
import javax.inject.Inject

class PlaceQueryRepositoryImpl @Inject constructor(
    private val placesServices: PlacesServices,
    private val mapper: MarkerEntityMapper
): PlaceQueryRepository {
    override suspend fun getMarkersByQuery(query: String): TariSearchResult<List<Marker>> = safeCall {
        mapper.mapFromList(placesServices.getPlacesByQuery(query).places).filterNotNull()
    }
}