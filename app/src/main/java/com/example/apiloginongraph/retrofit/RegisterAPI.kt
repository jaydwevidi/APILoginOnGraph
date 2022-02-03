package com.example.apiloginongraph.retrofit

import com.example.apiloginongraph.models.LoginBody
import com.example.apiloginongraph.models.LoginResponse
import com.example.apiloginongraph.models.RegisterResponse
import com.example.apiloginongraph.models.RegisterUserModel
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface RegisterAPI {

    @POST("registration")
    suspend fun register(@Body userDetails : RegisterUserModel) : Response<RegisterResponse>

    @POST("login")
    suspend fun login ( @Body loginDetails : LoginBody) : Response<LoginResponse>
}