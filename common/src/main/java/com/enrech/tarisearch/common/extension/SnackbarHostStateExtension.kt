package com.enrech.tarisearch.common.extension

import com.enrech.tarisearch.common.components.snackbar.SnackBar

suspend fun androidx.compose.material.SnackbarHostState.showSnackBar(data: SnackBar) {
    this.showSnackbar(data.message, data.actionLabel, data.duration)
}