package com.enrech.tarisearch.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.CompositionLocalProvider
import com.enrech.tarisearch.common.composition.LocalIntentNavigator
import com.enrech.tarisearch.common.navigation.IntentNavigator
import com.enrech.tarisearch.common_resources.theme.TariSearchTheme
import com.enrech.tarisearch.main.screen.MainScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            TariSearchTheme {
                CompositionLocalProvider(
                    LocalIntentNavigator provides IntentNavigator(this)
                ) {
                    MainScreen()
                }
            }
        }
    }
}