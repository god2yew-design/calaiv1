package com.example.calaiv1.data.local

import androidx.room.*
import com.example.calaiv1.domain.model.*
import kotlinx.coroutines.flow.Flow

@Database(
    entities = [
        User::class,
        Meal::class,
        FoodItem::class,
        Progress::class,
        DailyNutrition::class
    ],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun mealDao(): MealDao
    abstract fun foodItemDao(): FoodItemDao
    abstract fun progressDao(): ProgressDao
    abstract fun dailyNutritionDao(): DailyNutritionDao
}

@Dao
interface UserDao {
    @Query("SELECT * FROM users WHERE id = :userId")
    fun getUserById(userId: String): Flow<User?>
    
    @Query("SELECT * FROM users WHERE email = :email")
    suspend fun getUserByEmail(email: String): User?
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: User)
    
    @Update
    suspend fun updateUser(user: User)
    
    @Delete
    suspend fun deleteUser(user: User)
}

@Dao
interface MealDao {
    @Query("SELECT * FROM meals WHERE userId = :userId AND date = :date ORDER BY type")
    fun getMealsByDate(userId: String, date: Long): Flow<List<Meal>>
    
    @Query("SELECT * FROM meals WHERE userId = :userId ORDER BY date DESC")
    fun getAllMeals(userId: String): Flow<List<Meal>>
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMeal(meal: Meal)
    
    @Update
    suspend fun updateMeal(meal: Meal)
    
    @Delete
    suspend fun deleteMeal(meal: Meal)
}

@Dao
interface FoodItemDao {
    @Query("SELECT * FROM food_items WHERE category = :category")
    fun getFoodItemsByCategory(category: FoodCategory): Flow<List<FoodItem>>
    
    @Query("SELECT * FROM food_items WHERE name LIKE '%' || :query || '%'")
    fun searchFoodItems(query: String): Flow<List<FoodItem>>
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFoodItem(foodItem: FoodItem)
    
    @Update
    suspend fun updateFoodItem(foodItem: FoodItem)
    
    @Delete
    suspend fun deleteFoodItem(foodItem: FoodItem)
}

@Dao
interface ProgressDao {
    @Query("SELECT * FROM progress WHERE userId = :userId ORDER BY date DESC")
    fun getProgressHistory(userId: String): Flow<List<Progress>>
    
    @Query("SELECT * FROM progress WHERE userId = :userId ORDER BY date DESC LIMIT 1")
    fun getLatestProgress(userId: String): Flow<Progress?>
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProgress(progress: Progress)
    
    @Update
    suspend fun updateProgress(progress: Progress)
    
    @Delete
    suspend fun deleteProgress(progress: Progress)
}

@Dao
interface DailyNutritionDao {
    @Query("SELECT * FROM daily_nutrition WHERE userId = :userId AND date = :date")
    fun getDailyNutrition(userId: String, date: Long): Flow<DailyNutrition?>
    
    @Query("SELECT * FROM daily_nutrition WHERE userId = :userId ORDER BY date DESC LIMIT 30")
    fun getLast30DaysNutrition(userId: String): Flow<List<DailyNutrition>>
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDailyNutrition(dailyNutrition: DailyNutrition)
    
    @Update
    suspend fun updateDailyNutrition(dailyNutrition: DailyNutrition)
    
    @Delete
    suspend fun deleteDailyNutrition(dailyNutrition: DailyNutrition)
}
