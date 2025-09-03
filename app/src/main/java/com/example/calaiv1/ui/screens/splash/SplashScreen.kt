package com.example.calaiv1.ui.screens.splash

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun SplashScreen(navController: NavController) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "🚀 CalAI App",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Welcome to your AI Nutrition Companion!",
            fontSize = 18.sp
        )

        Spacer(modifier = Modifier.height(32.dp))

        Button(onClick = { navController.navigate("main") }) {
            Text("Enter App")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = { navController.navigate("onboarding") }) {
            Text("Setup Profile")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = { navController.navigate("test") }) {
            Text("Test Navigation")
        }
    }
}
