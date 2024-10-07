package com.android.encora.weather.ui.main

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.encora.weather.adapter.ItemAdapter
import com.android.encora.weather.databinding.FragmentSecondBinding
import com.android.encora.weather.db.Weather

class SecondFragment() : Fragment() {
    private var _binding: FragmentSecondBinding? = null
    private val binding get() = _binding!!
    private var weatherList: List<Weather> = emptyList()
    private val activityViewModel by activityViewModels<MainViewModel>()
    private lateinit var itemAdapter: ItemAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        subscribeUI()
        setupAdapter()
        attachActions()
    }

    private fun attachActions(){
        binding.btnClear.setOnClickListener {
            activityViewModel.clear()
        }
    }
    private fun setupAdapter(){
        itemAdapter = ItemAdapter()
        binding.itemList.apply {
            adapter = itemAdapter
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        }
        itemAdapter.updateWeatherList(emptyList())
    }


    private fun subscribeUI(){
        activityViewModel._clearResp.observe( viewLifecycleOwner, Observer { clear ->
            if (clear == "Success"){
                activityViewModel.getWeatherFromDB()
            }
        })

        activityViewModel.data2.observe( viewLifecycleOwner, Observer { weather ->
            weatherList = weather
            if (weatherList.size > 0){
                itemAdapter.updateWeatherList(weatherList)
                binding.itemList.visibility = View.VISIBLE
                binding.noFavoritesLayout.visibility = View.GONE
                binding.btnClear.visibility = View.VISIBLE
            } else {
                binding.itemList.visibility = View.GONE
                binding.noFavoritesLayout.visibility = View.VISIBLE
                binding.btnClear.visibility = View.GONE
            }
        })
    }

    override fun onResume() {
        super.onResume()
        activityViewModel.getWeatherFromDB()
    }
}