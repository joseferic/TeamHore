package com.example.login_post.kuis.type

import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.example.login_post.R
import com.example.login_post.databinding.ActivityMultipleChoiceBinding
import com.example.login_post.kuis.Quizzes
import com.example.login_post.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.activity_multiple_choice.*

class MultipleChoice : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityMultipleChoiceBinding
    private lateinit var viewModel: MainViewModel

    private var mCurrentPosition: Int = 1
    private var mQuizList: ArrayList<Quizzes>? = null
    private var mSelectedOptionPosition: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMultipleChoiceBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //val mQuizList: ArrayList<Quizzes?>? = intent.getParcelableArrayListExtra<Quizzes?>("list")
        mQuizList = intent.getParcelableArrayListExtra<Quizzes?>("list")

        Log.d("List PG", mQuizList.toString())
        setQuestion()

        binding.tvOptionOne.setOnClickListener(this)
        binding.tvOptionTwo.setOnClickListener(this)

        binding.btnSubmit.setOnClickListener(this)
    }

    private fun setQuestion() {
        mCurrentPosition = 1
        val quizzes: Quizzes? = mQuizList?.get(mCurrentPosition - 1)
        Log.d("Quiz = ", quizzes.toString())

        defaultOptionView()

        binding.progressBar.progress = mCurrentPosition
        binding.tvProgressBar.text = "$mCurrentPosition" + "/" + binding.progressBar.max

        Glide
            .with(this)
            .load(quizzes!!.pict_Quiz)
            .centerCrop()
            .into(iv_gambar);

        binding.tvOptionOne.text = quizzes.option1_Quiz
        binding.tvOptionTwo.text = quizzes.option2_Quiz


    }

    private fun defaultOptionView() {
        val options = ArrayList<TextView>()
        options.add(0, tv_option_one)
        options.add(1, tv_option_two)

        for (option in options) {
            option.setTextColor(Color.parseColor("#000000"))
            option.typeface = Typeface.DEFAULT
            option.background = ContextCompat.getDrawable(this, R.drawable.default_option_border_bg)
        }
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.tv_option_one -> {
                selectedOptionView(tv_option_one, 1)
            }
            R.id.tv_option_two -> {
                selectedOptionView(tv_option_two, 2)
            }
            R.id.btn_submit -> {
                if (mSelectedOptionPosition == 0) {
                    mCurrentPosition++
                    when {
                        mCurrentPosition <= mQuizList!!.size -> {
                            setQuestion()
                        }
                        else -> {
                            Toast.makeText(
                                this,
                                "You have succesfully completed the Quiz",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                } else {
                    val question = mQuizList?.get(mCurrentPosition - 1)
                    if (question!!.correctanswer_Quiz != mSelectedOptionPosition) {
                        answerView(mSelectedOptionPosition,R.drawable.wrong_option_border_bg)
                    }
                    else{
                        answerView(question.correctanswer_Quiz,R.drawable.correct_option_border_bg)
                    }
                    if (mCurrentPosition == mQuizList!!.size){
                        binding.btnSubmit.text = "Finish"
                    }else{
                        binding.btnSubmit.text = "Go to next question"
                    }

                }
            }
        }
    }

    private fun answerView(answer: Int, drawableView: Int) {
        when (answer) {
            1 -> {
                tv_option_one.background = ContextCompat.getDrawable(this, drawableView)
            }
            2 -> {
                tv_option_two.background = ContextCompat.getDrawable(this, drawableView)
            }
        }
    }

    private fun selectedOptionView(tv: TextView, selectedOptionNum: Int) {
        defaultOptionView()
        mSelectedOptionPosition = selectedOptionNum
        tv.setTextColor(Color.parseColor("#45B6FE"))
        tv.setTypeface(tv.typeface, Typeface.BOLD)
        tv.background = ContextCompat.getDrawable(this, R.drawable.selected_option_border_bg)
    }
}