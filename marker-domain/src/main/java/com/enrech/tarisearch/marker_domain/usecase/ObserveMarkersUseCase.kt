package com.enrech.tarisearch.marker_domain.usecase

import com.enrech.tarisearch.common_domain.repository.MarkersRepository
import javax.inject.Inject

class ObserveMarkersUseCase @Inject constructor(
    private val markersRepository: MarkersRepository
) {
    operator fun invoke() = markersRepository.observeAllMarkers()
}