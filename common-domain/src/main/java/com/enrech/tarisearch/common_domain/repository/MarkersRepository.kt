package com.enrech.tarisearch.common_domain.repository

import com.enrech.tarisearch.common_domain.entity.Marker
import kotlinx.coroutines.flow.Flow

interface MarkersRepository {
    suspend fun upsertMarker(vararg marker: Marker)
    suspend fun getAllMarkers(): List<Marker>
    fun observeAllMarkers(): Flow<List<Marker>>
}