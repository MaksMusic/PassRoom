package com.example.pass.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.example.pass.Adpter.AdapterPass
import com.example.pass.App.App
import com.example.pass.R
import com.example.pass.databinding.ActivityPassMenuBinding
import com.example.pass.room.model.PassDao
import com.example.pass.room.model.PassItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ActivityPassMenu : AppCompatActivity(),AdapterPass.OnClicListener{
    lateinit var binding: ActivityPassMenuBinding
    var listPassRoom = ArrayList<PassItem>()
    lateinit var adapterPass: AdapterPass

    lateinit var passDao: PassDao

    override  fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPassMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)
        passDao = (application as App).getDataBase().passDao()

        initList()
        addPass()

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
       return when(item.itemId){
            R.id.delete -> {true}
           else -> true
       }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_recycler,menu)
        return true
    }

    override fun onRestart() {
        super.onRestart()

        lifecycleScope.launch(Dispatchers.IO) {
            var list = passDao.getPassItemList() as ArrayList
            listPassRoom = list
            withContext(Dispatchers.Main) {
                adapterPass = AdapterPass(listPassRoom, this@ActivityPassMenu)
                binding.recycler.adapter = adapterPass
            }
        }
    }
    fun initList(){
        //room add list adapter
        lifecycleScope.launch(Dispatchers.IO) {
            var list = passDao.getPassItemList() as ArrayList
            listPassRoom.addAll(list)
            withContext(Dispatchers.Main) {
                adapterPass = AdapterPass(listPassRoom, this@ActivityPassMenu)
                binding.recycler.adapter = adapterPass
            }
        }
    }



    fun addPass(){
        binding.BtnFab.setOnClickListener(){
            val intent = Intent(this@ActivityPassMenu,ActivityAddItem::class.java)
            startActivity(intent)
        }
    }

    override fun clic(id:Long) {
        lifecycleScope.launch(Dispatchers.IO){
            val intent = Intent(this@ActivityPassMenu,PassItemInfo::class.java)
            intent.putExtra("id",id)
            startActivity(intent)

        }
    }
}