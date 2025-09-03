package com.example.calaiv1.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "daily_nutrition")
data class DailyNutrition(
    @PrimaryKey val id: String,
    val userId: String,
    val date: Long,
    val totalCalories: Int = 0,
    val totalProtein: Float = 0f,
    val totalCarbs: Float = 0f,
    val totalFat: Float = 0f,
    val totalFiber: Float = 0f,
    val totalSugar: Float = 0f,
    val waterIntake: Float = 0f,
    val exerciseMinutes: Int = 0,
    val caloriesBurned: Int = 0,
    val createdAt: Long = System.currentTimeMillis()
)
