package com.example.apiloginongraph.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.apiloginongraph.databinding.ActivityRegisteredBinding

private lateinit var binding: ActivityRegisteredBinding
class RegisteredActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisteredBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val details : String? = intent.getStringExtra("1")
        binding.registeredDetails.text = details.toString()


        binding.buttonToLogin.setOnClickListener {
            val intent = Intent(this@RegisteredActivity , LoginActivity::class.java)
            startActivity(intent)
        }
    }
}