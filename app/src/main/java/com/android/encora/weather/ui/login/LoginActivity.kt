package com.android.encora.weather.ui.login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.android.encora.weather.databinding.ActivityLoginBinding
import com.android.encora.weather.ui.main.MainActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var viewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[LoginViewModel::class.java]

        subscribeUi()
        attachActions()
    }

    private fun subscribeUi(){
        viewModel._weatherResp.observe(this, Observer {
            if (it.name != null && it.name != ""){
                val mainIntent = Intent(this , MainActivity::class.java)
                intent.putExtra("appid", binding.etAppId.text.toString())
                startActivity(mainIntent)
            }
        })
    }

    private fun attachActions(){
        binding.btnLogin.setOnClickListener {
            val mainIntent = Intent(this , MainActivity::class.java)
            mainIntent.putExtra("appid", binding.etAppId.text.toString())
            startActivity(mainIntent)
        }
    }
}