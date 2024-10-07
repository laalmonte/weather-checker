package com.android.encora.weather.db

import androidx.room.*


@Dao
interface WeatherDao {

    @Query("SELECT * FROM Weather ORDER BY name ASC")
    fun getSavedTrackFromDao(): List<Weather>

    @Insert
    fun save(weather: Weather)

    @Update
    fun update(weather: Weather)

    @Query("DELETE FROM Weather")
    fun clearAll()
}