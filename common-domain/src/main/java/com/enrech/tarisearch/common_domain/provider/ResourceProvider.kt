package com.enrech.tarisearch.common_domain.provider

import androidx.annotation.StringRes

interface ResourceProvider {
    fun getString(@StringRes id: Int, vararg args: Any): String
}