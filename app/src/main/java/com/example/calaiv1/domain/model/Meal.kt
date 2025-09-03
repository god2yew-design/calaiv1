package com.example.calaiv1.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "meals")
data class Meal(
    @PrimaryKey val id: String,
    val userId: String,
    val name: String,
    val type: MealType,
    val date: Long,
    val foodItems: List<FoodItem> = emptyList(),
    val totalCalories: Int = 0,
    val totalProtein: Float = 0f,
    val totalCarbs: Float = 0f,
    val totalFat: Float = 0f,
    val totalFiber: Float = 0f,
    val totalSugar: Float = 0f,
    val notes: String? = null,
    val createdAt: Long = System.currentTimeMillis()
)

enum class MealType {
    BREAKFAST, LUNCH, DINNER, SNACK
}
