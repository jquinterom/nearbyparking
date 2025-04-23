package co.mrcomondev.pro.nearby_parking

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performTouchInput
import androidx.compose.ui.test.swipeLeft
import androidx.compose.ui.test.swipeRight
import androidx.test.ext.junit.runners.AndroidJUnit4
import co.mrcomondev.pro.nearby_parking.ui.composables.SliderComp
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Created by gesoft
 */
@RunWith(AndroidJUnit4::class)
class SliderTest {

  @get:Rule
  val composeTestRule = createComposeRule()

  @Test
  fun slider_initialValue_isCorrect() {
    var currentValue = 1.0f
    val onValueChange: (Float) -> Unit = { newValue ->
      currentValue = newValue
    }

    composeTestRule.setContent {
      SliderComp (
        searchRadius = 1.0f,
        onValueChange = onValueChange
      )
    }

    composeTestRule.onNodeWithTag("radius_slider")
      .assertExists()
  }

  @Test
  fun slider_updatesValue_whenDraggedRight() {
    var currentValue = 1.0f
    composeTestRule.setContent {
      SliderComp(
        searchRadius = currentValue,
        onValueChange = { currentValue = it }
      )
    }

    composeTestRule.onNodeWithTag("radius_slider")
      .performTouchInput { swipeRight() }

    assert(currentValue > 1.0f)
  }

  @Test
  fun slider_respectsMaximumValue() {
    var currentValue = 1.8f
    composeTestRule.setContent {
      SliderComp(
        searchRadius = currentValue,
        onValueChange = { currentValue = it }
      )
    }

    repeat(3) {
      composeTestRule.onNodeWithTag("radius_slider")
        .performTouchInput { swipeRight() }
    }

    assert(currentValue == 2.0f) // Maximum of composable
  }

  @Test
  fun slider_respectsMinimumValue() {
    var currentValue = 0.6f
    composeTestRule.setContent {
      SliderComp(
        searchRadius = currentValue,
        onValueChange = { currentValue = it }
      )
    }

    repeat(3) {
      composeTestRule.onNodeWithTag("radius_slider")
        .performTouchInput { swipeLeft() }
    }

    assert(currentValue == 0.5f) // Minimum of composable
  }
}