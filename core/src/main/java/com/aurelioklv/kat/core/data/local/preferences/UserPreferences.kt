package com.aurelioklv.kat.core.data.local.preferences

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private const val DATASTORE_NAME = "user_preferences"
private val Context.dataStore by preferencesDataStore(name = DATASTORE_NAME)


class UserPreferences(private val context: Context) {
    val themePreference: Flow<AppTheme> = context.dataStore.data
        .map { preferences ->
            val themeName = preferences[THEME_KEY] ?: AppTheme.SYSTEM.name
            AppTheme.fromName(themeName)
        }

    suspend fun updateTheme(theme: AppTheme) {
        context.dataStore.edit { preferences ->
            preferences[THEME_KEY] = theme.name
        }
    }

    companion object {
        private val THEME_KEY = stringPreferencesKey("theme_preference")
    }
}