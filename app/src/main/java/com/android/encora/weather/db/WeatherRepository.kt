package com.android.encora.weather.db

import javax.inject.Inject
import kotlinx.coroutines.coroutineScope

/*
TrackRepository serves as the connection for database and api
*/
class WeatherRepository
@Inject constructor(
    private val weatherDao: WeatherDao,
) {

    suspend fun saveWeather(weather: Weather){
        weatherDao.save(weather)
    }


    suspend fun getSaveWeather() : List<Weather> {
        return coroutineScope {
            weatherDao.getSavedTrackFromDao()
        }
    }

    fun clearData(){
        weatherDao.clearAll()
    }

}