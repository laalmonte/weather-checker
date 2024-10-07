package com.android.encora.weather.api

import android.util.Log
import com.android.encora.weather.model.CityData
import javax.inject.Inject


class WeatherRepository @Inject constructor(private val weatherServiceImp: WeatherServiceImpl) {

    suspend fun getCityData(latitude: String, longitude: String, appId:String): CityData  {
        val response = weatherServiceImp.getCity(latitude, longitude ,appId)
        return  response
    }
}