package com.android.encora.weather.ui.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.android.encora.weather.ui.main.MainActivity
import com.android.encora.weather.databinding.ActivitySplashBinding
import com.android.encora.weather.ui.login.LoginActivity

class SplashActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Handler(Looper.getMainLooper()).postDelayed({
            val loginIntent = Intent(this , LoginActivity::class.java)
//            val loginIntent = Intent(this , MainActivity::class.java)
            startActivity(loginIntent)
            finish()
        }, 3000)
    }
}