package com.example.mypocketapp.di

import com.example.mypocketapp.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object EnvModule {
    @Provides @Singleton @Named("baseUrl")
    fun provideBaseUrl(): String = BuildConfig.API_BASE_URL
}