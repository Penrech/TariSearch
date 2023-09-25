package com.enrech.tarisearch.common_data.repository

import androidx.room.withTransaction
import com.enrech.tarisearch.common_data.mapper.MarkerRoomToMarkerMapper
import com.enrech.tarisearch.common_data.room.MarkersDatabase
import com.enrech.tarisearch.common_data.room.entity.MarkerRoomEntity
import com.enrech.tarisearch.common_domain.entity.Marker
import com.enrech.tarisearch.common_domain.provider.DispatcherProvider
import com.enrech.tarisearch.common_domain.repository.MarkersRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class MarkersRepositoryImpl @Inject constructor(
    private val db: MarkersDatabase,
    private val dispatcherProvider: DispatcherProvider,
    private val mapper: MarkerRoomToMarkerMapper
) : MarkersRepository {
    override suspend fun upsertMarker(vararg marker: Marker): List<Marker> =
        db.withTransaction {
            db.markerDao().addUpdateMarker(*mapper.mapToList(marker.toList()).toTypedArray())
            getAllMarkers()
        }

    override suspend fun deleteMarker(vararg marker: Marker) =
        db.markerDao().deleteMarker(*mapper.mapToList(marker.toList()).toTypedArray())

    override suspend fun getAllMarkers(): List<Marker> =
        mapper.mapFromList(db.markerDao().getAllMarkers())

    override fun observeAllMarkers(): Flow<List<Marker>> =
        db.markerDao().observeAllMarkers()
            .map { mapper.mapFromList(it) }
            .flowOn(dispatcherProvider.io())
            .distinctUntilChanged()

    override suspend fun checkExpiration(timeToDiscount: Int) {
        db.withTransaction {
            val markers = db.markerDao().getAllMarkers()
            val updatedMarkers =
                markers.map { it.copy(lifeSpan = maxOf(0, it.lifeSpan - timeToDiscount)) }

            val (markersToRemove, markersToUpdate) =
                updatedMarkers
                    .fold(
                        Pair(
                            mutableListOf<MarkerRoomEntity>(),
                            mutableListOf<MarkerRoomEntity>()
                        )
                    ) { acc, marker ->
                        acc.apply {
                            val lifeSpan = marker.lifeSpan.toInt()
                            if (lifeSpan == 0) acc.first.add(marker)
                            else acc.second.add(marker)
                        }
                    }
            db.markerDao().deleteMarker(*markersToRemove.toTypedArray())
            db.markerDao().addUpdateMarker(*markersToUpdate.toTypedArray())
        }
    }

}