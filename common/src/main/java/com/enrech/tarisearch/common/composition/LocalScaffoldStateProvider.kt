package com.enrech.tarisearch.common.composition

import androidx.compose.runtime.compositionLocalOf
import androidx.compose.material.ScaffoldState

val LocalScaffoldStateProvider = compositionLocalOf<ScaffoldState> {
    error("No LocalScaffoldStateProvider provided")
}