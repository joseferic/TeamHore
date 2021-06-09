package com.bangkitsubmission.pastiin_ui.kuis.type

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.bangkitsubmission.pastiin_ui.R
import com.bangkitsubmission.pastiin_ui.databinding.ActivityMultipleChoiceBinding
import com.bangkitsubmission.pastiin_ui.kuis.SelectQuiz
import com.bangkitsubmission.pastiin_ui.viewmodel.MainViewModel
import com.bumptech.glide.Glide
import com.example.login_post.kuis.Quizzes
import kotlin.random.Random

class MultipleChoice : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityMultipleChoiceBinding
    private lateinit var viewModel: MainViewModel

    private var mCurrentPosition: Int = 1
    private var mQuizList: ArrayList<Quizzes>? = null
    private var mSelectedOptionPosition: Int = 0
    private var mCorrectAnswer: Int = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMultipleChoiceBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //val mQuizList: ArrayList<Quizzes?>? = intent.getParcelableArrayListExtra<Quizzes?>("list")
        mQuizList = intent.getParcelableArrayListExtra<Quizzes?>("list")

        mQuizList = randomQuestion(mQuizList)

        Log.d("List PG", mQuizList.toString())

        setQuestion()

        binding.tvOptionOne.setOnClickListener(this)
        binding.tvOptionTwo.setOnClickListener(this)

        binding.btnSubmit.setOnClickListener(this)
    }

    private fun randomQuestion(mQuizListRandom: ArrayList<Quizzes>?) : ArrayList<Quizzes>?{

        for (i in mQuizListRandom!!.indices){
            mQuizListRandom[i] = mQuizListRandom[Random.nextInt(0, mQuizListRandom.size)]
        }
        return mQuizListRandom
    }

    private fun setQuestion() {
        //var quizzes: Quizzes? = mQuizList?.get(mCurrentPosition - 1)
        var quizzes: Quizzes? = mQuizList!!.get(mCurrentPosition - 1)
        Log.d("Quiz = ", quizzes.toString())

        defaultOptionView()

        if (mCurrentPosition == 3) {
            binding.btnSubmit.text = "Finish"
        } else {
            binding.btnSubmit.text = "Submit"
        }

        binding.progressBar.progress = mCurrentPosition
        binding.tvProgressBar.text = "$mCurrentPosition" + "/" + binding.progressBar.max

        Glide
            .with(this)
            .load(quizzes!!.pict_Quiz)
            .centerCrop()
            .into(binding.ivGambar);
        binding.ivGambar.setClipToOutline(true)
        binding.tvOptionOne.text = quizzes.option1_Quiz
        binding.tvOptionTwo.text = quizzes.option2_Quiz


    }

    private fun defaultOptionView() {
        val options = ArrayList<TextView>()
        options.add(0, binding.tvOptionOne)
        options.add(1, binding.tvOptionTwo)

        for (option in options) {
            option.setTextColor(Color.parseColor("#000000"))
            option.typeface = Typeface.DEFAULT
            option.background = ContextCompat.getDrawable(this, R.drawable.default_option_border_bg)
        }
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.tv_option_one -> {
                selectedOptionView(binding.tvOptionOne, 1)
            }
            R.id.tv_option_two -> {
                selectedOptionView(binding.tvOptionTwo, 2)
            }
            R.id.btn_submit -> {
                if (mSelectedOptionPosition == 0) {
                    mCurrentPosition++
                    when {
                        mCurrentPosition <= 3 -> {
                            setQuestion()
                        }
                        else -> {
                            Toast.makeText(
                                this,
                                "Congratulations you finish the quiz",
                                Toast.LENGTH_SHORT
                            ).show()
                            val intent = Intent(this, SelectQuiz::class.java)
                            intent.putExtra("score",mCorrectAnswer)
                            Toast.makeText(this, "Congratulation",Toast.LENGTH_LONG).show()
                            startActivity(intent)
                            finish()
                        }
                    }
                } else {
                    val question = mQuizList?.get(mCurrentPosition - 1)
                    if (question!!.correctanswer_Quiz != mSelectedOptionPosition) {
                        answerView(mSelectedOptionPosition, R.drawable.wrong_option_border_bg)
                    } else {
                        mCorrectAnswer++
                        answerView(mSelectedOptionPosition, R.drawable.correct_option_border_bg)
                    }
                    if (mCurrentPosition == 3) {
                        binding.btnSubmit.text = "Finish"
                    } else {
                        binding.btnSubmit.text = "Next Question"
                    }
                    mSelectedOptionPosition = 0
                }
            }
        }
    }


    private fun answerView(answer: Int, drawableView: Int) {
        when (answer) {
            1 -> {
                binding.tvOptionOne.background = ContextCompat.getDrawable(this, drawableView)
            }
            2 -> {
                binding.tvOptionTwo.background = ContextCompat.getDrawable(this, drawableView)
            }
            else -> {
                binding.tvOptionOne.background = ContextCompat.getDrawable(this, drawableView)
                binding.tvOptionTwo.background = ContextCompat.getDrawable(this, drawableView)
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