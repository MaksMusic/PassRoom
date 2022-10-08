package com.example.pass.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.example.pass.App.App
import com.example.pass.databinding.ActivityAddItemBinding
import com.example.pass.room.model.PassDao
import com.example.pass.room.model.PassItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ActivityAddItem : AppCompatActivity() {
    lateinit var binding: com.example.pass.databinding.ActivityAddItemBinding
    lateinit var passDao: PassDao
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddItemBinding.inflate(layoutInflater)

        setContentView(binding.root)
        passDao = (application as App).getDataBase().passDao()
        addPassBtn()
        exit()


    }

    fun exit() {
        binding.exit.setOnClickListener() {
            finish()
        }
    }

    fun addPassBtn() {
        binding.btnAdd.setOnClickListener() {
            val name: String = binding.textNameItem.text.toString() ?: ""
            val login: String = binding.textLoginItem.text.toString() ?: ""
            val pass: String = binding.textPassItem.text.toString() ?: ""
            val email: String = binding.textEmailItem.text.toString() ?: ""
            val notes: String = binding.textNotesItem.text.toString() ?: ""

            val item = PassItem(0, login, name, pass, email, notes)
            lifecycleScope.launch(Dispatchers.IO) {
                passDao.insertPassItem(item)
                withContext(Dispatchers.Main) {
                    finish()
                }

            }
        }
    }


}