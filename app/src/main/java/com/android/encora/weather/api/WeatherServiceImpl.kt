package com.android.encora.weather.api
import com.android.encora.weather.model.CityData
import javax.inject.Inject

class WeatherServiceImpl @Inject constructor(private val weatherService: WeatherService) {

    suspend fun getCity(longitude: String,latitude: String,appId:String):CityData
    = weatherService.getCityData(latitude, longitude,appId)
}