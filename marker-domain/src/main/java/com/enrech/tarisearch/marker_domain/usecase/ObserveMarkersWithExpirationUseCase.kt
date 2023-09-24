package com.enrech.tarisearch.marker_domain.usecase

import com.enrech.tarisearch.common_domain.entity.Marker
import com.enrech.tarisearch.common_domain.repository.MarkersRepository
import com.enrech.tarisearch.common_domain.utils.CountUp
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import javax.inject.Inject

class ObserveMarkersWithExpirationUseCase @Inject constructor(
    private val markersRepository: MarkersRepository,
    private val countUp: CountUp
) {

    operator fun invoke(): Flow<List<Marker>> =
        combine(
            markersRepository.observeAllMarkers(),
            countUp.count(REPEAT_INTERVAL)
        ) { _, elapsed ->
            markersRepository.checkExpirationAndReturn(elapsed)
        }

    companion object {
        const val REPEAT_INTERVAL = 1000L
    }

}