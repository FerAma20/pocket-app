package com.example.mypocketapp.data.local

import kotlinx.coroutines.flow.firstOrNull

// data/local/SessionDataStore.kt

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

// Extensión única del contexto (nombre del DataStore)
private val Context.sessionDataStore by preferencesDataStore(name = "session")

@Singleton
class SessionDataStore @Inject constructor(
    @ApplicationContext private val context: Context
) {
    private object Keys {
        val ACCESS = stringPreferencesKey("access_token")
        val REFRESH = stringPreferencesKey("refresh_token")
        val IS_LOGGED_IN = booleanPreferencesKey("is_logged_in")
    }

    // -------------------------------
    // 🔐 Guardar sesión
    // -------------------------------
    suspend fun saveSession(
        accessToken: String,
        refreshToken: String? = null
    ) {
        context.sessionDataStore.edit { prefs ->
            prefs[Keys.ACCESS] = accessToken
            refreshToken?.let { prefs[Keys.REFRESH] = it }
            prefs[Keys.IS_LOGGED_IN] = true
        }
    }

    // -------------------------------
    // 📤 Eliminar sesión (logout)
    // -------------------------------
    suspend fun clearSession() {
        context.sessionDataStore.edit { prefs ->
            prefs.clear()
        }
    }

    // -------------------------------
    // 📥 Lecturas como Flow
    // -------------------------------
    val accessTokenFlow: Flow<String?> =
        context.sessionDataStore.data.map { it[Keys.ACCESS] }

    val refreshTokenFlow: Flow<String?> =
        context.sessionDataStore.data.map { it[Keys.REFRESH] }

    val isLoggedInFlow: Flow<Boolean> =
        context.sessionDataStore.data.map { it[Keys.IS_LOGGED_IN] ?: false }

    // -------------------------------
    // 🧭 Métodos suspend para lectura puntual
    // -------------------------------
    suspend fun getAccessToken(): String? =
        accessTokenFlow.firstOrNull()

    suspend fun getRefreshToken(): String? =
        refreshTokenFlow.firstOrNull()

    suspend fun isLoggedIn(): Boolean =
        isLoggedInFlow.firstOrNull() ?: false
}
