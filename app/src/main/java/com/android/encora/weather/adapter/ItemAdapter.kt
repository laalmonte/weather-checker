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
import com.android.encora.weather.api.data.Results
import com.android.encora.weather.db.Track
import com.android.encora.weather.extension.loadUrl

/*
ItemAdapter is the adapter class for Home API List UI
*/
class ItemAdapter(private val onItemSelectCallback: OnItemSelectCallback) :
    RecyclerView.Adapter<ItemAdapter.ViewHolder>() {

    private var oldResultList = mutableListOf<Results>()
    private var dbTrackList : List<Track> = emptyList()

    private lateinit var mContext: Context

    private var resultList: List<Results> by Delegates.observable(emptyList()) { _, _, _ ->
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.view_item, parent, false))
    }

    override fun getItemCount(): Int = resultList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(resultList[position], position)
    }

    fun updateContext(contextParam: Context){
        mContext = contextParam
    }

    fun updateResultList(newDataSet: List<Results>) {
        oldResultList.clear()
        oldResultList.addAll(newDataSet)
        resultList = emptyList()
        resultList = oldResultList
        notifyDataSetChanged()
    }

    fun updateTrackList(trackList: List<Track>) {
        dbTrackList = emptyList()
        dbTrackList = trackList
        notifyDataSetChanged()
    }


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindItem(obj: Results, position: Int) {
            val tvName = itemView.findViewById<AppCompatTextView>(R.id.tvName)
            val tvGenre = itemView.findViewById<AppCompatTextView>(R.id.tvGenre)
            val tvPrice = itemView.findViewById<AppCompatTextView>(R.id.tvPrice)
            val ivArtwork = itemView.findViewById<AppCompatImageView>(R.id.ivArtwork)
            val trackLayout = itemView.findViewById<LinearLayoutCompat>(R.id.trackLayout)


            var name = ""
            obj.trackName?.let {
                name = "Track Name: " + obj.trackName

                // check if saved to favorite
                var isFavorite = false
                dbTrackList.forEach { dbTrack ->
                    if (dbTrack.trackName.equals(obj.trackName)){ isFavorite = true }
                }

            } ?: run {
                name = "Track Name: None"
            }

            val genre = "Genre: " + obj.primaryGenreName
            val prc = "Price: $" + obj.trackPrice.toString()

            tvName.text = name
            tvGenre.text = genre
            tvPrice.text = prc

            ivArtwork.loadUrl(obj.artworkUrl60)

            trackLayout.setOnClickListener { onItemSelectCallback.onSelectItem(obj) }

        }
    }

    interface OnItemSelectCallback {
        fun onSelectItem(trackObject: Results)
    }

}