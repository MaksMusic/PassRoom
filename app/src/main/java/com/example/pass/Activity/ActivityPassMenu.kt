package com.example.pass.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.example.pass.Adpter.AdapterPass
import com.example.pass.App.App
import com.example.pass.PrefManager.PrefManager
import com.example.pass.R
import com.example.pass.databinding.ActivityPassMenuBinding
import com.example.pass.room.model.CategoryDao
import com.example.pass.room.model.PassDao
import com.example.pass.room.model.PassItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ActivityPassMenu : AppCompatActivity(),AdapterPass.OnClicListener{
    lateinit var binding: ActivityPassMenuBinding
    var listPassRoom = ArrayList<PassItem>()


    lateinit var adapterPass: AdapterPass
    lateinit var prefManager: PrefManager

    lateinit var passDao: PassDao

    override  fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPassMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)

        passDao = (application as App).getDataBase().passDao()
        binding.toolbar.title = ""
        setSupportActionBar(binding.toolbar)
        prefManager = PrefManager(this)
        initList()
        addPass()

    }

    fun addCategory(){
        binding.BtnFabCategory.setOnClickListener(){
            //val intent =Intent()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
       return when(item.itemId){
            R.id.deleteList-> {
                deliteList()
                true
            }
           R.id.setPass -> {
                setPass()
               true
           }
           else -> true
       }
    }

    private fun setPass(){
        binding.recycler.visibility = View.GONE
        binding.LL1setPass.visibility = View.VISIBLE
        binding.btnSetPass.setOnClickListener(){
            if (binding.editTextSetPass.text.toString() == prefManager.getString("passwordReg").toString()){
                prefManager.putString("passwordReg",binding.EditTextNewPass.text.toString())
                Toast.makeText(applicationContext,"Вы сменили пароль",Toast.LENGTH_LONG).show()
                binding.recycler.visibility = View.VISIBLE
                binding.LL1setPass.visibility = View.GONE
                binding.editTextSetPass.setText("")
                binding.EditTextNewPass.setText("")
            }else{
                Toast.makeText(applicationContext,"Не верный пароль",Toast.LENGTH_LONG).show()
                binding.editTextSetPass.setText("")
            }
        }
        binding.btnCloseSetPass.setOnClickListener(){
            binding.recycler.visibility = View.VISIBLE
            binding.LL1setPass.visibility = View.GONE
            binding.editTextSetPass.setText("")
            binding.EditTextNewPass.setText("")
        }

    }

    override fun onPause() {
        finish()
        super.onPause()
    }


     private fun deliteList(){
        binding.recycler.visibility = View.GONE
        binding.LL1Delite.visibility = View.VISIBLE
        binding.delete.setOnClickListener(){
            if (binding.textPass.toString() == prefManager.getString("passwordReg").toString() ){
                lifecycleScope.launch(Dispatchers.IO){
                    passDao.clearPassItem()
                }
                adapterPass.clearList()
                binding.textPass.setText("")
                binding.recycler.visibility = View.VISIBLE
                binding.LL1Delite.visibility = View.GONE
                Toast.makeText(applicationContext,"Пароли удаленны",Toast.LENGTH_LONG).show()
            }

        }
        binding.close.setOnClickListener(){
            binding.recycler.visibility = View.VISIBLE
            binding.LL1Delite.visibility = View.GONE
            binding.textPass.setText("")
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
            val intent = Intent(this@ActivityPassMenu,PassItemInfoActivity::class.java)
            intent.putExtra("id",id)
            startActivity(intent)

        }
    }
}