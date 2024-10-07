package com.android.encora.weather.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.recyclerview.widget.RecyclerView
import com.airbnb.lottie.LottieAnimationView
import com.android.encora.weather.R
import com.android.encora.weather.db.Track
import com.android.encora.weather.extension.loadUrl
import kotlin.properties.Delegates

/*
TrackAdapter is the adapter class for Favorites Database List UI
*/
class TrackAdapter(private val onItemSelectCallback: OnItemSelectCallback) :
    RecyclerView.Adapter<TrackAdapter.ViewHolder>() {

    private var oldTrackList = mutableListOf<Track>()
    private lateinit var mContext: Context

    private var trackList: List<Track> by Delegates.observable(emptyList()) { _, _, _ ->
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.view_item, parent, false))
    }

    override fun getItemCount(): Int = trackList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(trackList[position], position)
    }

    fun updateContext(contextParam: Context){
        mContext = contextParam
    }

    fun updateTrackList(newDataSet: List<Track>) {
        oldTrackList.clear()
        oldTrackList.addAll(newDataSet)
        trackList = emptyList()
        trackList = oldTrackList
        notifyDataSetChanged()
    }

    fun updateEmptyTrackList() {
        oldTrackList.clear()
        trackList = emptyList()
        trackList = oldTrackList
        notifyDataSetChanged()
    }


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindItem(obj: Track, position: Int) {
            val tvName = itemView.findViewById<AppCompatTextView>(R.id.tvName)
            val tvGenre = itemView.findViewById<AppCompatTextView>(R.id.tvGenre)
            val tvPrice = itemView.findViewById<AppCompatTextView>(R.id.tvPrice)
            val ivArtwork = itemView.findViewById<AppCompatImageView>(R.id.ivArtwork)
            val trackLayout = itemView.findViewById<LinearLayoutCompat>(R.id.trackLayout)


            var name = ""
            obj.trackName?.let {
                name = "Track Name: " + obj.trackName
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
        fun onSelectItem(track: Track)
    }

}