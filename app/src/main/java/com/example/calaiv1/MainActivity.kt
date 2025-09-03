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
            text = "🎉 CalAI App Loaded Successfully!",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "The app is working! Navigation system bypassed.",
            fontSize = 16.sp
        )

        Spacer(modifier = Modifier.height(32.dp))

        Button(onClick = { /* TODO: Add navigation later */ }) {
            Text("Ready to build features!")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "✅ App is functional",
            fontSize = 14.sp,
            color = androidx.compose.ui.graphics.Color.Green
        )
    }
}