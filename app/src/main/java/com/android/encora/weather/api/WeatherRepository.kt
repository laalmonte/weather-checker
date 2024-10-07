package com.android.encora.weather.api

import com.android.encora.weather.model.CityData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject


class WeatherRepository @Inject constructor(private val weatherServiceImp: WeatherServiceImpl) {

//    fun getCityData(city:String): Flow<CityData> = flow {
//        val response= weatherServiceImp.getCity(city,"98c97084e019c509214b2c526809d017")
//        emit(response)
//    }.flowOn(Dispatchers.IO)
//        .conflate()

    suspend fun getCityData(latitude: String, longitude: String): CityData  {
        val response = weatherServiceImp.getCity(latitude, longitude ,"98c97084e019c509214b2c526809d017")
        return  response
    }
}