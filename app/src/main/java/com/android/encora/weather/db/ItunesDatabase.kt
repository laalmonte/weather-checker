package com.android.encora.weather.db

import androidx.room.Database
import androidx.room.RoomDatabase

/*
ItunesDatabase is the primary class for room database
*/
@Database(entities = [Track::class], version = 1, exportSchema = false)
abstract class ItunesDatabase : RoomDatabase() {
    abstract fun trackDao(): TrackDao

}