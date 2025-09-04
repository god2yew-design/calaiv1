package com.example.calaiv1.ui.screens.onboarding

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.calaiv1.domain.model.*
import com.example.calaiv1.ui.theme.AccentOrange
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OnboardingScreen(
    navController: NavController,
    viewModel: OnboardingViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    val pagerState = rememberPagerState(pageCount = { 5 })
    val scope = rememberCoroutineScope()

    val onboardingPages = listOf(
        OnboardingPage.Welcome,
        OnboardingPage.BasicInfo,
        OnboardingPage.Goals,
        OnboardingPage.Diet,
        OnboardingPage.Activity
    )

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
                title = { Text("Setup Your Profile") },
                actions = {
                    TextButton(onClick = {
                        viewModel.skipOnboarding()
                    }) {
                        Text("Skip", color = AccentOrange)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surface
                )
            )
        },
        bottomBar = {
            OnboardingBottomBar(
                currentPage = pagerState.currentPage,
                totalPages = onboardingPages.size,
                onPrevious = {
                    scope.launch {
                        if (pagerState.currentPage > 0) {
                            pagerState.animateScrollToPage(pagerState.currentPage - 1)
                        }
                    }
                },
                onNext = {
                    scope.launch {
                        if (pagerState.currentPage < onboardingPages.size - 1) {
                            pagerState.animateScrollToPage(pagerState.currentPage + 1)
                        } else {
                            viewModel.completeOnboarding()
                        }
                    }
                },
                canProceed = when (pagerState.currentPage) {
                    0 -> true // Welcome page
                    1 -> uiState.user.sex != null && uiState.user.age != null &&
                         uiState.user.height != null && uiState.user.weight != null
                    2 -> uiState.user.goalType != null && uiState.user.goalWeight != null
                    3 -> uiState.user.preferredDiet != null
                    4 -> uiState.user.activityLevel != null
                    else -> true
                }
            )
        }
    ) { paddingValues ->
        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) { page ->
            when (onboardingPages[page]) {
                OnboardingPage.Welcome -> WelcomePage()
                OnboardingPage.BasicInfo -> BasicInfoPage(uiState, viewModel)
                OnboardingPage.Goals -> GoalsPage(uiState, viewModel)
                OnboardingPage.Diet -> DietPage(uiState, viewModel)
                OnboardingPage.Activity -> ActivityPage(uiState, viewModel)
            }
        }
    }
}

// Onboarding Page Types
enum class OnboardingPage {
    Welcome, BasicInfo, Goals, Diet, Activity
}

// Welcome Page
@Composable
fun WelcomePage() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "👋 Welcome to CalAI!",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.onBackground
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Let's set up your personalized nutrition experience",
            fontSize = 18.sp,
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        Spacer(modifier = Modifier.height(32.dp))

        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(
                containerColor = AccentOrange.copy(alpha = 0.1f)
            )
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = "✨ What you'll get:",
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.onBackground
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text("• Personalized meal recommendations", color = MaterialTheme.colorScheme.onSurfaceVariant)
                Text("• Smart food tracking", color = MaterialTheme.colorScheme.onSurfaceVariant)
                Text("• Progress monitoring", color = MaterialTheme.colorScheme.onSurfaceVariant)
                Text("• AI-powered insights", color = MaterialTheme.colorScheme.onSurfaceVariant)
            }
        }
    }
}

// Basic Info Page
@Composable
fun BasicInfoPage(uiState: OnboardingUiState, viewModel: OnboardingViewModel) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        Text(
            text = "Tell us about yourself",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onBackground
        )

        // Sex Selection
        OnboardingSection(title = "What's your sex?") {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Sex.values().forEach { sex ->
                    FilterChip(
                        selected = uiState.user.sex == sex,
                        onClick = { viewModel.updateSex(sex) },
                        label = { Text(sex.name.replace("_", " ")) },
                        modifier = Modifier.weight(1f)
                    )
                }
            }
        }

        // Age Input
        OnboardingSection(title = "How old are you?") {
            OutlinedTextField(
                value = uiState.user.age?.toString() ?: "",
                onValueChange = { viewModel.updateAge(it.toIntOrNull()) },
                label = { Text("Age") },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = androidx.compose.foundation.text.KeyboardOptions(
                    keyboardType = androidx.compose.ui.text.input.KeyboardType.Number
                )
            )
        }

        // Height and Weight
        OnboardingSection(title = "Your measurements") {
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
    }
}

