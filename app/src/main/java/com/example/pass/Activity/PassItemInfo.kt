package com.example.pass.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.example.pass.App.App
import com.example.pass.databinding.ActivityPassItemInfoBinding
import com.example.pass.databinding.PassItemBinding
import com.example.pass.room.model.PassDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PassItemInfo : AppCompatActivity() {
    private lateinit var binding: ActivityPassItemInfoBinding
    private var id:Long? = null
    lateinit var passDao: PassDao
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPassItemInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        passDao = (application as App).getDataBase().passDao()
        id  = intent.extras?.getLong("id")

        initText()

    }

    fun initText(){
        lifecycleScope.launch(Dispatchers.IO){
            if (id!=null){
                var itemPass = passDao.getPassItem(id!!)
                var (name,login,pass) = itemPass

                withContext(Dispatchers.Main){
                  binding.textNameItem.setText(name.toString())
                  binding.textLoginItem.setText(login.toString())
                  binding.textPassItem.setText(pass.toString())
                }
            }
        }
    }
}