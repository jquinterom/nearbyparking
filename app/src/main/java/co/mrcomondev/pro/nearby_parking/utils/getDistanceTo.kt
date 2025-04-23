package co.mrcomondev.pro.nearby_parking.utils

import android.location.Location
import com.google.android.gms.maps.model.LatLng

/**
 * Created by gesoft
 */

fun LatLng.distanceTo(other: LatLng): Float {
  val results = FloatArray(1)
  Location.distanceBetween(
    this.latitude,
    this.longitude,
    other.latitude,
    other.longitude,
    results
  )
  return results[0] // Distancia en metros
}