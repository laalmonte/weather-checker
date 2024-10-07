package com.android.encora.weather.model


data class CityData(
    val weather:List<WeatherData>,
    val main:TempData,
    val wind:WindData,
    val sys: SysData,
    val name:String = ""
) {
}