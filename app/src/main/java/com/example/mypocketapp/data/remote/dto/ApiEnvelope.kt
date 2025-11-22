package com.example.mypocketapp.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class ApiEnvelope<T>(
    val status: Boolean,
    val message: String,
    val data: T? = null
)