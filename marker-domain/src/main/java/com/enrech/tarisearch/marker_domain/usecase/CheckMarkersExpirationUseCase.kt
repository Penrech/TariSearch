package com.enrech.tarisearch.marker_domain.usecase

import com.enrech.tarisearch.common_domain.repository.MarkersRepository
import com.enrech.tarisearch.common_domain.utils.Looper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CheckMarkersExpirationUseCase @Inject constructor(
    private val markersRepository: MarkersRepository,
    private val looper: Looper
) {

    operator fun invoke(): Flow<Unit> =
        looper.count(REPEAT_INTERVAL).map {
            markersRepository.checkExpiration(it)
        }

    companion object {
        const val REPEAT_INTERVAL = 1000L
    }

}