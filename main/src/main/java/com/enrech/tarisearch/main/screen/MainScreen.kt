package com.enrech.tarisearch.main.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun MainScreen(viewModel: MainViewModel = hiltViewModel()) {
    UIContent()
}

@Composable
private fun UIContent() {
    Column(Modifier.fillMaxSize()) {

    }
}

@Preview(showBackground = true, name = "Main Template")
@Composable
private fun MainPreview() {
    UIContent()
}