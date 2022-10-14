package com.example.pass.room.model

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.pass.room.model.PassItem

@Dao
interface PassDao {
    @Insert
    suspend fun insertPassItem(passItem: PassItem)

    @Query("SELECT * FROM passItem")
    suspend fun getPassItemList() : List<PassItem>

    @Query ("SELECT* FROM passItem WHERE id = :id")
    suspend fun getPassItem(id:Long):PassItem

    @Update
    suspend fun update(passItem: PassItem)

    @Delete
    suspend fun deleteItem(passItem: PassItem)


}