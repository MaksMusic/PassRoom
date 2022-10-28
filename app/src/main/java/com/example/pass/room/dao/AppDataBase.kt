package com.example.pass.room.dao

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.pass.room.model.Category
import com.example.pass.room.model.CategoryDao
import com.example.pass.room.model.PassDao
import com.example.pass.room.model.PassItem

@Database(entities = [PassItem::class, Category::class], version = 1)
abstract  class AppDataBase : RoomDatabase() {
    abstract fun passDao(): PassDao
    abstract fun categoryDao():CategoryDao

}