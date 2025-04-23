package co.mrcomondev.pro.nearby_parking.database

import co.mrcomondev.pro.nearby_parking.model.ParkingSpot

/**
 * Created by gesoft
 */
val PARKING_SPOTS  = listOf(
  ParkingSpot("1", "Center Parking", 1.853535, -76.046880, false, "$5/h"),
  ParkingSpot("2", "Parking Coffee Mall", 1.849257, -76.048559, true, "Free"),
  ParkingSpot("3", "Mall Parking", 1.861519, -76.049363, false, "$3/h"),
  ParkingSpot("4", "Door Parking", 1.842083, -76.041907, true, "Free"),
  ParkingSpot("5", "End Parking", 1.846189, -76.053947, true, "Free"),
  ParkingSpot("6", "School Parking", 1.849855, -76.049326, false, "$4/h")
)