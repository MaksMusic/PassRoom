package com.example.pass.room.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity (tableName = "category" )
data class Category  (
    @PrimaryKey(autoGenerate = true) val id:Int,
    @ColumnInfo(name = "name") var name:String,
    //@ColumnInfo(name = "listpass") var listpass: List<PassItem>
    )

{

}