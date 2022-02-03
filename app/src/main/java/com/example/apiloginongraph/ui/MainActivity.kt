package com.example.apiloginongraph.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.lifecycleScope
import com.example.apiloginongraph.databinding.ActivityMainBinding
import com.example.apiloginongraph.models.RegisterResponse
import com.example.apiloginongraph.models.RegisterUserModel
import com.example.apiloginongraph.retrofit.BuilderInstance

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    val TAG = "Jay MainActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        supportActionBar?.hide()

        binding.registerButton.setOnClickListener {
            binding.registerButton.startAnimation()
            startRegistration()
        }

        binding.alreadyRegistered.setOnClickListener {
            val sendToLogin = Intent(this , LoginActivity::class.java )
            startActivity(sendToLogin)
        }

    }

    private fun startRegistration(){
        lifecycleScope.launchWhenCreated {
            val response = try {
                BuilderInstance.builderAPI.register(getUser())
            } catch (e: Exception) {
                binding.registerButton.revertAnimation()
                binding.errorText.text = e.message
                Log.e(TAG, "onCreate: Some Exception ${e.message} $e")
                return@launchWhenCreated
            }
            if (response.isSuccessful) {
                Log.e(TAG, "response : ${response.body()}")
                val body : RegisterResponse = response.body()!!

                if (body.status){
                    Log.d(TAG, "onCreate: user registered $body")
                    val registeredActivity = Intent(this@MainActivity , RegisteredActivity::class.java)
                    registeredActivity.putExtra("1" , body.toString())
                    binding.registerButton.revertAnimation()
                    startActivity(registeredActivity)
                }
                else {
                    binding.errorText.text = body.message
                    binding.registerButton.revertAnimation()
                }
            }
        }
    }

    private fun getUser() = RegisterUserModel (
                first_name = binding.nameET.text.toString(),
                email = binding.emailET.text.toString(),
                password = binding.password.text.toString(),
                user_name = binding.userNameET.text.toString()
            )
}