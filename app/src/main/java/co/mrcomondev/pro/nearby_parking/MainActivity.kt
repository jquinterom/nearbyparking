package co.mrcomondev.pro.nearby_parking

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import co.mrcomondev.pro.nearby_parking.ui.theme.NearbyParkingTheme
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapType
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.Marker

class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()
    setContent {
      NearbyParkingTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
          MyGoogleMaps(
            modifier = Modifier.padding(innerPadding)
          )
        }
      }
    }
  }
}

@Composable
fun MyGoogleMaps(modifier: Modifier = Modifier) {
  val marker = LatLng(1.836291, -76.007408)
  val properties : MapProperties by remember {
    mutableStateOf(MapProperties(mapType = MapType.NORMAL))

  }

  val uiSettings by remember {
    mutableStateOf(MapUiSettings(zoomControlsEnabled = false))
  }

  GoogleMap(modifier = modifier, properties = properties, uiSettings = uiSettings) {
    Marker(position = marker, title = "Higuerón")
  }
}