package com.example.pass.PrefManager

import android.content.Context
import android.content.SharedPreferences

class PrefManager(context: Context) {
    private val sharedPreferenc : SharedPreferences = context.getSharedPreferences("passreg",Context.MODE_PRIVATE)

    fun putString(key: String?,value:String?){
        val editor = sharedPreferenc.edit()
        editor.putString(key,value)
        editor.apply()
    }

    fun getString(key: String?) : String?{
        return sharedPreferenc.getString(key,null)
    }
}