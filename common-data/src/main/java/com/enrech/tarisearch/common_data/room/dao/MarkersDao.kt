package com.enrech.tarisearch.common_data.room.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.enrech.tarisearch.common_data.room.Constant
import com.enrech.tarisearch.common_data.room.entity.MarkerRoomEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MarkersDao {
    @Upsert
    fun addUpdateMarker(vararg marker: MarkerRoomEntity)

    @Query("SELECT * FROM ${Constant.markersTable}")
    fun getAllMarkers(): List<MarkerRoomEntity>

    @Query("SELECT * FROM ${Constant.markersTable}")
    fun observeAllMarkers(): Flow<List<MarkerRoomEntity>>

    @Delete
    fun deleteMarker(vararg marker: MarkerRoomEntity)
}