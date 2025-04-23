package co.mrcomondev.pro.nearby_parking.ui.composables

import androidx.compose.foundation.layout.height
import androidx.compose.material3.Slider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import co.mrcomondev.pro.nearby_parking.ui.theme.NearbyParkingTheme

/**
 * Created by gesoft
 */
@Composable
fun SliderComp(searchRadius: Float, onValueChange: (Float) -> Unit) {
  Slider(
    modifier = Modifier.height(8.dp).testTag("radius_slider"),
    value = searchRadius,
    valueRange = 0.5f..2f,
    onValueChange = onValueChange,

  )
}

@Preview
@Composable
private fun SliderCompPrev() {
  NearbyParkingTheme {
    SliderComp(searchRadius = 5f, onValueChange = {})
  }
}