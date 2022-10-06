package com.example.pass.room.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "passItem")
data class PassItem (
    @PrimaryKey(autoGenerate = true) val id:Int,
    @ColumnInfo(name = "login") val login:String,
    @ColumnInfo(name = "name") val name:String,
    @ColumnInfo(name = "password") val password:String,
    @ColumnInfo(name = "email") val email:String,
    @ColumnInfo(name = "notes") val notes:String,
        ){
}