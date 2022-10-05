package com.example.pass.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.pass.databinding.PassItemBinding

class PassItemInfo : AppCompatActivity() {
    lateinit var binding: PassItemBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = PassItemBinding.inflate(layoutInflater)
        setContentView(binding.root)



    }
}