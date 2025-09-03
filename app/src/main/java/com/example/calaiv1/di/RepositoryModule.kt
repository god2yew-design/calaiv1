package com.example.calaiv1.di

import com.example.calaiv1.data.repository.UserRepository
import com.example.calaiv1.data.repository.MealRepository
import com.example.calaiv1.data.local.UserDao
import com.example.calaiv1.data.local.MealDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    
    @Provides
    @Singleton
    fun provideUserRepository(userDao: UserDao): UserRepository = UserRepository(userDao)
    
    @Provides
    @Singleton
    fun provideMealRepository(mealDao: MealDao): MealRepository = MealRepository(mealDao)
}
