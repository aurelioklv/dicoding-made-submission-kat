package com.aurelioklv.kat.ui.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.aurelioklv.kat.core.data.local.preferences.AppTheme
import com.aurelioklv.kat.core.data.local.preferences.UserPreferences
import kotlinx.coroutines.launch

class SettingsViewModel(private val userPreferences: UserPreferences) : ViewModel() {

    val appTheme = userPreferences.themePreference.asLiveData()

    fun updateTheme(theme: AppTheme) {
        viewModelScope.launch {
            userPreferences.updateTheme(theme)
        }
    }
}