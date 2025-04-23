package co.mrcomondev.pro.nearby_parking.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.enableEdgeToEdge
import co.mrcomondev.pro.nearby_parking.utils.checkLocationPermission
import co.mrcomondev.pro.nearby_parking.utils.requestLocationPermission
import co.mrcomondev.pro.nearby_parking.utils.startApp

class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()

    if (checkLocationPermission(this)) {
      startApp(this, this@MainActivity)
    } else {
      requestLocationPermission(this, this@MainActivity)
    }
  }
}