package com.enrech.tarisearch.common_data.room.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.enrech.tarisearch.common_data.room.Constant
import com.enrech.tarisearch.common_data.utils.LocalDateTimeParceler
import com.enrech.tarisearch.common_domain.entity.MarkerType
import kotlinx.datetime.LocalDateTime
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.TypeParceler

@Entity(tableName = Constant.markersTable)
@Parcelize
@TypeParceler<LocalDateTime, LocalDateTimeParceler>
data class MarkerRoomEntity(
    @PrimaryKey val id: String,
    val type: MarkerType,
    val name: String,
    val address: String?,
    val lat: Float,
    val lon: Float,
    val beginDate: LocalDateTime,
    val lifeSpan: Long
): Parcelable
