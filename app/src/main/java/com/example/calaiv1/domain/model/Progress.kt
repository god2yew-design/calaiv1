package com.example.calaiv1.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "progress")
data class Progress(
    @PrimaryKey val id: String,
    val userId: String,
    val date: Long,
    val weight: Float? = null,
    val bodyFatPercentage: Float? = null,
    val muscleMass: Float? = null,
    val waistCircumference: Float? = null,
    val hipCircumference: Float? = null,
    val chestCircumference: Float? = null,
    val armCircumference: Float? = null,
    val thighCircumference: Float? = null,
    val notes: String? = null,
    val photoUrl: String? = null,
    val createdAt: Long = System.currentTimeMillis()
)
