package com.example.calaiv1.data.repository

import com.example.calaiv1.data.local.UserDao
import com.example.calaiv1.domain.model.User
import kotlinx.coroutines.flow.Flow
import javax.inject.Singleton

@Singleton
class UserRepository(
    private val userDao: UserDao
) {
    fun getUserById(userId: String): Flow<User?> = userDao.getUserById(userId)
    
    suspend fun getUserByEmail(email: String): User? = userDao.getUserByEmail(email)
    
    suspend fun insertUser(user: User) = userDao.insertUser(user)
    
    suspend fun updateUser(user: User) = userDao.updateUser(user)
    
    suspend fun deleteUser(user: User) = userDao.deleteUser(user)
}
