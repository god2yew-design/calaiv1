package com.example.calaiv1.data.repository

import com.example.calaiv1.data.local.MealDao
import com.example.calaiv1.domain.model.Meal
import kotlinx.coroutines.flow.Flow
import javax.inject.Singleton

@Singleton
class MealRepository(
    private val mealDao: MealDao
) {
    fun getMealsByDate(userId: String, date: Long): Flow<List<Meal>> = mealDao.getMealsByDate(userId, date)
    
    fun getAllMeals(userId: String): Flow<List<Meal>> = mealDao.getAllMeals(userId)
    
    suspend fun insertMeal(meal: Meal) = mealDao.insertMeal(meal)
    
    suspend fun updateMeal(meal: Meal) = mealDao.updateMeal(meal)
    
    suspend fun deleteMeal(meal: Meal) = mealDao.deleteMeal(meal)
}
