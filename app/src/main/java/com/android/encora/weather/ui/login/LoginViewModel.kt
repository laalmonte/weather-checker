package com.android.encora.weather.ui.login

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.encora.weather.api.WeatherRepository
import com.android.encora.weather.model.CityData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel
@Inject constructor(
    private val weatherRepository: WeatherRepository
) : ViewModel() {
    val _weatherResp = MutableLiveData<CityData>()

    init {

    }

    fun getCityData( appId: String) {
        viewModelScope.launch {
            val resp = weatherRepository.getCityData("14.20", "121.15", "98c97084e019c509214b2c526809d017")
            _weatherResp.postValue(resp)
        }
    }
}