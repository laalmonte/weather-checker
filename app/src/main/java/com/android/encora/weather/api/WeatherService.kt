package com.android.encora.weather.api
import com.android.encora.weather.model.CityData
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherService {

    @GET("weather/")
    suspend fun getCityData(
        @Query("lat") lat:String,
        @Query("lon") lon:String,
        @Query("appid") appId:String
    ):CityData
}