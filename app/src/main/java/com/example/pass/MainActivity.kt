package com.example.pass

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.pass.PrefManager.PrefManager
import com.example.pass.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding:ActivityMainBinding
    lateinit var prefManager: PrefManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        prefManager = PrefManager(this)
        initInterface()
        reg()
        connect()



    }

    fun reg(){
        binding.btnConnectReg.setOnClickListener(){
            prefManager.putString("passwordReg",binding.textfieldReg.editText?.text.toString())
            val toast = Toast.makeText(applicationContext, "Пароль установлен", Toast.LENGTH_LONG)
            toast.show()
            initInterface()

        }
    }

    fun connect(){
        binding.btnConnect.setOnClickListener(){
            if (binding.textfield.editText?.text.toString().equals(prefManager.getString("passwordReg"))){
                var intent = Intent(applicationContext,ActivityPassMenu::class.java)
                startActivity(intent)
            }else{
                val toast = Toast.makeText(applicationContext, "Не верный пароль", Toast.LENGTH_LONG)
                toast.show()
            }

        }
    }

  fun  initInterface(){
        if (prefManager.getString("passwordReg") != null){
            binding.LLReg.visibility = View.GONE
            binding.LL1.visibility = View.VISIBLE
        }else{
            binding.LL1.visibility = View.GONE
            binding.LLReg.visibility = View.VISIBLE

        }
    }


    override fun onPause() {
        super.onPause()
        binding.textReg.setText("")
        binding.textFieldConnect.setText("")
    }
}