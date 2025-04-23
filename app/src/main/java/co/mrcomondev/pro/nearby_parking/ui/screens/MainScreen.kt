package co.mrcomondev.pro.nearby_parking.ui.screens

/**
 * Created by gesoft
 */

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import co.mrcomondev.pro.nearby_parking.ui.composables.MyGoogleMap
import co.mrcomondev.pro.nearby_parking.ui.composables.SliderComp
import co.mrcomondev.pro.nearby_parking.utils.limitDecimals
import co.mrcomondev.pro.nearby_parking.viewmodel.ParkingViewModel
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.maps.android.compose.rememberCameraPositionState

@Composable
fun MainScreen(modifier: Modifier = Modifier, viewModel: ParkingViewModel = viewModel()) {
  val userLocation by viewModel.userLocation.collectAsState()
  val filteredSpots by viewModel.filteredParkingSpots.collectAsState()
  val searchRadius by viewModel.searchRadius.collectAsState()

  val cameraPositionState = rememberCameraPositionState()

  LaunchedEffect(userLocation) {
    if (userLocation.latitude != 0.0 && userLocation.longitude != 0.0) {
      cameraPositionState.animate(
        CameraUpdateFactory.newLatLngZoom(
          userLocation,
          15f
        ),
      )
    }
  }

  Column(
    modifier = modifier
      .fillMaxSize(),
    verticalArrangement = Arrangement.spacedBy(16.dp)
  ) {
    Column(modifier = Modifier.padding(horizontal = 8.dp), verticalArrangement = Arrangement.spacedBy(16.dp)) {
      Text("Search radius: ${searchRadius.limitDecimals(2)} km")
      SliderComp(searchRadius.toFloat()) { viewModel.updateSearchRadius(it.toDouble()) }
    }

    MyGoogleMap(Modifier.weight(1f), cameraPositionState, userLocation, filteredSpots, searchRadius)
  }
}