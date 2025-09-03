package com.example.calaiv1.ui.screens.splash

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.calaiv1.ui.theme.AccentOrange
import kotlinx.coroutines.delay
import android.util.Log

@Composable
fun SplashScreen(navController: NavController) {
    var startAnimation by remember { mutableStateOf(false) }

    val scale by animateFloatAsState(
        targetValue = if (startAnimation) 1f else 0.3f,
        animationSpec = tween(durationMillis = 1000),
        label = "scale"
    )

    val alpha by animateFloatAsState(
        targetValue = if (startAnimation) 1f else 0f,
        animationSpec = tween(durationMillis = 1000),
        label = "alpha"
    )

    // Debug: Navigate immediately to test if navigation works
    LaunchedEffect(Unit) {
        Log.d("SplashScreen", "SplashScreen launched")
        startAnimation = true
        Log.d("SplashScreen", "Animation started")
    }

    // Try using a simple state-based navigation
    var shouldNavigate by remember { mutableStateOf(false) }

    // Trigger navigation after a short delay
    LaunchedEffect(Unit) {
        delay(2000L) // 2 seconds
        Log.d("SplashScreen", "Setting shouldNavigate to true")
        shouldNavigate = true
    }

    // Handle navigation when state changes
    LaunchedEffect(shouldNavigate) {
        if (shouldNavigate) {
            Log.d("SplashScreen", "Navigation triggered by state change")
            try {
                navController.navigate("main") {
                    popUpTo("splash") { inclusive = true }
                }
                Log.d("SplashScreen", "Navigation to main successful")
            } catch (e: Exception) {
                Log.e("SplashScreen", "Navigation failed", e)
            }
        }
    }
    
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Simple text for debugging
        Text("Splash Screen Debug", fontSize = 24.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(16.dp))
        Text("Animation started: $startAnimation", fontSize = 16.sp)
        Text("Should navigate: $shouldNavigate", fontSize = 16.sp)

        Spacer(modifier = Modifier.height(32.dp))

        // Manual navigation button for testing
        Button(onClick = {
            Log.d("SplashScreen", "Manual navigation button clicked")
            try {
                navController.navigate("main") {
                    popUpTo("splash") { inclusive = true }
                }
                Log.d("SplashScreen", "Manual navigation successful")
            } catch (e: Exception) {
                Log.e("SplashScreen", "Manual navigation failed", e)
            }
        }) {
            Text("Navigate to Main (Manual Test)")
        }
    }
}
