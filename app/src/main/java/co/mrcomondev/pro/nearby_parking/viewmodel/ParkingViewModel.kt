package co.mrcomondev.pro.nearby_parking.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.mrcomondev.pro.nearby_parking.database.PARKING_SPOTS
import co.mrcomondev.pro.nearby_parking.model.ParkingSpot
import co.mrcomondev.pro.nearby_parking.utils.constants
import co.mrcomondev.pro.nearby_parking.utils.distanceTo
import com.google.android.gms.maps.model.LatLng
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn

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
        location.distanceTo(LatLng(spot.lat, spot.lng)) <= radius * 1000 // Convertir km a metros
      }
    }.stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

  init {
    // Datos de ejemplo
    _parkingSpots.value = PARKING_SPOTS
  }

  fun updateUserLocation(latLng: LatLng) {
    _userLocation.value = latLng
  }

  fun updateSearchRadius(radius: Double) {
    _searchRadius.value = radius
  }
}
