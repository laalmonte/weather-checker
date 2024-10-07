package com.android.encora.weather.di

import com.android.encora.weather.db.WeatherDao
import com.android.encora.weather.db.WeatherRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideTrackRepository(
        weatherDao: WeatherDao,
    ): WeatherRepository =
        WeatherRepository(weatherDao)
}