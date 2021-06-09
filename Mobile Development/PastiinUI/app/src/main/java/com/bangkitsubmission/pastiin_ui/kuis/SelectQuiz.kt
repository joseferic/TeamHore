package com.bangkitsubmission.pastiin_ui.kuis

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bangkitsubmission.pastiin_ui.databinding.ActivitySelectQuizBinding
import com.bangkitsubmission.pastiin_ui.kuis.type.MultipleChoice
import com.bangkitsubmission.pastiin_ui.repository.Repository
import com.bangkitsubmission.pastiin_ui.viewmodel.MainViewModel
import com.bangkitsubmission.pastiin_ui.viewmodel.MainViewModelFactory
import com.example.login_post.kuis.Quizzes

class SelectQuiz : AppCompatActivity() {

    private lateinit var binding: ActivitySelectQuizBinding
    private lateinit var viewModel: MainViewModel
    private lateinit var listQuiz : List<Quizzes>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySelectQuizBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN

        val repository = Repository()
        val viewModelFactory = MainViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)
        var listQuizDatabase : List<Quizzes>?
        var listQuiz : List<Quizzes>?
        viewModel.getListQuiz()
        var DatanyaAda: Boolean? = false
        viewModel.myListQuizResponse.observe(this, Observer { response ->
            if (response.isSuccessful) {
                listQuizDatabase = response.body()
                // Log.d("listQuizDatabase",listQuizDatabase.toString())
                //displayToastForDebug(true)
                DatanyaAda = true
            } else {
                Log.d("Response List Error", response.errorBody().toString())
            }
            displayToastForDebug(DatanyaAda)
            var listQuiz = generateListQuiz(response.body())
            var listQuizGambar = seperateListQuizType(listQuiz, "1")
            var listQuizAngka = seperateListQuizType(listQuiz, "2")

            binding.btnQuizTebakGambar.setOnClickListener {
                Log.d("List Tebak Gambar = ", listQuizGambar.toString())

                //Kirim list ke activity yang bener
                  val intent = Intent(this@SelectQuiz, MultipleChoice::class.java)
                  intent.putParcelableArrayListExtra("list", listQuizGambar as ArrayList<Quizzes?>?)
                  startActivity(intent)
                  finish()
            }
            binding.btnQuizTebakBanyakBuah.setOnClickListener {
                Log.d("List Tebak Byk Buah = ", listQuizAngka.toString())
                //Kirim list ke activity yang bener
                val intent = Intent(this@SelectQuiz, MultipleChoice::class.java)
                intent.putParcelableArrayListExtra("list", listQuizAngka as ArrayList<Quizzes?>?)
                startActivity(intent)
                finish()
            }
        })

    }

    private fun displayToastForDebug(ada: Boolean?){
        if (ada==true){
            Toast.makeText(this, "Data list quiz success", Toast.LENGTH_LONG).show()
        }
        else{
            Toast.makeText(this, "Data list quiz gagal", Toast.LENGTH_LONG).show();
        }
    }

    private fun generateListQuiz(list: List<Quizzes>?) : List<Quizzes>?{
        var listQuiz : List<Quizzes>? = list
        return listQuiz
    }

    private fun seperateListQuizType(list: List<Quizzes>?, typeQuiz: String) : List<Quizzes>?{

        var listQuizByType : List<Quizzes>? = null

        listQuizByType = list
        var listQuizTypeTebakGambar : MutableList<Quizzes> = mutableListOf<Quizzes>()
        var listQuizTypeTebakAngka  : MutableList<Quizzes> = mutableListOf<Quizzes>()

        for (i in listQuizByType!!.indices-1){
            if (listQuizByType[i].type_Quiz == "1"){
                var quiz: Quizzes = listQuizByType[i]
                listQuizTypeTebakGambar!!.add(quiz)
            }
            if (listQuizByType[i].type_Quiz == "2"){
                var quiz: Quizzes = listQuizByType[i]
                listQuizTypeTebakAngka!!.add(quiz)
            }
        }

        if (typeQuiz == "1"){
            listQuizByType = listQuizTypeTebakGambar
        }
        if (typeQuiz == "2"){
            listQuizByType = listQuizTypeTebakAngka
        }
        return listQuizByType
    }

}