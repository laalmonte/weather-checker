package com.android.encora.weather.api

import com.android.encora.weather.model.CityData
import javax.inject.Inject


class WeatherRepository @Inject constructor(private val weatherServiceImp: WeatherServiceImpl) {

    suspend fun getCityData(latitude: String, longitude: String): CityData  {
        val response = weatherServiceImp.getCity(latitude, longitude ,"98c97084e019c509214b2c526809d017")
        return  response
    }
}