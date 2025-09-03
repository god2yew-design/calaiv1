package com.example.calaiv1.data.local

import androidx.room.TypeConverter
import com.example.calaiv1.domain.model.*
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {
    private val gson = Gson()
    
    @TypeConverter
    fun fromSex(sex: Sex?): String? = sex?.name
    
    @TypeConverter
    fun toSex(sex: String?): Sex? = sex?.let { Sex.valueOf(it) }
    
    @TypeConverter
    fun fromGoalType(goalType: GoalType?): String? = goalType?.name
    
    @TypeConverter
    fun toGoalType(goalType: String?): GoalType? = goalType?.let { GoalType.valueOf(it) }
    
    @TypeConverter
    fun fromDietType(dietType: DietType?): String? = dietType?.name
    
    @TypeConverter
    fun toDietType(dietType: String?): DietType? = dietType?.let { DietType.valueOf(it) }
    
    @TypeConverter
    fun fromActivityLevel(activityLevel: ActivityLevel?): String? = activityLevel?.name
    
    @TypeConverter
    fun toActivityLevel(activityLevel: String?): ActivityLevel? = activityLevel?.let { ActivityLevel.valueOf(it) }
    
    @TypeConverter
    fun fromUnits(units: Units): String = units.name
    
    @TypeConverter
    fun toUnits(units: String): Units = Units.valueOf(units)
    
    @TypeConverter
    fun fromMealType(mealType: MealType): String = mealType.name
    
    @TypeConverter
    fun toMealType(mealType: String): MealType = MealType.valueOf(mealType)
    
    @TypeConverter
    fun fromFoodCategory(foodCategory: FoodCategory): String = foodCategory.name
    
    @TypeConverter
    fun toFoodCategory(foodCategory: String): FoodCategory = FoodCategory.valueOf(foodCategory)
    
    @TypeConverter
    fun fromStringList(value: List<String>): String = gson.toJson(value)
    
    @TypeConverter
    fun toStringList(value: String): List<String> {
        val listType = object : TypeToken<List<String>>() {}.type
        return gson.fromJson(value, listType) ?: emptyList()
    }
    
    @TypeConverter
    fun fromFoodItemList(value: List<FoodItem>): String = gson.toJson(value)
    
    @TypeConverter
    fun toFoodItemList(value: String): List<FoodItem> {
        val listType = object : TypeToken<List<FoodItem>>() {}.type
        return gson.fromJson(value, listType) ?: emptyList()
    }
}
