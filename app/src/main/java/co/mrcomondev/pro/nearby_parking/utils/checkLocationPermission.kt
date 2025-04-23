package co.mrcomondev.pro.nearby_parking.utils

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat

/**
 * Created by gesoft
 */

fun checkLocationPermission(context: Context): Boolean {
  return ContextCompat.checkSelfPermission(
    context,
    Manifest.permission.ACCESS_FINE_LOCATION
  ) == PackageManager.PERMISSION_GRANTED
}

private fun requestPermissionLauncher(
  componentActivity: ComponentActivity,
  context: Context
): ActivityResultLauncher<Array<String>> {

  return componentActivity.registerForActivityResult(
    ActivityResultContracts.RequestMultiplePermissions()
  ) { permissions ->
    when {
      permissions.getOrDefault(Manifest.permission.ACCESS_FINE_LOCATION, false) -> {
        startApp(componentActivity, context)
      }

      permissions.getOrDefault(Manifest.permission.ACCESS_COARSE_LOCATION, false) -> {
        startApp(componentActivity, context)
      }

      else -> {
        Toast.makeText(componentActivity, "Location permission denied", Toast.LENGTH_LONG).show()
      }
    }
  }
}

fun requestLocationPermission(componentActivity: ComponentActivity, context: Context) {
  requestPermissionLauncher(componentActivity, context).launch(
    arrayOf(
      Manifest.permission.ACCESS_FINE_LOCATION,
      Manifest.permission.ACCESS_COARSE_LOCATION
    )
  )
}