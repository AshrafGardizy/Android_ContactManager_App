package com.example.contactmanagerapp.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface UsersDAO {

    @Insert
    suspend fun insertUsers(user:Users):Long

    @Update
    suspend fun updateUsers(user:Users)

    @Delete
    suspend fun deleteUsers(user:Users)

    //Custom Query
    @Query("delete  from users")
    suspend fun deleteAll()

    @Query("Select * from users")
    fun getAllUsersFromDB():LiveData<List<Users>>
}