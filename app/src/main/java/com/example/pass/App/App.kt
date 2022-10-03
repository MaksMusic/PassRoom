package com.example.pass.App

import android.app.Application
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.pass.room.dao.AppDataBase

class App :Application(){
  private lateinit var appDatabase: AppDataBase

    override fun onCreate() {
        super.onCreate()
        appDatabase = Room.databaseBuilder(applicationContext, AppDataBase::class.java,"pass_database").build()

    }
    fun getDataBase(): AppDataBase {
        return appDatabase
    }

}