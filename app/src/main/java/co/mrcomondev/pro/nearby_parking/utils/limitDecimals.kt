package co.mrcomondev.pro.nearby_parking.utils

import java.math.BigDecimal
import java.math.RoundingMode

/**
 * Created by gesoft
 */
fun Double.limitDecimals(decimals: Int): Double {
  val decimal = BigDecimal(this.toString()).setScale(decimals, RoundingMode.HALF_UP)
  return decimal.toDouble()
}
