package co.mrcomondev.pro.nearby_parking.utils.getDistanceTo

import com.google.android.gms.maps.model.LatLng

/**
 * Created by gesoft
 */
interface LocationCalculator {
  fun calculateDistance(pointA: LatLng, pointB: LatLng): Float
}