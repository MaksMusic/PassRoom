package com.example.pass.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.Toast
import androidx.core.view.doOnLayout
import androidx.core.view.doOnNextLayout
import androidx.core.widget.doOnTextChanged
import com.example.pass.PrefManager.PrefManager
import com.example.pass.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var prefManager: PrefManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        prefManager = PrefManager(this)
        initInterface()
        reg()
        connect()
        passRegErrorXml()
        passConnectErrorXml()

    }


    fun  passConnectErrorXml(){
        binding.textFieldConnect.doOnTextChanged { text, start, before, count ->
            if(text!!.length > 16){
                binding.textfield.error = "слишком длинный пароль"
            }else if(text!!.length < 16){
                binding.textfieldReg.error = null
            }
        }
    }


    fun passRegErrorXml(){
        binding.textReg.doOnTextChanged { text, start, before, count ->
            if(text!!.length > 16){
                binding.textfieldReg.error = "слишком длинный парроль"
            }else if(text!!.length < 16){
                binding.textfieldReg.error = null
            }
        }
    }


    fun reg(){
        binding.btnConnectReg.setOnClickListener(){
            if (binding.textfieldReg.editText?.text.toString().length <= 16 && binding.textfieldReg.editText?.text.toString() != "null") {
                prefManager.putString(Const.PASS_KEY, binding.textfieldReg.editText?.text.toString())
                val toast =
                    Toast.makeText(applicationContext, "Пароль установлен", Toast.LENGTH_LONG)
                toast.show()
                initInterface()
            }
        }
    }

    override fun onRestart() {
        binding.textFieldConnect.setText("")
        super.onRestart()
    }

    fun connect(){
        binding.btnConnect.setOnClickListener(){
            if (binding.textfield.editText?.text.toString().equals(prefManager.getString(Const.PASS_KEY))){
                var intent = Intent(applicationContext, ActivityPassMenu::class.java)
                startActivity(intent)
            }else{
                binding.textfield.editText?.imeOptions = 2
                val toast = Toast.makeText(applicationContext, "Не верный пароль", Toast.LENGTH_SHORT)
                toast.setGravity(0,0,Gravity.BOTTOM)
                toast.show()
            }

        }
    }

  fun  initInterface(){
        if (prefManager.getString(Const.PASS_KEY) != null){
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