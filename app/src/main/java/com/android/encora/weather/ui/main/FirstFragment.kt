package com.android.encora.weather.ui.main

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import androidx.lifecycle.Observer
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.android.encora.weather.R
import com.android.encora.weather.databinding.FragmentFirstBinding
import com.android.encora.weather.db.Weather
import com.android.encora.weather.extension.loadDrawable
import com.android.encora.weather.model.CityData
import com.android.encora.weather.util.DateHelper
import com.android.encora.weather.util.MathHelper
import java.text.SimpleDateFormat
import java.util.*


class FirstFragment() : Fragment() {

    private var _binding: FragmentFirstBinding? = null
    private val activityViewModel by activityViewModels<MainViewModel>()
    private val binding get() = _binding!!
    private var locationManager : LocationManager? = null

    private var appIdString = "";

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        checkLocationPermission()
        subscribeUi()

        appIdString  = requireActivity().intent.getStringExtra("appid").toString()
    }

    private fun subscribeUi(){
        activityViewModel._weatherResp.observe(requireActivity(), Observer { response ->
                if (response.weather[0].description != ""){
                    hideLoading()
                    setupData(response)
                } else {
                    showLoading()
                }
        })
    }

    private fun checkLocationPermission(){
        val permission = ContextCompat.checkSelfPermission(requireActivity(),
            android.Manifest.permission.ACCESS_FINE_LOCATION)
        if (permission != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(requireActivity(),
                arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION), 101)
        } else {
            checkCoarsePermission()
        }
    }

    private fun checkCoarsePermission(){
        val permission = ContextCompat.checkSelfPermission(requireActivity(),
            android.Manifest.permission.ACCESS_COARSE_LOCATION)
        if (permission != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(requireActivity(),
                arrayOf(android.Manifest.permission.ACCESS_COARSE_LOCATION), 102)
        } else {
            getLongLat()
        }
    }

    private fun getLongLat(){
        locationManager = requireActivity().getSystemService(Context.LOCATION_SERVICE) as LocationManager?

        locationManager?.let {
            if (ActivityCompat.checkSelfPermission(
                    requireActivity(),
                    Manifest.permission.ACCESS_FINE_LOCATION
                ) == PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                    requireActivity(),
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ) == PackageManager.PERMISSION_GRANTED
            ) {
                it.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,0L, 0f, locationListener)
            }
        }
    }

    private val locationListener: LocationListener = object : LocationListener {
        override fun onLocationChanged(location: Location) {

            val latString = "Latitude: " + location.latitude.toString()
            val longString = "Longitude: " + location.longitude.toString()
            binding.tvLat.text = latString
            binding.tvLong.text = longString

            activityViewModel.getCityData(location.latitude.toString(), location.longitude.toString(), appIdString)
        }
        override fun onStatusChanged(provider: String, status: Int, extras: Bundle) {}
        override fun onProviderEnabled(provider: String) {}
        override fun onProviderDisabled(provider: String) {}
    }


    private fun setupData(cityData: CityData){
        when (cityData.weather[0].description) {
            "clear sky", "mist" -> {
                binding.ivBg.loadDrawable(R.drawable.bg_clear)
                binding.tvLat.setTextColor(ContextCompat.getColor(requireActivity(),R.color.primaryDark))
                binding.tvLong.setTextColor(ContextCompat.getColor(requireActivity(),R.color.primaryDark))
                binding.tvCountry.setTextColor(ContextCompat.getColor(requireActivity(),R.color.primaryDark))
                binding.tvName.setTextColor(ContextCompat.getColor(requireActivity(),R.color.primaryDark))
                binding.tvSunset.setTextColor(ContextCompat.getColor(requireActivity(),R.color.primaryDark))
                binding.tvSunrise.setTextColor(ContextCompat.getColor(requireActivity(),R.color.primaryDark))
            } "haze", "overcast clouds" -> {
                whiteText()
            } else -> {
                whiteText()
            }
        }

        binding.tvName.text = cityData.name
        val celciusTemp = cityData.main.temp - 273.15
        binding.tvTemp.text = MathHelper().roundOffDecimal(celciusTemp)

        val sunsetTime = "Sunset: " + SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(Date(cityData.sys.sunset))
        val sunriseTime = "Sunrise: " + SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(Date(cityData.sys.sunrise))
        binding.tvSunset.text = sunsetTime
        binding.tvSunrise.text = sunriseTime

        binding.tvCountry.text = cityData.sys.country

        if (DateHelper().isDayTime){
            binding.ivNight.visibility = View.GONE
            binding.ivDay.visibility = View.VISIBLE
        } else {
            binding.ivNight.visibility = View.VISIBLE
            binding.ivDay.visibility = View.GONE
        }

        activityViewModel.saveToDB(Weather(cityData.name,cityData.sys.country,MathHelper().roundOffDecimal(celciusTemp),DateHelper().getCurrentDateTime().toString()))
    }

    private fun whiteText(){
        binding.ivBg.loadDrawable(R.drawable.bg_rainy)
        binding.tvLat.setTextColor(ContextCompat.getColor(requireActivity(),R.color.white))
        binding.tvLong.setTextColor(ContextCompat.getColor(requireActivity(),R.color.white))
        binding.tvCountry.setTextColor(ContextCompat.getColor(requireActivity(),R.color.white))
        binding.tvName.setTextColor(ContextCompat.getColor(requireActivity(),R.color.white))
        binding.tvSunset.setTextColor(ContextCompat.getColor(requireActivity(),R.color.white))
        binding.tvSunrise.setTextColor(ContextCompat.getColor(requireActivity(),R.color.white))
    }


    private fun showLoading(){
        binding.customProgressLayout.visibility = View.VISIBLE
        binding.customBgLayout.visibility = View.GONE
    }

    private fun hideLoading(){
        binding.customProgressLayout.visibility = View.GONE
        binding.customBgLayout.visibility = View.VISIBLE
    }

    override fun onResume() {
        super.onResume()
        checkLocationPermission()
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        when (requestCode) {
            101 -> {
                if (grantResults.isEmpty() || grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                    Log.e("PERM", "FINE LOC Permission has been denied by user")
                } else {
                    Log.e("PERM", "FINE Permission has been granted by user")
                    checkCoarsePermission()
                }
            }
            102 -> {
                if (grantResults.isEmpty() || grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                    Log.e("PERM", "COARSE Permission has been denied by user")
                } else {
                    Log.e("PERM", "COARSE Permission has been granted by user")
                    getLongLat()
                }
            }
        }
    }
}