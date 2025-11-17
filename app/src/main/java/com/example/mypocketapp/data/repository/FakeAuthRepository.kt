package com.example.mypocketapp.data.repository

import com.example.mypocketapp.data.remote.dto.LoginData
import kotlinx.coroutines.delay

class FakeAuthRepository : AuthRepository {
    override suspend fun login(idCompany: String, email: String, password: String): Result<LoginData> {
        delay(1200)
        return if (email == "demo@demo.com" && password == "123456")
            Result.success(LoginData("fake_token", null))
        else
            Result.failure(Exception("Credenciales inválidas"))
    }
}