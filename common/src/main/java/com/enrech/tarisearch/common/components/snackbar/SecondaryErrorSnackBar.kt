package com.enrech.tarisearch.common.components.snackbar

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.res.stringResource

data class SecondaryErrorSnackBar(override var message: String): SnackBar

@Composable
fun rememberSecondaryErrorSnackBar(
    message: String = stringResource(id = com.enrech.ulessontest.common_resources.R.string.secondary_error)
): SecondaryErrorSnackBar =
    remember {
        SecondaryErrorSnackBar(message)
    }