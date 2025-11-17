package com.example.mypocketapp.data.repository

import kotlinx.coroutines.delay

class FakeAuthRepository : AuthRepository {
    override suspend fun login(email: String, password: String): Result<Unit> {
        delay(1200)
        return if (email == "demo@demo.com" && password == "123456")
            Result.success(Unit)
        else
            Result.failure(Exception("Credenciales inválidas"))
    }
}