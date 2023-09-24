package com.enrech.tarisearch.common_data.utils

import android.os.Parcel
import com.enrech.tarisearch.common_domain.extension.now
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.toLocalDateTime
import kotlinx.parcelize.Parceler

data object LocalDateTimeParceler: Parceler<LocalDateTime> {
    override fun create(parcel: Parcel): LocalDateTime {
        val date = parcel.readString()
        return date?.toLocalDateTime() ?: LocalDateTime.now()
    }

    override fun LocalDateTime.write(parcel: Parcel, flags: Int) {
        parcel.writeString(this.toString())
    }
}