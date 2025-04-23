package co.mrcomondev.pro.nearby_parking.utils.getDistanceTo

import android.location.Location
import com.google.android.gms.maps.model.LatLng

/**
 * Created by gesoft
 */

fun LatLng.distanceTo(
  other: LatLng,
  calculator: LocationCalculator = AndroidLocationCalculator()
): Float {
  return calculator.calculateDistance(this, other)
}

class AndroidLocationCalculator : LocationCalculator {
  override fun calculateDistance(
    pointA: LatLng,
    pointB: LatLng
  ): Float {
    val results = FloatArray(1)
    Location.distanceBetween(
      pointA.latitude,
      pointA.longitude,
      pointB.latitude,
      pointB.longitude,
      results
    )
    return results[0] // meters distance
  }
}