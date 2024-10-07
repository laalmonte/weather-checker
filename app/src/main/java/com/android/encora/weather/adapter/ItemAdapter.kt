package com.android.encora.weather.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.recyclerview.widget.RecyclerView
import com.airbnb.lottie.LottieAnimationView
import com.android.encora.weather.R
import kotlin.properties.Delegates
import com.android.encora.weather.db.Weather
import com.android.encora.weather.extension.loadUrl

/*
ItemAdapter is the adapter class for Home API List UI
*/
class ItemAdapter() :
    RecyclerView.Adapter<ItemAdapter.ViewHolder>() {

    private var dbWeatherList : List<Weather> = emptyList()

    private lateinit var mContext: Context

    private var weatherList: List<Weather> by Delegates.observable(emptyList()) { _, _, _ ->
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.view_item, parent, false))
    }

    override fun getItemCount(): Int = weatherList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(weatherList[position], position)
    }

    fun updateContext(contextParam: Context){
        mContext = contextParam
    }

    fun updateWeatherList(weatherListParam: List<Weather>) {
        weatherList = emptyList()
        weatherList = weatherListParam
        notifyDataSetChanged()
    }


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindItem(obj: Weather, position: Int) {
            val tvName = itemView.findViewById<AppCompatTextView>(R.id.tvName)
            val tvCountry = itemView.findViewById<AppCompatTextView>(R.id.tvCountry)
            val tvTemp = itemView.findViewById<AppCompatTextView>(R.id.tvTemp)
            val tvTime = itemView.findViewById<AppCompatTextView>(R.id.tvTime)


            var name = ""
            obj.name?.let {
                name = "City Name: " + obj.name

            } ?: run {
                name = "City Name: None"
            }

            val country = "Country: " + obj.country
            val temp = "Temp: " + obj.temperature.toString() + " Celcius"
            val time = "Time: " + obj.timestamp.toString()

            tvName.text = name
            tvCountry.text = country
            tvTime.text = time
            tvTemp.text = temp

        }
    }

}