package com.android.encora.weather.ui.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.android.encora.weather.ui.main.MainActivity
import com.android.encora.weather.databinding.ActivitySplashBinding

/* SplashActivity is a custom splash loading screen upon app open */
class SplashActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initiates time delay of 3 seconds then goes to MainActivity or the Home Dashboard
        Handler(Looper.getMainLooper()).postDelayed({
            val mainIntent = Intent(this , MainActivity::class.java)
            startActivity(mainIntent)
            finish()
        }, 3000)
    }
}