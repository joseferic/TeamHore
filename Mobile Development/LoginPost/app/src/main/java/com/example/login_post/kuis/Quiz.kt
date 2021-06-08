package com.example.login_post.kuis

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.login_post.R
import com.example.login_post.databinding.ActivityMainBinding
import com.example.login_post.databinding.ActivityQuizBinding

class Quiz : AppCompatActivity() {

    private lateinit var binding: ActivityQuizBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuizBinding.inflate(layoutInflater)
        setContentView(binding.root)



    }
}