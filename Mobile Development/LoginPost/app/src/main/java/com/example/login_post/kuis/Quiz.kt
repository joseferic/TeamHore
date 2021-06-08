package com.example.login_post.kuis

<<<<<<< HEAD
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.login_post.databinding.ActivityQuizBinding
import com.example.login_post.kuis.type.MultipleChoice
import com.example.login_post.viewmodel.MainViewModel
import com.example.login_post.viewmodel.MainViewModelFactory
import com.example.repository.Repository
=======
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.login_post.R
import com.example.login_post.databinding.ActivityMainBinding
import com.example.login_post.databinding.ActivityQuizBinding
>>>>>>> fd3f7db4bec5c4841241e4d8880c063839931dc2

class Quiz : AppCompatActivity() {

    private lateinit var binding: ActivityQuizBinding
<<<<<<< HEAD
    private lateinit var viewModel: MainViewModel
    private lateinit var listQuiz : List<Quizzes>
=======
>>>>>>> fd3f7db4bec5c4841241e4d8880c063839931dc2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuizBinding.inflate(layoutInflater)
        setContentView(binding.root)

<<<<<<< HEAD
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
                val intent = Intent(this@Quiz, MultipleChoice::class.java)
                intent.putParcelableArrayListExtra("list", listQuizGambar as ArrayList<Quizzes?>?)
                startActivity(intent)
            }
            binding.btnQuizTebakBanyakBuah.setOnClickListener {
                Log.d("List Tebak Byk Buah = ", listQuizAngka.toString())
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
=======


>>>>>>> fd3f7db4bec5c4841241e4d8880c063839931dc2
    }
}