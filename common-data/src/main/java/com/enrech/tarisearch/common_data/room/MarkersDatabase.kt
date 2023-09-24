package com.enrech.tarisearch.common_data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.enrech.tarisearch.common_data.room.dao.MarkersDao
import com.enrech.tarisearch.common_data.room.entity.MarkerRoomEntity
import com.enrech.tarisearch.common_data.room.utils.DateConverter

@Database(entities = [MarkerRoomEntity::class], version = 1, exportSchema = false)
@TypeConverters(DateConverter::class)
abstract class MarkersDatabase: RoomDatabase() {
    abstract fun markerDao(): MarkersDao
}