package com.example.calaiv1.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class User(
    @PrimaryKey val id: String,
    val email: String,
    val name: String? = null,
    val sex: Sex? = null,
    val age: Int? = null,
    val height: Float? = null,
    val weight: Float? = null,
    val goalWeight: Float? = null,
    val goalType: GoalType? = null,
    val preferredDiet: DietType? = null,
    val goalDate: Long? = null,
    val foodDislikes: List<String> = emptyList(),
    val allergies: List<String> = emptyList(),
    val activityLevel: ActivityLevel? = null,
    val healthConditions: List<String> = emptyList(),
    val units: Units = Units.IMPERIAL,
    val isOnboardingCompleted: Boolean = false,
    val isPremium: Boolean = false,
    val createdAt: Long = System.currentTimeMillis(),
    val updatedAt: Long = System.currentTimeMillis()
)

enum class Sex {
    MALE, FEMALE, OTHER
}

enum class GoalType {
    FAT_LOSS, MUSCLE_GAIN, MAINTAIN
}

enum class DietType {
    LOW_CARB, KETO, BALANCED, MEDITERRANEAN
}

enum class ActivityLevel {
    SEDENTARY, MODERATE, ACTIVE
}

enum class Units {
    IMPERIAL, METRIC
}
