package com.example.team_project

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.team_project.databinding.ActivityLoginBinding
import com.example.team_project.databinding.ActivityMainBinding
import com.kakao.sdk.common.util.Utility


class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        var keyHash = Utility.getKeyHash(this)
//
//        println(keyHash)
//        Log.e("keyhash" , keyHash.toString())
//
        startActivity(Intent(this, LoginActivity::class.java))

    }
}