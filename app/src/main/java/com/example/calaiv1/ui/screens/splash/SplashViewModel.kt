package com.example.calaiv1.ui.screens.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor() : ViewModel() {
    
    private val _shouldNavigateToOnboarding = MutableStateFlow(false)
    val shouldNavigateToOnboarding: StateFlow<Boolean> = _shouldNavigateToOnboarding
    
    private val _shouldNavigateToMain = MutableStateFlow(false)
    val shouldNavigateToMain: StateFlow<Boolean> = _shouldNavigateToMain
    
    fun checkOnboardingStatus() {
        viewModelScope.launch {
            // For now, we'll simulate checking user status
            // In a real app, you'd check if the user exists and has completed onboarding
            kotlinx.coroutines.delay(1000) // Simulate network/database call
            
            // For demo purposes, always go to onboarding first
            _shouldNavigateToOnboarding.value = true
        }
    }
}
