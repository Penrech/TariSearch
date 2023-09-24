package com.enrech.tarisearch.api_domain.repository

import com.enrech.tarisearch.common_domain.TariSearchResult
import com.enrech.tarisearch.common_domain.entity.Marker

interface PlaceQueryRepository {
    suspend fun getMarkersByQuery(query: String): TariSearchResult<List<Marker>>
}