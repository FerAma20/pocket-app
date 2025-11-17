package com.example.mypocketapp.data.repository

import com.example.mypocketapp.data.remote.dto.LoginData

interface AuthRepository {
    suspend fun login(idCompany: String, email: String, password: String): Result<LoginData>
}