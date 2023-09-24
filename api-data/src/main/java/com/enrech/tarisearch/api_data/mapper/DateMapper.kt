package com.enrech.tarisearch.api_data.mapper

import com.enrech.tarisearch.common_domain.BaseMapper
import com.enrech.tarisearch.common_domain.extension.now
import kotlinx.datetime.LocalDateTime
import timber.log.Timber
import javax.inject.Inject

class DateMapper @Inject constructor(): BaseMapper<String?, LocalDateTime>() {
    override fun mapFrom(from: String?): LocalDateTime = try {
        from?.let {
            when(it.contains(DATE_SEPARATOR)) {
                true -> {
                    val dateList = it.split(DATE_SEPARATOR)
                    LocalDateTime(dateList[0].toInt(), dateList[1].toInt(), dateList[2].toInt(), 0, 0)
                }
                false -> {
                    LocalDateTime(year = it.toInt(), 0, 0, 0, 0)
                }
            }
        } ?: LocalDateTime.now()
    } catch (e: Exception) {
        Timber.e(e)
        LocalDateTime.now()
    }

    private companion object {
        const val DATE_SEPARATOR = "-"
    }
}