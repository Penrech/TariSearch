package com.enrech.tarisearch.common.composition

import androidx.compose.runtime.compositionLocalOf
import com.enrech.tarisearch.common.navigation.IntentNavigator

val LocalIntentNavigator = compositionLocalOf<IntentNavigator> {
    error("No LocalIntentNavigator provided")
}
