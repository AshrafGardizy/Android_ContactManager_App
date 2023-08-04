package com.example.contactmanagerapp.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class Users(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "user_id")
    val id:Int,

    @ColumnInfo(name = "user_name")
    var name:String,

    @ColumnInfo(name = "user_email")
    var email:String
)
