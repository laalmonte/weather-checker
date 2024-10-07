package com.android.encora.weather.di

import android.content.Context
import androidx.room.Room
import com.android.encora.weather.db.ItunesDatabase
import com.android.encora.weather.db.TrackDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
/*
DatabaseModule is for dependency injection of database and dao
*/
@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideItunesDatabase(@ApplicationContext appContext: Context): ItunesDatabase =
        buildDatabase(appContext)


    @Provides
    fun provideTrackDao(trackDatabase: ItunesDatabase): TrackDao = trackDatabase.trackDao()


    private fun buildDatabase(appContext: Context): ItunesDatabase{
        val builder = Room.databaseBuilder(
            appContext.applicationContext,
            ItunesDatabase::class.java, "DB1"

        )
        builder.allowMainThreadQueries()
        return builder.build()
    }



}