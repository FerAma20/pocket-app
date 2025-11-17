package com.example.mypocketapp.data.remote.dto

// data/remote/dto/AuthDtos.kt

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonElement

@Serializable
data class LoginRequest(
    val idCompany: String,
    val email: String,
    val password: String
)

/**
 * Envelope genérico de tu API:
 * { status: Boolean, message: String, data: T }
 */
@Serializable
data class ApiEnvelope<T>(
    val status: Boolean,
    val message: String,
    val data: T? = null
)

/**
 * Solo mapeamos lo que realmente necesitamos.
 * Si quieres todo el arbol, puedes poner userData: JsonElement
 */
@Serializable
data class LoginData(
    val accessToken: String,
    val userData: JsonElement? = null // o crea otro DTO si lo necesitas tipado
)
