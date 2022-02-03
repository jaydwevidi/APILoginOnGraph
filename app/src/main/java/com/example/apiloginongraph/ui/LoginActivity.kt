package com.example.apiloginongraph.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.example.apiloginongraph.databinding.ActivityLoginBinding
import com.example.apiloginongraph.models.LoginBody
import com.example.apiloginongraph.retrofit.BuilderInstance
import java.lang.Exception

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    val TAG = "jay LoginActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)


        supportActionBar?.hide()

        binding.password.setText("")
        binding.loginButton.setOnClickListener {
            var password = binding.password.text.toString()
            if (password == "")
                password = "1"

            binding.loginButton.startAnimation()
            lifecycleScope.launchWhenCreated {
                val response = try {
                    BuilderInstance.builderAPI.login(
                        LoginBody(
                            "sadfsdsdafds",
                            binding.emailET.text.toString(),
                            password
                        )
                    )
                }

                catch (e: Exception) {
                    binding.loginButton.revertAnimation()
                    binding.errorText.text = e.message.toString()
                    Log.d(TAG, "onCreate: login Exception $e  , ${e.message}")
                    return@launchWhenCreated


                }

                if(response.isSuccessful){
                    val body = response.body()
                    Log.d(TAG, "onCreate: response successful ${response.body()}")
                    if (body!!.status){
                        val toLoggedIn = Intent(this@LoginActivity , LoggedInActivity::class.java)
                        toLoggedIn.putExtra("1" , body.toString())
                        binding.loginButton.revertAnimation()
                        startActivity(toLoggedIn)
                    }
                    else{
                        binding.errorText.text = body.message
                        binding.loginButton.revertAnimation()
                    }
                }
                else{
                    Log.d(TAG, "onCreate: response unsuccessful")
                    Toast.makeText(this@LoginActivity, "some Error Occurred", Toast.LENGTH_SHORT)
                        .show()
                    binding.loginButton.revertAnimation()
                }
            }
        }


    }
}