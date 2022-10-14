package com.example.pass.Activity

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
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

binding.textNameItem.isClickable = false




    }

    private fun itemDelete(){
        lifecycleScope.launch(Dispatchers.IO){
            var passItem = passDao.getPassItem(id!!)
            passDao.deleteItem(passItem)
            finish()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
       return when(item.itemId){
           R.id.delete -> {
               itemDelete()
               true
           }
           else -> onOptionsItemSelected(item)
       }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_info_item, menu)
        return true
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