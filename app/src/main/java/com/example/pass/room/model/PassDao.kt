package com.example.pass.room.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.pass.room.model.PassItem

@Dao
interface PassDao {
    @Insert
    suspend fun insertPassItem(passItem: PassItem)

    @Query("SELECT * FROM passItem")
    suspend fun getPassItemList() : List<PassItem>
}