package com.android.encora.weather.di

import android.content.Context
import androidx.room.Room
import com.android.encora.weather.db.WeatherDao
import com.android.encora.weather.db.WeatherDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideItunesDatabase(@ApplicationContext appContext: Context): WeatherDatabase =
        buildDatabase(appContext)


    @Provides
    fun provideTrackDao(weatherDatabase: WeatherDatabase): WeatherDao = weatherDatabase.weatherDao()


    private fun buildDatabase(appContext: Context): WeatherDatabase{
        val builder = Room.databaseBuilder(
            appContext.applicationContext,
            WeatherDatabase::class.java, "DB1"
        )
        builder.allowMainThreadQueries()
        return builder.build()
    }
}