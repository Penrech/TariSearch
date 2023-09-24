package com.enrech.tarisearch.common_data.repository

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
    override suspend fun upsertMarker(vararg marker: Marker) =
        withContext(dispatcherProvider.io()) {
            db.markerDao().addUpdateMarker(*mapper.mapToList(marker.toList()).toTypedArray())
        }

    override suspend fun getAllMarkers(): List<Marker> = withContext(dispatcherProvider.io()) {
        mapper.mapFromList(db.markerDao().getAllMarkers())
    }

    override fun observeAllMarkers(): Flow<List<Marker>> =
        db.markerDao().observeAllMarkers()
            .map { mapper.mapFromList(it) }
            .flowOn(dispatcherProvider.io())
}