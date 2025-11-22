package com.example.mypocketapp.di

import com.example.mypocketapp.BuildConfig
import com.example.mypocketapp.data.remote.api.AuthApi
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import javax.inject.Named
import javax.inject.Singleton



// di/NetworkModule.kt
@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides @Singleton
    fun provideJson(): Json = Json {
        ignoreUnknownKeys = true
        explicitNulls = false
        isLenient = true
    }

    @Provides @Singleton
    fun provideOkHttp(): OkHttpClient =
        OkHttpClient.Builder()
            //.addInterceptor(...) // si luego necesitas token
            .build()

    @Provides @Singleton
    fun provideRetrofit(
        json: Json,
        client: OkHttpClient,
        @Named("baseUrl") baseUrl: String
    ): Retrofit {
        val contentType = "application/json".toMediaType()           // import okhttp3.MediaType.Companion.toMediaType
        return Retrofit.Builder()
            .baseUrl(ensureEndsWithSlash(baseUrl))                   // ← evita error de slash faltante
            .addConverterFactory(json.asConverterFactory(contentType))// import com.jakewharton...asConverterFactory
            .client(client)
            .build()
    }

    @Provides @Singleton
    fun provideAuthApi(retrofit: Retrofit): AuthApi =
        retrofit.create(AuthApi::class.java)
}

// helper pequeño para no caer en IllegalArgumentException de Retrofit
private fun ensureEndsWithSlash(url: String) =
    if (url.endsWith("/")) url else "$url/"
