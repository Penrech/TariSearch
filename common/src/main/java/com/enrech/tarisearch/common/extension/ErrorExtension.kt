package com.enrech.tarisearch.common.extension

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.enrech.tarisearch.common_domain.TariSearchError

@Composable
fun TariSearchError.getErrorMessage() = stringResource(id = getErrorMessageId())

fun TariSearchError.getErrorMessageId() =
    when(this) {
        TariSearchError.Offline,
        TariSearchError.Timeout -> com.enrech.ulessontest.common_resources.R.string.secondary_network_error
        TariSearchError.Unknown -> com.enrech.ulessontest.common_resources.R.string.secondary_error
    }