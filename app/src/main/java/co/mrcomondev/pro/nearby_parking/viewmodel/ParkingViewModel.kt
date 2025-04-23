package co.mrcomondev.pro.nearby_parking.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.mrcomondev.pro.nearby_parking.model.ParkingSpot
import co.mrcomondev.pro.nearby_parking.utils.constants
import co.mrcomondev.pro.nearby_parking.utils.getDistanceTo.distanceTo
import com.google.android.gms.maps.model.LatLng
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlin.random.Random

/**
 * Created by gesoft
 */
class ParkingViewModel : ViewModel() {
  private val _userLocation = MutableStateFlow(constants.DEFAULT_LOCATION)
  val userLocation = _userLocation.asStateFlow()

  private val _searchRadius = MutableStateFlow(1.0)
  val searchRadius = _searchRadius.asStateFlow()

  private val _parkingSpots = MutableStateFlow<List<ParkingSpot>>(emptyList())
  val filteredParkingSpots: StateFlow<List<ParkingSpot>> =
    _userLocation.combine(_searchRadius) { location, radius ->
      _parkingSpots.value.filter { spot ->
        location.distanceTo(LatLng(spot.lat, spot.lng)) <= radius * 1000 // Convert to kilometers
      }
    }.stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

  init {
    filterParkingByUserLocation()
  }

  fun updateUserLocation(latLng: LatLng) {
    _userLocation.value = latLng
  }

  fun updateSearchRadius(radius: Double) {
    _searchRadius.value = radius

  }

  fun filterParkingByUserLocation() {
    val userLocation = _userLocation.value
    var latDistance = 0.0025
    var lngDistance = 0.0035


    for (i in 0..15) {
      val randomLat = Random.nextDouble(-0.0045, 0.0045) + latDistance
      val randomLong = Random.nextDouble(-0.0045, 0.0055) + lngDistance

      val isFree = randomLat < 0
      val newLatitude =
        if (isFree) userLocation.latitude + randomLat else userLocation.latitude - randomLat
      val newLongitude =
        if (isFree) userLocation.longitude + randomLong else userLocation.longitude - randomLong

      val newPoint1 = ParkingSpot(
        id = i.toString(),
        name = "Nearby Parking $i",
        lat = newLatitude,
        lng = newLongitude,
        isFree = isFree,
        price = if (isFree) "Free" else "$${Random.nextInt(1, 10)}/h"
      )

      _parkingSpots.update { currentList ->
        currentList + newPoint1
      }
    }
  }
}
