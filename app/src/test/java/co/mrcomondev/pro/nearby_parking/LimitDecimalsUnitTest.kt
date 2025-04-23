package co.mrcomondev.pro.nearby_parking

import co.mrcomondev.pro.nearby_parking.utils.limitDecimals
import org.junit.Assert.assertEquals
import org.junit.Assert.assertThrows
import org.junit.Assert.assertTrue
import org.junit.Test

/**
 * Created by gesoft
 */
class LimitDecimalsUnitTest {

  val numberToTest = Math.PI

  @Test
  fun getTowDecimals() {
    val newNumber = numberToTest.limitDecimals(2)
    assertEquals(3.14, newNumber, 0.0)
  }

  @Test
  fun getThreeDecimals() {
    val newNumber = numberToTest.limitDecimals(3)
    assertEquals(3.142, newNumber, 0.0)
  }

  @Test
  fun limitDecimalsShouldBeZero() {
    val newNumber = numberToTest.limitDecimals(0)
    assertEquals(3.0, newNumber, 0.0)
  }

  @Test(expected = IllegalArgumentException::class)
  fun limitDecimalsShouldThrowException() {
    numberToTest.limitDecimals(-1)
  }

  @Test
  fun limitDecimalsShouldThrowExceptionWithCorrectMessage() {
    val exception: IllegalArgumentException = assertThrows(IllegalArgumentException::class.java) {
      numberToTest.limitDecimals(-2)
    }

    assertTrue(exception.message?.contains("The number decimals must be greater than 0") == true)
  }

}