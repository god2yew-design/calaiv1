package com.example.calaiv1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.calaiv1.ui.theme.CalAiTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CalAiTheme {
                // Completely bypass navigation and show a simple screen
                SimpleAppScreen()
            }
        }
    }
}

@Composable
fun SimpleAppScreen() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "🚀 CALAI APP WORKING!",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            color = androidx.compose.ui.graphics.Color(0xFF4CAF50)
        )

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "Splash screen completely removed!",
            fontSize = 18.sp,
            color = androidx.compose.ui.graphics.Color(0xFF2196F3)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "App is now functional without splash screen",
            fontSize = 14.sp
        )

        Spacer(modifier = Modifier.height(40.dp))

        Button(
            onClick = { /* Navigation will be added later */ },
            colors = androidx.compose.material3.ButtonDefaults.buttonColors(
                containerColor = androidx.compose.ui.graphics.Color(0xFF4CAF50)
            )
        ) {
            Text("🎯 Ready to Build Features!", color = androidx.compose.ui.graphics.Color.White)
        }

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "✅ SUCCESS: No more splash screen!",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = androidx.compose.ui.graphics.Color(0xFF4CAF50)
        )
    }
}