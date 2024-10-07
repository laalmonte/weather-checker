package com.android.encora.weather.db

import androidx.lifecycle.LiveData
import androidx.room.*

/*
TrackDao has queries and interface for database
*/
@Dao
interface TrackDao {

    @Query("SELECT * FROM Track ORDER BY artistName ASC")
    fun getSavedTrackFromDao(): List<Track>

    @Insert
    fun save(tracks: Track)

    @Update
    fun update(tracks: Track)

    @Query("DELETE FROM Track")
    fun clearAll()
}