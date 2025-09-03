package com.example.calaiv1.ui.screens.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor() : ViewModel() {

    var onNavigateToOnboarding: (() -> Unit)? = null
    var onNavigateToMain: (() -> Unit)? = null

    fun checkOnboardingStatus() {
        viewModelScope.launch {
            // For now, we'll simulate checking user status
            // In a real app, you'd check if the user exists and has completed onboarding
            delay(1000) // Simulate network/database call

            // For demo purposes, always go to onboarding first
            onNavigateToOnboarding?.invoke()
        }
    }
}
