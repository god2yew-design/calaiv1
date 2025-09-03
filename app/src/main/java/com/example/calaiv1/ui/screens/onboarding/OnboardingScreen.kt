package com.example.calaiv1.ui.screens.onboarding

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.calaiv1.domain.model.*
import com.example.calaiv1.ui.theme.AccentOrange

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OnboardingScreen(
    navController: NavController,
    viewModel: OnboardingViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    
    LaunchedEffect(uiState.isOnboardingCompleted) {
        if (uiState.isOnboardingCompleted) {
            navController.navigate("main") {
                popUpTo("onboarding") { inclusive = true }
            }
        }
    }
    
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Complete Your Profile") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surface
                )
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            // Welcome Header
            Text(
                text = "Let's personalize your experience",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onBackground
            )
            
            // Basic Information Section
            OnboardingSection(title = "Basic Information") {
                // Sex Selection
                Text(
                    text = "Sex",
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.Medium
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Sex.values().forEach { sex ->
                        FilterChip(
                            selected = uiState.user.sex == sex,
                            onClick = { viewModel.updateSex(sex) },
                            label = { Text(sex.name.replace("_", " ")) }
                        )
                    }
                }
                
                Spacer(modifier = Modifier.height(16.dp))
                
                // Age Input
                OutlinedTextField(
                    value = uiState.user.age?.toString() ?: "",
                    onValueChange = { viewModel.updateAge(it.toIntOrNull()) },
                    label = { Text("Age") },
                    modifier = Modifier.fillMaxWidth(),
                    keyboardOptions = androidx.compose.foundation.text.KeyboardOptions(
                        keyboardType = androidx.compose.ui.text.input.KeyboardType.Number
                    )
                )
                
                Spacer(modifier = Modifier.height(16.dp))
                
                // Height and Weight
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    OutlinedTextField(
                        value = uiState.user.height?.toString() ?: "",
                        onValueChange = { viewModel.updateHeight(it.toFloatOrNull()) },
                        label = { Text("Height") },
                        modifier = Modifier.weight(1f),
                        keyboardOptions = androidx.compose.foundation.text.KeyboardOptions(
                            keyboardType = androidx.compose.ui.text.input.KeyboardType.Decimal
                        )
                    )
                    
                    OutlinedTextField(
                        value = uiState.user.weight?.toString() ?: "",
                        onValueChange = { viewModel.updateWeight(it.toFloatOrNull()) },
                        label = { Text("Weight") },
                        modifier = Modifier.weight(1f),
                        keyboardOptions = androidx.compose.foundation.text.KeyboardOptions(
                            keyboardType = androidx.compose.ui.text.input.KeyboardType.Decimal
                        )
                    )
                }
            }
            
            // Goals Section
            OnboardingSection(title = "Your Goals") {
                // Goal Type
                Text(
                    text = "Goal Type",
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.Medium
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    GoalType.values().forEach { goalType ->
                        FilterChip(
                            selected = uiState.user.goalType == goalType,
                            onClick = { viewModel.updateGoalType(goalType) },
                            label = { Text(goalType.name.replace("_", " ")) }
                        )
                    }
                }
                
                Spacer(modifier = Modifier.height(16.dp))
                
                // Goal Weight
                OutlinedTextField(
                    value = uiState.user.goalWeight?.toString() ?: "",
                    onValueChange = { viewModel.updateGoalWeight(it.toFloatOrNull()) },
                    label = { Text("Goal Weight") },
                    modifier = Modifier.fillMaxWidth(),
                    keyboardOptions = androidx.compose.foundation.text.KeyboardOptions(
                        keyboardType = androidx.compose.ui.text.input.KeyboardType.Decimal
                    )
                )
            }
            
            // Diet Preferences Section
            OnboardingSection(title = "Diet Preferences") {
                // Preferred Diet
                Text(
                    text = "Preferred Diet",
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.Medium
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    DietType.values().forEach { dietType ->
                        FilterChip(
                            selected = uiState.user.preferredDiet == dietType,
                            onClick = { viewModel.updatePreferredDiet(dietType) },
                            label = { Text(dietType.name.replace("_", " ")) }
                        )
                    }
                }
                
                Spacer(modifier = Modifier.height(16.dp))
                
                // Food Dislikes
                OutlinedTextField(
                    value = uiState.foodDislikesInput,
                    onValueChange = { viewModel.updateFoodDislikesInput(it) },
                    label = { Text("Food Dislikes (comma separated)") },
                    modifier = Modifier.fillMaxWidth()
                )
                
                Spacer(modifier = Modifier.height(16.dp))
                
                // Allergies
                OutlinedTextField(
                    value = uiState.allergiesInput,
                    onValueChange = { viewModel.updateAllergiesInput(it) },
                    label = { Text("Allergies (comma separated)") },
                    modifier = Modifier.fillMaxWidth()
                )
            }
            
            // Activity Level Section
            OnboardingSection(title = "Activity Level") {
                Text(
                    text = "How active are you?",
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.Medium
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    ActivityLevel.values().forEach { activityLevel ->
                        FilterChip(
                            selected = uiState.user.activityLevel == activityLevel,
                            onClick = { viewModel.updateActivityLevel(activityLevel) },
                            label = { Text(activityLevel.name.replace("_", " ")) }
                        )
                    }
                }
            }
            
            // Units Section
            OnboardingSection(title = "Units") {
                Text(
                    text = "Preferred Units",
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.Medium
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Units.values().forEach { units ->
                        FilterChip(
                            selected = uiState.user.units == units,
                            onClick = { viewModel.updateUnits(units) },
                            label = { Text(units.name.replace("_", " ")) }
                        )
                    }
                }
            }
            
            // Complete Button
            Button(
                onClick = { viewModel.completeOnboarding() },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = AccentOrange
                ),
                enabled = uiState.isFormValid
            ) {
                Text(
                    text = "Complete Setup",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Medium
                )
            }
            
            // Skip Button
            TextButton(
                onClick = { viewModel.skipOnboarding() },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Skip for now")
            }
        }
    }
}

@Composable
private fun OnboardingSection(
    title: String,
    content: @Composable () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        )
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = title,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface
            )
            content()
        }
    }
}
