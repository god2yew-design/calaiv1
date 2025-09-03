package com.example.calaiv1.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.calaiv1.ui.screens.splash.SplashScreen
import com.example.calaiv1.ui.screens.onboarding.OnboardingScreen
import com.example.calaiv1.ui.screens.main.MainScreen

@Composable
fun NavGraph(
    navController: NavHostController,
    startDestination: String = Screen.Splash.route
) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(Screen.Splash.route) {
            SplashScreen(navController = navController)
        }
        
        composable(Screen.Onboarding.route) {
            OnboardingScreen(navController)
        }
        
        composable(Screen.Main.route) {
            MainScreen(navController)
        }
    }
}

sealed class Screen(val route: String) {
    object Splash : Screen("splash")
    object Onboarding : Screen("onboarding")
    object Main : Screen("main")
}
