package com.example.aplicao.ui.settings

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class SettingsViewModel : ViewModel() {

    private val _darkModeEnabled = MutableStateFlow(false)
    val darkModeEnabled: StateFlow<Boolean> = _darkModeEnabled

    private val _translationEnabled = MutableStateFlow(true)
    val translationEnabled: StateFlow<Boolean> = _translationEnabled

    fun toggleDarkMode() {
        _darkModeEnabled.value = !_darkModeEnabled.value
    }

    fun toggleTranslation() {
        _translationEnabled.value = !_translationEnabled.value
    }
}
