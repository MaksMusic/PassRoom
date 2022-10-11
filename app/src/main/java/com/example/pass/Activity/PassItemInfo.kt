package com.example.pass.Activity

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.lifecycle.lifecycleScope
import com.example.pass.App.App
import com.example.pass.R
import com.example.pass.databinding.ActivityPassItemInfoBinding
import com.example.pass.databinding.PassItemBinding
import com.example.pass.room.model.PassDao
import com.example.pass.room.model.PassItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.time.Duration.Companion.seconds


class PassItemInfo : AppCompatActivity() {
    private lateinit var binding: ActivityPassItemInfoBinding
    private var id: Long? = null
    lateinit var passDao: PassDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPassItemInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        passDao = (application as App).getDataBase().passDao()
        id = intent.extras?.getLong("id")

        initText()
        btnExit()
        btnSave()
        btnEditText()
binding.textNameItem.isClickable = false

    }

    private fun btnEditText(){
        binding.btnEdit.setOnClickListener(){
            booleanEditText(true)
        }
    }



    private fun booleanEditText(boolean: Boolean) {
        binding.btnEdit.setOnClickListener(){
            binding.textEmailItem.isClickable = boolean
            binding.textEmailItem.isCursorVisible = boolean
            binding.textEmailItem.isFocusable = boolean
            binding.textEmailItem.isLongClickable = boolean

            binding.textLoginItem.isClickable = boolean
            binding.textLoginItem.isCursorVisible = boolean
            binding.textLoginItem.isFocusable = boolean
            binding.textLoginItem.isLongClickable = boolean

            binding.textNameItem.isClickable = boolean
            binding.textNameItem.isCursorVisible = boolean
            binding.textNameItem.isFocusable = boolean
            binding.textNameItem.isLongClickable = boolean

            binding.textPassItem.isClickable = boolean
            binding.textPassItem.isCursorVisible = boolean
           binding.textPassItem.isFocusable = boolean
            binding.textPassItem.isLongClickable = boolean

            binding.textNotesItem.isClickable = boolean
            binding.textNotesItem.isCursorVisible = boolean
            binding.textNotesItem.isFocusable = boolean
            binding.textNotesItem.isLongClickable = boolean

        }
    }

    private fun btnSave() {
        binding.btnSave.setOnClickListener() {
            if (id != null) {
                lifecycleScope.launch(Dispatchers.IO) {

                    var name = binding.textNameItem.text.toString()
                    var login = binding.textLoginItem.text.toString()
                    var pass = binding.textPassItem.text.toString()
                    var email = binding.textEmailItem.text.toString()
                    var notes = binding.textNotesItem.text.toString()

                    var itemPass =
                        PassItem(passDao.getPassItem(id!!).id, login, name, pass, email, notes)
                    passDao.update(itemPass)
                    finish()
                }
            }
        }
    }


    private fun btnExit() {
        binding.btnToolBarExit.setOnClickListener() {
            finish()
        }
    }


    fun initText() {
        lifecycleScope.launch(Dispatchers.IO) {
            if (id != null) {
                var itemPass = passDao.getPassItem(id!!)
                var (id, login, name, pass, email, notes) = itemPass

                withContext(Dispatchers.Main) {
                    binding.textNameItem.setText(name.toString())
                    binding.textLoginItem.setText(login.toString())
                    binding.textPassItem.setText(pass.toString())
                    binding.textEmailItem.setText(email.toString())
                    binding.textNotesItem.setText(notes.toString())
                }
            }
        }
    }
}