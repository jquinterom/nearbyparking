package co.mrcomondev.pro.nearby_parking.model

/**
 * Created by gesoft
 */

data class ParkingSpot(
  val id: String,
  val name: String,
  val lat: Double,
  val lng: Double,
  val isFree: Boolean,
  val price: String
)
