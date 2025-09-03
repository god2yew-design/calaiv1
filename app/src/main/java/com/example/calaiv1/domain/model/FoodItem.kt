package com.example.calaiv1.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "food_items")
data class FoodItem(
    @PrimaryKey val id: String,
    val name: String,
    val brand: String? = null,
    val barcode: String? = null,
    val servingSize: Float,
    val servingUnit: String,
    val calories: Int,
    val protein: Float,
    val carbs: Float,
    val fat: Float,
    val fiber: Float,
    val sugar: Float,
    val imageUrl: String? = null,
    val category: FoodCategory = FoodCategory.OTHER
)

enum class FoodCategory {
    FRUITS, VEGETABLES, GRAINS, PROTEIN, DAIRY, SNACKS, BEVERAGES, OTHER
}
