package com.enrech.tarisearch.common.extension

import android.content.Context
import com.enrech.tarisearch.common.navigation.IntentNavigator

val Context.intentNavigator get() = IntentNavigator(this)
