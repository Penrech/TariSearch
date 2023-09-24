package com.enrech.tarisearch.common.components.snackbar

import androidx.compose.material.SnackbarDuration

interface SnackBar {
    var message: String
    val actionLabel: String? get() =  null
    val duration: SnackbarDuration get() = SnackbarDuration.Short
}