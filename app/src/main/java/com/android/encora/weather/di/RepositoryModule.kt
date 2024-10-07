package com.android.encora.weather.di

import com.android.encora.weather.api.TrackService
import com.android.encora.weather.db.TrackDao
import com.android.encora.weather.db.TrackRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/*
RepositoryModule is for dependency injection of repositories
*/
@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideTrackRepository(
        trackDao: TrackDao,
        trackService: TrackService
    ): TrackRepository =
        TrackRepository(trackDao, trackService)
}