package co.mrcomondev.pro.nearby_parking.ui.composables

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.Circle

/**
 * Created by gesoft
 */
@Composable
fun RadiusCircle(
  center: LatLng,
  radiusKm: Double
) {
  val animatedRadius by animateFloatAsState(
    targetValue = (radiusKm * 1000).toFloat(),
    animationSpec = spring(
      dampingRatio = 0.5f,
      stiffness = 100f
    )
  )

  Circle(
    center = center,
    radius = animatedRadius.toDouble(),
    fillColor = Color.Blue.copy(alpha = 0.1f),
    strokeColor = Color.Blue,
    strokeWidth = 2.5f
  )
}