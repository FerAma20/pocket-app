package com.example.mypocketapp.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable data class LoginRequest(val idCompany: String, val email: String, val password: String)
@Serializable data class LoginData(val accessToken: String)