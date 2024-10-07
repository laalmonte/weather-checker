package com.android.encora.weather.db

import javax.inject.Inject
import kotlinx.coroutines.coroutineScope

class WeatherRepository
@Inject constructor(
    private val weatherDao: WeatherDao,
) {

    suspend fun saveWeather(weather: Weather){
        weatherDao.save(weather)
    }


    suspend fun getSaveWeather() : List<Weather> {
        return coroutineScope {
            weatherDao.getSavedWeatherFromDao()
        }
    }

    fun clearData(){
        weatherDao.clearAll()
    }

}