package co.mrcomondev.pro.nearby_parking.utils

import java.math.BigDecimal
import java.math.RoundingMode

/**
 * Created by gesoft
 */
fun Double.limitDecimals(decimals: Int): Double {
  if (decimals < 0) throw IllegalArgumentException("The number decimals must be greater than 0")

  val decimal = BigDecimal(this.toString()).setScale(decimals, RoundingMode.HALF_UP)
  return decimal.toDouble()
}
