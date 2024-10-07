package com.android.encora.weather.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.encora.weather.api.WeatherRepository
import com.android.encora.weather.db.Weather
import com.android.encora.weather.model.*
import kotlinx.coroutines.launch
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*
import javax.inject.Inject


@FlowPreview
@HiltViewModel
class MainViewModel
@Inject constructor(
    private val weatherDbRepository: com.android.encora.weather.db.WeatherRepository,
    private val weatherRepository: WeatherRepository
) : ViewModel() {


    val _weatherResp = MutableLiveData<CityData>()
    val _saveResp = MutableLiveData<String>()
    val _clearResp = MutableLiveData<String>()

    private val _data2 = MutableLiveData<List<Weather>>()

    val data2: LiveData<List<Weather>>
        get() = _data2

    init {

    }

    fun getCityData(longitude: String, latitude: String) {
        viewModelScope.launch {
            val resp = weatherRepository.getCityData(latitude, longitude)
            _weatherResp.postValue(resp)
        }
    }

    fun saveToDB(weather: Weather) =
        viewModelScope.launch {
            weatherDbRepository.saveWeather(weather)
            _saveResp.postValue("Success")
        }

    fun getWeatherFromDB() =
        viewModelScope.launch {
            _data2.postValue(weatherDbRepository.getSaveWeather())
        }

    fun clear(){
        viewModelScope.launch {
            weatherDbRepository.clearData()
            _clearResp.postValue("Success")
        }
    }


}