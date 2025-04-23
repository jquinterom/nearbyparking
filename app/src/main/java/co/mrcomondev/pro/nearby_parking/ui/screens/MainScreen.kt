package co.mrcomondev.pro.nearby_parking.ui.screens

/**
 * Created by gesoft
 */

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import co.mrcomondev.pro.nearby_parking.ui.composables.SliderComp
import co.mrcomondev.pro.nearby_parking.viewmodel.ParkingViewModel
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.rememberCameraPositionState

@Composable
fun MyGoogleMaps(modifier: Modifier = Modifier, viewModel: ParkingViewModel = viewModel()) {
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

  Column(modifier = modifier.fillMaxSize()) {

    SliderComp(searchRadius.toFloat()) { viewModel.updateSearchRadius(it.toDouble()) }

    Text("Radio de búsqueda: $searchRadius km")

    // Mapa
    GoogleMap(
      modifier = Modifier.weight(1f),
      cameraPositionState = cameraPositionState,
      properties = MapProperties(
        isMyLocationEnabled = true
      ),
      uiSettings = MapUiSettings(zoomControlsEnabled = true)
    ) {
      Marker(
        position = userLocation,
        title = "Tú estás aquí",
        icon = BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE)
      )

      filteredSpots.forEach { spot ->
        Marker(
          position = LatLng(spot.lat, spot.lng),
          title = "${spot.name} (${spot.price})",
          icon = BitmapDescriptorFactory.defaultMarker(
            if (spot.isFree) BitmapDescriptorFactory.HUE_GREEN
            else BitmapDescriptorFactory.HUE_RED
          )
        )
      }
    }
  }
}