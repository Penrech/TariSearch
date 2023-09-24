package com.enrech.tarisearch.api_domain.usecase

import com.enrech.tarisearch.api_domain.repository.PlaceQueryRepository
import com.enrech.tarisearch.common_domain.map
import com.enrech.tarisearch.common_domain.repository.MarkersRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SearchMarkerByQueryUseCase @Inject constructor(
    private val placeQueryRepository: PlaceQueryRepository,
    private val markersRepository: MarkersRepository
) {
    suspend operator fun invoke(query: String) = placeQueryRepository.getMarkersByQuery(query).map {
        markersRepository.upsertMarker(*it.toTypedArray())
    }
}