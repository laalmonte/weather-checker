package com.android.encora.weather

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import java.io.File

@HiltAndroidApp
class WeatherChecker : Application(){
    companion object {
        lateinit var instance: Application

    }

    init {
        instance = this
    }

    override fun onCreate() {
        super.onCreate()
        val dexOutputDir: File = codeCacheDir
        dexOutputDir.setReadOnly()

    }

    override fun onTerminate() {
        super.onTerminate()
    }
}