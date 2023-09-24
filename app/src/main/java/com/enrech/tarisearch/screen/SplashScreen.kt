package com.enrech.tarisearch.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.enrech.tarisearch.R
import com.enrech.tarisearch.common.composition.LocalIntentNavigator
import com.enrech.tarisearch.common.utils.HandleEffects

@Composable
fun SplashScreen(viewModel: SplashViewModel = hiltViewModel()) {
    val intentNavigator = LocalIntentNavigator.current

    HandleEffects(viewModel.effect) {
        when(it) {
            SplashEffect.LaunchMain -> intentNavigator.openMain()
        }
    }

    UIContent()
}

@Composable
private fun UIContent() {
    Column(
        Modifier
            .fillMaxSize()
            .background(Color.LightGray)
            .padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        val annotatedString = buildAnnotatedString {
            withStyle(style = SpanStyle(fontSize = MaterialTheme.typography.displayMedium.fontSize)) {
                append(stringResource(id = R.string.splash_title))
            }
            append("\n")
            withStyle(style = SpanStyle(fontSize = MaterialTheme.typography.displaySmall.fontSize)) {
                append(stringResource(id = R.string.splash_subtitle))
            }
        }

        Text(text = annotatedString)
    }
}

@Preview(showBackground = true, name = "Splash Template")
@Composable
private fun SplashPreview() {
    UIContent()
}