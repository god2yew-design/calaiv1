package com.example.calaiv1.di

import android.content.Context
import androidx.room.Room
import com.example.calaiv1.data.local.AppDatabase
import com.example.calaiv1.data.local.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    
    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "calai_database"
        ).build()
    }
    
    @Provides
    fun provideUserDao(database: AppDatabase): UserDao = database.userDao()
    
    @Provides
    fun provideMealDao(database: AppDatabase): MealDao = database.mealDao()
    
    @Provides
    fun provideFoodItemDao(database: AppDatabase): FoodItemDao = database.foodItemDao()
    
    @Provides
    fun provideProgressDao(database: AppDatabase): ProgressDao = database.progressDao()
    
    @Provides
    fun provideDailyNutritionDao(database: AppDatabase): DailyNutritionDao = database.dailyNutritionDao()
}
