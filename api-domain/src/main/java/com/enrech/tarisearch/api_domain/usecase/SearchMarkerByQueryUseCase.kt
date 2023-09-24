package com.enrech.tarisearch.api_domain.usecase

import com.enrech.tarisearch.api_domain.repository.PlaceQueryRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SearchMarkerByQueryUseCase @Inject constructor(
    private val placeQueryRepository: PlaceQueryRepository
) {
    suspend operator fun invoke(query: String) = placeQueryRepository.getMarkersByQuery(query)
}