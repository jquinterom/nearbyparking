package co.mrcomondev.pro.nearby_parking

import android.location.Location
import co.mrcomondev.pro.nearby_parking.utils.getDistanceTo.LocationCalculator
import co.mrcomondev.pro.nearby_parking.utils.getDistanceTo.distanceTo
import com.google.android.gms.maps.model.LatLng
import org.junit.Test
import org.junit.Assert.assertEquals
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkStatic
import io.mockk.unmockkStatic
import org.junit.After
import org.junit.Before

class CalculateDistanceUnitTest {

  @Before
  fun setup() {
    mockkStatic(Location::class)
  }

  @After
  fun tearDown() {
    unmockkStatic(Location::class)
  }

  @Test
  fun `distance between same point should be zero`() {
    val mockCalculator = mockk<LocationCalculator>()
    val point = LatLng(40.7128, -74.0060)

    every {mockCalculator.calculateDistance(point, point)} returns 0f

    val distance = point.distanceTo(point, mockCalculator)

    assertEquals(0.0f, distance)
  }

  @Test
  fun `test distance calculation`() {
    val mockCalculator = mockk<LocationCalculator>()
    val point1 = LatLng(0.0, 0.0)
    val point2 = LatLng(0.0, 0.0)

    every { mockCalculator.calculateDistance(point1, point2) } returns 1000f

    val result = point1.distanceTo(point2, mockCalculator)

    assertEquals(1000f, result)
  }

}