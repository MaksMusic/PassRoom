package com.example.pass

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.example.pass.Adpter.AdapterPass
import com.example.pass.App.App
import com.example.pass.databinding.ActivityPassMenuBinding
import com.example.pass.room.model.PassDao
import com.example.pass.room.model.PassItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ActivityPassMenu : AppCompatActivity() {
    lateinit var binding: ActivityPassMenuBinding
    var listPassRoom = ArrayList<PassItem>()
    lateinit var adapterPass: AdapterPass

    lateinit var passDao: PassDao

    override  fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPassMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)
        passDao = (application as App).getDataBase().passDao()


        //room add list adapter
        lifecycleScope.launch(Dispatchers.IO){
            passDao.insertPassItem(PassItem(0,"Login","Name","12345","Email",))
            var list = passDao.getPassItemList() as ArrayList
            listPassRoom.addAll(list)
            withContext(Dispatchers.Main){
                adapterPass = AdapterPass(listPassRoom)
                binding.recycler.adapter = adapterPass
            }
        }


        addPass()




    }



    fun addPass(){
        binding.BtnFab.setOnClickListener(){
            adapterPass.addItem(PassItem(0,"qw","2","ww","ew"))

        }
    }
}