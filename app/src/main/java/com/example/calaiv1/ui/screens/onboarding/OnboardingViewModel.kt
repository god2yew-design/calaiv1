package com.example.calaiv1.ui.screens.onboarding

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.calaiv1.domain.model.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class OnboardingViewModel @Inject constructor() : ViewModel() {
    
    private val _uiState = MutableStateFlow(OnboardingUiState())
    val uiState: StateFlow<OnboardingUiState> = _uiState.asStateFlow()
    
    fun updateSex(sex: Sex) {
        _uiState.value = _uiState.value.copy(
            user = _uiState.value.user.copy(sex = sex)
        )
        validateForm()
    }
    
    fun updateAge(age: Int?) {
        _uiState.value = _uiState.value.copy(
            user = _uiState.value.user.copy(age = age)
        )
        validateForm()
    }
    
    fun updateHeight(height: Float?) {
        _uiState.value = _uiState.value.copy(
            user = _uiState.value.user.copy(height = height)
        )
        validateForm()
    }
    
    fun updateWeight(weight: Float?) {
        _uiState.value = _uiState.value.copy(
            user = _uiState.value.user.copy(weight = weight)
        )
        validateForm()
    }
    
    fun updateGoalType(goalType: GoalType) {
        _uiState.value = _uiState.value.copy(
            user = _uiState.value.user.copy(goalType = goalType)
        )
        validateForm()
    }
    
    fun updateGoalWeight(goalWeight: Float?) {
        _uiState.value = _uiState.value.copy(
            user = _uiState.value.user.copy(goalWeight = goalWeight)
        )
        validateForm()
    }
    
    fun updatePreferredDiet(dietType: DietType) {
        _uiState.value = _uiState.value.copy(
            user = _uiState.value.user.copy(preferredDiet = dietType)
        )
    }
    
    fun updateFoodDislikesInput(input: String) {
        _uiState.value = _uiState.value.copy(foodDislikesInput = input)
        val foodDislikes = input.split(",").map { it.trim() }.filter { it.isNotEmpty() }
        _uiState.value = _uiState.value.copy(
            user = _uiState.value.user.copy(foodDislikes = foodDislikes)
        )
    }
    
    fun updateAllergiesInput(input: String) {
        _uiState.value = _uiState.value.copy(allergiesInput = input)
        val allergies = input.split(",").map { it.trim() }.filter { it.isNotEmpty() }
        _uiState.value = _uiState.value.copy(
            user = _uiState.value.user.copy(allergies = allergies)
        )
    }
    
    fun updateActivityLevel(activityLevel: ActivityLevel) {
        _uiState.value = _uiState.value.copy(
            user = _uiState.value.user.copy(activityLevel = activityLevel)
        )
        validateForm()
    }
    
    fun updateUnits(units: Units) {
        _uiState.value = _uiState.value.copy(
            user = _uiState.value.user.copy(units = units)
        )
        validateForm()
    }
    
    fun updateGoalDate(goalDate: Long?) {
        _uiState.value = _uiState.value.copy(
            user = _uiState.value.user.copy(goalDate = goalDate)
        )
        validateForm()
    }
    
    fun updateHealthConditionsInput(input: String) {
        _uiState.value = _uiState.value.copy(healthConditionsInput = input)
        val healthConditions = input.split(",").map { it.trim() }.filter { it.isNotEmpty() }
        _uiState.value = _uiState.value.copy(
            user = _uiState.value.user.copy(healthConditions = healthConditions)
        )
    }
    
    private fun validateForm() {
        val user = _uiState.value.user
        val isValid = user.sex != null && 
                user.age != null && user.age > 0 &&
                user.height != null && user.height > 0 &&
                user.weight != null && user.weight > 0 &&
                user.goalType != null &&
                user.goalWeight != null && user.goalWeight > 0 &&
                user.preferredDiet != null &&
                user.goalDate != null &&
                user.activityLevel != null
        
        _uiState.value = _uiState.value.copy(isFormValid = isValid)
    }
    
    fun completeOnboarding() {
        viewModelScope.launch {
            // For now, just mark onboarding as completed
            // In a real app, you'd save the user data
            _uiState.value = _uiState.value.copy(
                isOnboardingCompleted = true
            )
        }
    }
    
    fun skipOnboarding() {
        viewModelScope.launch {
            // For now, just mark onboarding as completed
            // In a real app, you'd create a default user
            _uiState.value = _uiState.value.copy(
                isOnboardingCompleted = true
            )
        }
    }
}

data class OnboardingUiState(
    val user: User = User(
        id = UUID.randomUUID().toString(),
        email = "user@example.com"
    ),
    val foodDislikesInput: String = "",
    val allergiesInput: String = "",
    val healthConditionsInput: String = "",
    val isFormValid: Boolean = false,
    val isOnboardingCompleted: Boolean = false
)
