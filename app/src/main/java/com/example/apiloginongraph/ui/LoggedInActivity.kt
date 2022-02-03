package com.example.apiloginongraph.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.apiloginongraph.databinding.ActivityLoggedInBinding

class LoggedInActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoggedInBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoggedInBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.loginDetailsTV.text = intent.getStringExtra("1").toString()
    }
}