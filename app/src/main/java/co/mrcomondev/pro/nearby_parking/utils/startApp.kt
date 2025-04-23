package co.mrcomondev.pro.nearby_parking.utils

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.core.content.ContextCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import co.mrcomondev.pro.nearby_parking.ui.screens.MainScreen
import co.mrcomondev.pro.nearby_parking.ui.theme.NearbyParkingTheme
import co.mrcomondev.pro.nearby_parking.viewmodel.ParkingViewModel
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.model.LatLng
import kotlinx.coroutines.tasks.await

/**
 * Created by gesoft
 */
fun startApp(componentActivity: ComponentActivity, context: Context) {
  componentActivity.setContent {
    val viewModel: ParkingViewModel = viewModel()

    NearbyParkingTheme {
      Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        MainScreen(
          modifier = Modifier.padding(innerPadding)
        )
      }

      LaunchedEffect(Unit) {
        if (ContextCompat.checkSelfPermission(
            context,
            Manifest.permission.ACCESS_FINE_LOCATION
          ) == PackageManager.PERMISSION_GRANTED
        ) {
          val location = LocationServices.getFusedLocationProviderClient(context)
            .lastLocation.await()

          location?.let {
            viewModel.updateUserLocation(LatLng(it.latitude, it.longitude))
          }
        }
      }
    }
  }
}