package com.example.mypocketapp.data.remote.api

// data/remote/api/AuthApi.kt
import com.example.mypocketapp.data.remote.dto.ApiEnvelope;
import com.example.mypocketapp.data.remote.dto.LoginData;
import com.example.mypocketapp.data.remote.dto.LoginRequest;
import retrofit2.Response
import retrofit2.http.Body;
import retrofit2.http.POST;

interface AuthApi {
    @POST("api/auth/signin")
    suspend fun login(@Body body: LoginRequest): Response<ApiEnvelope<LoginData>>
}