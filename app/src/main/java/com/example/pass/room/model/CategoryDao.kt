package com.example.pass.room.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface CategoryDao {

    @Insert
    suspend fun insertPassItem(category: Category)

   // @Query("SELECT * FROM category")
   // suspend fun getPassItemList() : List<Category>




}