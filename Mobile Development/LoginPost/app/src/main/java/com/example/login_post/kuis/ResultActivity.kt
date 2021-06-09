package com.example.login_post.kuis

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.login_post.R
import com.example.login_post.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.activity_result.*

class ResultActivity : AppCompatActivity() {

    private var mScore: Int? = null
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        mScore = intent.getIntExtra("score",0)
        tv_score.text = "The quiz score you get this time is " + "${mScore}" + " out of 3 questions"
        btn_backToChooseQuiz.setOnClickListener{
            val intent= Intent(this,Quiz::class.java)
            startActivity(intent)
        }
    }
}