package com.android.encora.weather.ui.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.encora.weather.api.WeatherRepository
import com.android.encora.weather.api.data.Data
import com.android.encora.weather.db.TrackRepository
import com.android.encora.weather.api.data.Result
import com.android.encora.weather.db.Track
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
    private val trackRepository: TrackRepository,
    private val weatherRepository: WeatherRepository
) : ViewModel() {

    private val _weatherResponse: MutableStateFlow<CityData> = MutableStateFlow(
        CityData(
            listOf(WeatherData()),
            TempData(),
            WindData(),
            SysData(),
        )
    )

    val weatherResponse: StateFlow<CityData> = _weatherResponse
    private val searchChannel = MutableSharedFlow<String>(1)

    private val _data = MutableLiveData<Data<Result>>()
    private val _data2 = MutableLiveData<List<Track>>()
    val data: LiveData<Data<Result>>
        get() = _data

    val data2: LiveData<List<Track>>
        get() = _data2

    init {

    }

    fun getCityData(longitude: String, latitude: String) {
        viewModelScope.launch {
            val resp = weatherRepository.getCityData(latitude, longitude)
            _weatherResponse.value = resp
        }
    }

    // calls API to get responses as result object for the home tab
    fun getTracks() =
        viewModelScope.launch {
            _data.postValue(Data.loading(null))
            _data.postValue(trackRepository.getResult())
        }

    // gets saved tracks from db for the favorites tab
    fun getTracksFromDB() =
        viewModelScope.launch {
            _data2.postValue(trackRepository.getSaveTracks())
        }


//    fun clearData() { trackRepository.clearData() }

}