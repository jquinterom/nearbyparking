package co.mrcomondev.pro.nearby_parking.ui.composables

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import co.mrcomondev.pro.nearby_parking.model.ParkingSpot
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.CameraPositionState
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.Marker

/**
 * Created by gesoft
 */
@Composable
fun MyGoogleMap(modifier: Modifier = Modifier, cameraPositionState: CameraPositionState, userLocation: LatLng, filteredSpots: List<ParkingSpot>, searchRadius: Double) {
  GoogleMap(
    modifier = modifier,
    cameraPositionState = cameraPositionState,
    properties = MapProperties(
      isMyLocationEnabled = true
    ),
    uiSettings = MapUiSettings(zoomControlsEnabled = true)
  ) {
    Marker(
      position = userLocation,
      title = "Your Location",
      icon = BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE)
    )

    RadiusCircle(userLocation, searchRadius)

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