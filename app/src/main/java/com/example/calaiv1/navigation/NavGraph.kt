package com.example.calaiv1.navigation

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.calaiv1.ui.screens.onboarding.OnboardingScreen
import com.example.calaiv1.ui.screens.main.MainScreen

@Composable
fun NavGraph(
    navController: NavHostController,
    startDestination: String = "test" // Start with test screen to bypass all issues
) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(Screen.Onboarding.route) {
            OnboardingScreen(navController)
        }

        composable(Screen.Main.route) {
            MainScreen(navController)
        }

        // Test route for debugging
        composable("test") {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text("Navigation Test Successful!", fontSize = 24.sp)
                Button(onClick = { navController.navigate("main") }) {
                    Text("Go to Main")
                }
            }
        }
    }
}

sealed class Screen(val route: String) {
    object Splash : Screen("splash")
    object Onboarding : Screen("onboarding")
    object Main : Screen("main")
}
