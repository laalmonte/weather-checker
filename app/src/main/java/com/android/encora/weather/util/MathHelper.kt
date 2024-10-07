package com.android.encora.weather.util

import java.math.RoundingMode
import java.text.DecimalFormat

class MathHelper {

     fun roundOffDecimal(number: Double): String {
        val df = DecimalFormat("#")
        df.roundingMode = RoundingMode.FLOOR
        return df.format(number).toString()
    }
}