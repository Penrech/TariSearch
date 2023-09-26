package com.enrech.tarisearch.main.screen

import android.annotation.SuppressLint
import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.enrech.tarisearch.common.components.input.SearchBox
import com.enrech.tarisearch.common.utils.HandleEffects
import com.enrech.tarisearch.common_resources.theme.accentRed
import com.enrech.tarisearch.marker.utils.bitmapDescriptorFromVector
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.LatLngBounds
import com.google.maps.android.compose.CameraPositionState
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MarkerInfoWindow
import com.google.maps.android.compose.rememberCameraPositionState
import com.google.maps.android.compose.rememberMarkerState
import kotlinx.coroutines.launch

@Composable
fun MainScreen(viewModel: MainViewModel = hiltViewModel()) {
    val state by viewModel.screenState.collectAsState()
    val cameraPositionState = rememberCameraPositionState()

    val context = LocalContext.current
    val snackbarHostState = remember { SnackbarHostState() }
    val keyboardController = LocalSoftwareKeyboardController.current
    val focus = LocalFocusManager.current

    LaunchedEffect(state.timestamp) {
        if (state.markers.isNotEmpty()) {
            val bounds = state.markers.fold(LatLngBounds.builder()) { builder, item ->
                builder.include(item.coordinates)
            }

            val cameraUpdate = CameraUpdateFactory.newLatLngBounds(bounds.build(), 10)
            cameraPositionState.animate(cameraUpdate)
        }
    }

    HandleEffects(viewModel.effect) {
        when (it) {
            is MainEffect.DisplaySecondaryError -> launch {
                snackbarHostState.showSnackbar(it.error)
            }

            MainEffect.CloseKeyBoard -> {
                keyboardController?.hide()
                focus.clearFocus(true)
            }
        }
    }

    UIContent(context, snackbarHostState, state, cameraPositionState, viewModel::setAction)
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
private fun UIContent(
    context: Context,
    snackbarHostState: SnackbarHostState,
    state: MainScreenState,
    cameraState: CameraPositionState,
    action: (MainAction) -> Unit
) {
    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
        topBar = {
            SearchBox(
                modifier = Modifier.padding(16.dp),
                isRequestLoading = state.isRequestLoading,
                onSearch = { action(MainAction.SearchByQuery(it)) })
        }
    ) {
        val density = LocalDensity.current

        GoogleMap(
            modifier = Modifier
                .fillMaxSize(),
            cameraPositionState = cameraState
        ) {
            state.markers.forEach { markerUI ->
                MarkerInfoWindow(
                    state = rememberMarkerState(
                        key = markerUI.id,
                        position = markerUI.coordinates
                    ),
                    alpha = markerUI.remaining,
                    icon = bitmapDescriptorFromVector(
                        context,
                        density = density,
                        size = DpSize(32.dp, 32.dp),
                        com.enrech.tarisearch.marker.R.drawable.ic_location
                    ),
                    title = markerUI.name
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center,
                        modifier = Modifier
                            .clip(RoundedCornerShape(10))
                            .background(accentRed)
                            .padding(16.dp)
                    ) {
                        Image(
                            modifier = Modifier.size(36.dp),
                            painter = painterResource(id = markerUI.iconRes),
                            contentDescription = markerUI.type
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = markerUI.name,
                            maxLines = 3,
                            overflow = TextOverflow.Ellipsis,
                            style = MaterialTheme.typography.bodyMedium,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                        markerUI.address?.let {
                            Text(
                                text = it,
                                maxLines = 3,
                                overflow = TextOverflow.Ellipsis,
                                style = MaterialTheme.typography.bodyMedium,
                                fontStyle = FontStyle.Italic,
                                color = Color.White
                            )
                        }

                    }
                }
            }
        }
    }
}

@Preview(showBackground = true, name = "Main Template")
@Composable
private fun MainPreview() {
    val cameraState = rememberCameraPositionState()
    val context = LocalContext.current
    val snackbarHostState = remember { SnackbarHostState() }
    UIContent(context, snackbarHostState, MainScreenState(), cameraState) {}
}