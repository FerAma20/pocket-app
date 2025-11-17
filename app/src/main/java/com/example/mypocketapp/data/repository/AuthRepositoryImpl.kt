package com.example.mypocketapp.data.repository

// data/repository/AuthRepositoryImpl.kt

import com.example.mypocketapp.data.remote.api.AuthApi
import com.example.mypocketapp.data.remote.dto.ApiEnvelope
import com.example.mypocketapp.data.remote.dto.LoginData
import com.example.mypocketapp.data.remote.dto.LoginRequest
import kotlinx.serialization.json.Json
import retrofit2.Response
import javax.inject.Inject


class AuthRepositoryImpl @Inject constructor(
    private val api: AuthApi,
    private val json: Json
) : AuthRepository {

    override suspend fun login(idCompany: String, email: String, password: String): Result<LoginData> {
        return runCatching {
            val response = api.login(LoginRequest(idCompany, email, password))
            response.requireBodyOrError()
        }
    }

    private fun Response<ApiEnvelope<LoginData>>.requireBodyOrError(): LoginData {
        if (!isSuccessful) {
            error("Error HTTP ${code()}")
        }
        val body = body() ?: error("Respuesta vacía del servidor")

        // Tu API puede devolver 200 con status=false
        if (!body.status) {
            error(body.message.ifBlank { "Credenciales inválidas" })
        }
        return body.data ?: error("No se recibió 'data' en la respuesta")
    }

}
