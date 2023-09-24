package com.enrech.tarisearch

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.core.view.WindowCompat
import com.enrech.tarisearch.common.composition.LocalIntentNavigator
import com.enrech.tarisearch.common.navigation.IntentNavigator
import com.enrech.tarisearch.common_resources.theme.TariSearchTheme
import dagger.hilt.android.AndroidEntryPoint

@SuppressLint("CustomSplashScreen")
@AndroidEntryPoint
class SplashActivity: ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            TariSearchTheme {
                CompositionLocalProvider(
                    LocalIntentNavigator provides IntentNavigator(this)
                ) {
                    Box(modifier = Modifier.windowInsetsPadding(WindowInsets.navigationBars)) {

                    }
                }
            }
        }
    }
}