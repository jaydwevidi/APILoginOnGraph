package com.example.apiloginongraph.models

data class LoginBody(
    val device_token: String,
    val email: String,
    val password: String
)