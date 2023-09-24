package com.enrech.tarisearch.common_data.repository

import androidx.room.withTransaction
import com.enrech.tarisearch.common_data.mapper.MarkerRoomToMarkerMapper
import com.enrech.tarisearch.common_data.room.MarkersDatabase
import com.enrech.tarisearch.common_domain.entity.Marker
import com.enrech.tarisearch.common_domain.provider.DispatcherProvider
import com.enrech.tarisearch.common_domain.repository.MarkersRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MarkersRepositoryImpl @Inject constructor(
    private val db: MarkersDatabase,
    private val dispatcherProvider: DispatcherProvider,
    private val mapper: MarkerRoomToMarkerMapper
) : MarkersRepository {
    override suspend fun upsertMarker(vararg marker: Marker): List<Marker> =
        withContext(dispatcherProvider.io()) {
            db.withTransaction {
                db.markerDao().addUpdateMarker(*mapper.mapToList(marker.toList()).toTypedArray())
                getAllMarkers()
            }
        }

    override suspend fun deleteMarker(vararg marker: Marker) = withContext(dispatcherProvider.io()) {
        db.markerDao().deleteMarker(*mapper.mapToList(marker.toList()).toTypedArray())
    }

    override suspend fun getAllMarkers(): List<Marker> = withContext(dispatcherProvider.io()) {
        mapper.mapFromList(db.markerDao().getAllMarkers())
    }

    override fun observeAllMarkers(): Flow<List<Marker>> =
        db.markerDao().observeAllMarkers()
            .map { mapper.mapFromList(it) }
            .flowOn(dispatcherProvider.io())

    override suspend fun checkExpirationAndReturn(elapsedTime: Long): List<Marker> = withContext(dispatcherProvider.io()) {
        db.withTransaction {
            val markers = db.markerDao().getAllMarkers()
            val markersToRemove = markers.filter { (it.lifeSpan - elapsedTime) <= 0 }
            db.markerDao().deleteMarker(*markersToRemove.toTypedArray())
            getAllMarkers()
        }
    }
}