// Goals Page
@Composable
fun GoalsPage(uiState: OnboardingUiState, viewModel: OnboardingViewModel) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        Text(
            text = "What's your goal?",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onBackground
        )

        // Goal Type
        OnboardingSection(title = "What are you aiming for?") {
            Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                GoalType.values().forEach { goalType ->
                    FilterChip(
                        selected = uiState.user.goalType == goalType,
                        onClick = { viewModel.updateGoalType(goalType) },
                        label = { Text(goalType.name.replace("_", " ")) }
                    )
                }
            }
        }

        // Goal Weight
        OnboardingSection(title = "Target weight (optional)") {
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
    }
}

// Diet Page
@Composable
fun DietPage(uiState: OnboardingUiState, viewModel: OnboardingViewModel) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        Text(
            text = "Diet preferences",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onBackground
        )

        // Preferred Diet
        OnboardingSection(title = "What's your preferred diet?") {
            Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                DietType.values().forEach { dietType ->
                    FilterChip(
                        selected = uiState.user.preferredDiet == dietType,
                        onClick = { viewModel.updatePreferredDiet(dietType) },
                        label = { Text(dietType.name.replace("_", " ")) }
                    )
                }
            }
        }

        // Food Dislikes
        OnboardingSection(title = "Any foods you dislike?") {
            OutlinedTextField(
                value = uiState.foodDislikesInput,
                onValueChange = { viewModel.updateFoodDislikesInput(it) },
                label = { Text("Foods to avoid (comma separated)") },
                modifier = Modifier.fillMaxWidth(),
                minLines = 2
            )
        }

        // Allergies
        OnboardingSection(title = "Any allergies?") {
            OutlinedTextField(
                value = uiState.allergiesInput,
                onValueChange = { viewModel.updateAllergiesInput(it) },
                label = { Text("Allergies (comma separated)") },
                modifier = Modifier.fillMaxWidth(),
                minLines = 2
            )
        }
    }
}

// Activity Page
@Composable
fun ActivityPage(uiState: OnboardingUiState, viewModel: OnboardingViewModel) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        Text(
            text = "Activity level",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onBackground
        )

        OnboardingSection(title = "How active are you?") {
            Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                ActivityLevel.values().forEach { activityLevel ->
                    FilterChip(
                        selected = uiState.user.activityLevel == activityLevel,
                        onClick = { viewModel.updateActivityLevel(activityLevel) },
                        label = { Text(activityLevel.name.replace("_", " ")) }
                    )
                }
            }
        }

        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(
                containerColor = AccentOrange.copy(alpha = 0.1f)
            )
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = "🎉 You're all set!",
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onBackground
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Your profile is ready. Let's start your nutrition journey!",
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}

// Onboarding Section Component
@Composable
fun OnboardingSection(title: String, content: @Composable () -> Unit) {
    Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
        Text(
            text = title,
            fontSize = 18.sp,
            fontWeight = FontWeight.SemiBold,
            color = MaterialTheme.colorScheme.onBackground
        )
        content()
    }
}

// Bottom Navigation Bar
@Composable
fun OnboardingBottomBar(
    currentPage: Int,
    totalPages: Int,
    onPrevious: () -> Unit,
    onNext: () -> Unit,
    canProceed: Boolean
) {
    Surface(
        tonalElevation = 4.dp,
        shadowElevation = 4.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Page indicators
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                repeat(totalPages) { index ->
                    Box(
                        modifier = Modifier
                            .size(8.dp)
                            .background(
                                color = if (index == currentPage)
                                    AccentOrange
                                else
                                    AccentOrange.copy(alpha = 0.3f),
                                shape = androidx.compose.foundation.shape.CircleShape
                            )
                    )
                }
            }

            Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                // Previous button
                if (currentPage > 0) {
                    OutlinedButton(onClick = onPrevious) {
                        Text("Previous")
                    }
                }

                // Next/Complete button
                Button(
                    onClick = onNext,
                    enabled = canProceed,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = AccentOrange,
                        disabledContainerColor = AccentOrange.copy(alpha = 0.3f)
                    )
                ) {
                    Text(if (currentPage == totalPages - 1) "Complete" else "Next")
                }
            }
        }
    }
}
