package com.example.contactmanagerapp.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Users::class], version = 1)
abstract class UsersDatabase :RoomDatabase() {

    abstract val usersDAO:UsersDAO

    //Singleton (Only create one object for database connection
    companion object{
        @Volatile
        private var INSTANCE:UsersDatabase ?= null
        fun getInstance(context:Context):UsersDatabase
        {
            synchronized(this){
                var instance = INSTANCE
                if(instance == null){
                    instance = Room.databaseBuilder(context,UsersDatabase::class.java,"users_db").build()
                }
                return instance
            }
        }
    }
}