package com.enrech.tarisearch.main.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.enrech.tarisearch.common.components.input.SearchBox
import com.google.maps.android.compose.CameraPositionState
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MarkerInfoWindow
import com.google.maps.android.compose.rememberCameraPositionState

@Composable
fun MainScreen(viewModel: MainViewModel = hiltViewModel()) {
    val state by viewModel.screenState.collectAsState()
    val cameraPositionState = rememberCameraPositionState(state.markers.hashCode().toString()) {
    }

    UIContent(state, cameraPositionState, viewModel::setAction)
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
private fun UIContent(state: MainScreenState, cameraState: CameraPositionState, action: (MainAction) -> Unit) {
    Scaffold(
        topBar = {
            SearchBox(
                modifier = Modifier.padding(16.dp),
                isRequestLoading = state.isRequestLoading,
                onSearch = { action(MainAction.SearchByQuery(it)) })
        }
    ) {
       GoogleMap(
           modifier = Modifier.fillMaxSize(),
           cameraPositionState = cameraState
       ) {
            state.markers.forEach {
                MarkerInfoWindow(

                ) {

                }
            }
       }
    }
}

@Preview(showBackground = true, name = "Main Template")
@Composable
private fun MainPreview() {
    val cameraState = rememberCameraPositionState()
    UIContent(MainScreenState(), cameraState) {}
}