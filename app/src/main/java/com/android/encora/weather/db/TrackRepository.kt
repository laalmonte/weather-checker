package com.android.encora.weather.db

import android.util.Log
import androidx.lifecycle.LiveData
import com.android.encora.weather.api.TrackService
import com.android.encora.weather.api.data.Data
import com.android.encora.weather.api.data.NoData
import javax.inject.Inject
import com.android.encora.weather.api.data.Result
import com.android.encora.weather.api.data.Results
import kotlinx.coroutines.coroutineScope

/*
TrackRepository serves as the connection for database and api
*/
class TrackRepository
@Inject constructor(
    private val trackDao: TrackDao,
    private val apiService: TrackService
) {

    suspend fun getResult() : Data<Result> {

        try {
            val response = apiService.getTrackFromApi()
            Log.d("API1", " resp = " + response.toString())
            if (response.isSuccessful) {
                response.body()?.let {
                    return Data.success(it)
                } ?: throw NoData()
            } else {
                return Data.error(response.errorBody().toString(), null)
            }
        } catch (e: Exception) {
            return Data.error(e.message.toString(), null)
        }
    }

    suspend fun saveTrack(result: Results){
        trackDao.save(result.toTrack())
    }


//    fun getSaveTracks(): LiveData<MutableList<Track>> = trackDao.getSavedTrackFromDao()

    suspend fun getSaveTracks() : List<Track> {
        return coroutineScope {
            trackDao.getSavedTrackFromDao()
        }
    }

    fun clearData(){
        trackDao.clearAll()
    }

}