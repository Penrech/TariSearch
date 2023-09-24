package com.enrech.tarisearch.common_domain.repository

import com.enrech.tarisearch.common_domain.entity.Marker
import kotlinx.coroutines.flow.Flow

interface MarkersRepository {
    suspend fun upsertMarker(vararg marker: Marker): List<Marker>
    suspend fun deleteMarker(vararg marker: Marker)
    suspend fun getAllMarkers(): List<Marker>
    fun observeAllMarkers(): Flow<List<Marker>>
    suspend fun checkExpirationAndReturn(elapsedTime: Long): List<Marker>
}