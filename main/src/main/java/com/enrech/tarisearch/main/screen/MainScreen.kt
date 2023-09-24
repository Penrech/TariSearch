package com.enrech.tarisearch.main.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.enrech.tarisearch.common.components.input.SearchBox

@Composable
fun MainScreen(viewModel: MainViewModel = hiltViewModel()) {
    val state by viewModel.screenState.collectAsState()

    UIContent(state, viewModel::setAction)
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
private fun UIContent(state: MainScreenState, action: (MainAction) -> Unit) {
    Scaffold(
        topBar = {
            SearchBox(
                modifier = Modifier.padding(16.dp),
                isRequestLoading = state.isRequestLoading,
                onSearch = { action(MainAction.SearchByQuery(it)) })
        }
    ) {
        Column(Modifier.fillMaxSize().background(Color.Red).padding(it)) {

        }
    }
}

@Preview(showBackground = true, name = "Main Template")
@Composable
private fun MainPreview() {
    UIContent(MainScreenState()) {}
}