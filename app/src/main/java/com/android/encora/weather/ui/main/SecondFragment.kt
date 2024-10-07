package com.android.encora.weather.ui.main

import android.content.Intent
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
import com.android.encora.weather.adapter.TrackAdapter
import com.android.encora.weather.api.data.Results
import com.android.encora.weather.databinding.FragmentSecondBinding
import com.android.encora.weather.db.Track
import com.android.encora.weather.model.ParcelableResult


/*
Second Fragment is the Favorites Tab that displays save items as favorites
Favorites are items fetched from the track database
*/

class SecondFragment() : Fragment() {
    private var _binding: FragmentSecondBinding? = null
    private val binding get() = _binding!!
    private var tracksList: List<Track> = emptyList()
    private val activityViewModel by activityViewModels<MainViewModel>()
    private lateinit var trackAdapter: TrackAdapter

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
        activityViewModel.getTracksFromDB()
    }

    // pre-setup for the list track adapter
    private fun setupAdapter(){
        trackAdapter = TrackAdapter(onItemSelectCallback)
        binding.itemList.apply {
            adapter = trackAdapter
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        }
        trackAdapter.updateTrackList(emptyList())
    }

    // callback from track adapter
    private val onItemSelectCallback = object : TrackAdapter.OnItemSelectCallback {
        override fun onSelectItem(track: Track) {
//            val detailIntent = Intent(requireActivity() , DetailActivity::class.java)
//            detailIntent.putExtra("result", ParcelableResult(
//                track.artistId,
//                track.artistName,
//                track.artistViewUrl,
//                track.artworkUrl100,
//                track.artworkUrl30,
//                track.artworkUrl60,
//                track.collectionCensoredName,
//                track.collectionExplicitness,
//                track.collectionId,
//                track.collectionName,
//                track.collectionPrice,
//                track.collectionViewUrl,
//                track.country,
//                track.currency,
//                track.discCount,
//                track.discNumber,
//                track.kind,
//                track.previewUrl,
//                track.primaryGenreName,
//                track.releaseDate,
//                track.trackCensoredName,
//                track.trackCount,
//                track.trackExplicitness,
//                track.trackId,
//                track.trackName,
//                track.trackNumber,
//                track.trackPrice,
//                track.trackTimeMillis,
//                track.trackViewUrl,
//                track.wrapperType,)
//            )
//            startActivity(detailIntent)
        }
    }

    // all lifecycle observe can be found here
    private fun subscribeUI(){
        activityViewModel.data2.observe( viewLifecycleOwner, Observer { tracks ->
            tracksList = tracks
            if (tracksList.size > 0){
                trackAdapter.updateTrackList(tracksList)
                binding.itemList.visibility = View.VISIBLE
                binding.noFavoritesLayout.visibility = View.GONE
            } else {
                binding.itemList.visibility = View.GONE
                binding.noFavoritesLayout.visibility = View.VISIBLE
            }
        })
    }
}