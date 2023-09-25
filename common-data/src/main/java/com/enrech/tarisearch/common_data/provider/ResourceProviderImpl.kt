package com.enrech.tarisearch.common_data.provider

import android.content.Context
import com.enrech.tarisearch.common_domain.provider.ResourceProvider
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class ResourceProviderImpl @Inject constructor(
    @ApplicationContext private val context: Context
) : ResourceProvider {
    override fun getString(id: Int, vararg args: Any): String = context.getString(id, args)
}