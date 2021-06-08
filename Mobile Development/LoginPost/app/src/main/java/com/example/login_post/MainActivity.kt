package com.example.login_post

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.login_post.adapter.Adapter
import com.example.login_post.databinding.ActivityMainBinding
import com.example.login_post.model.UserDatas
import com.example.login_post.viewmodel.MainViewModel
import com.example.login_post.viewmodel.MainViewModelFactory
import com.example.repository.Repository

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    private lateinit var binding: ActivityMainBinding
    private val Myadapter by lazy { Adapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerview()

        val repository = Repository()
        val viewModelFactory = MainViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)
//        viewModel.getCompDatas()
//        viewModel.myResponse.observe(this, Observer { responses ->
//           if (responses.isSuccessful){
//               Log.d("Response",responses.body()?.id_compsData.toString())
//               Log.d("Response",responses.body()?.id_foodData.toString())
//               Log.d("Response",responses.body()?.name_compsData!!)
//               binding.namaBahanMakanan.setText(responses.body()?.name_compsData!!)
//               Log.d("Response",responses.body()?.desc_compsData!!)
//           }
//            else{
//                Log.d("Response",responses.errorBody().toString())
//            }
//        })

/*        viewModel.getListCompDatas()
        viewModel.myListResponse.observe(this, Observer { response ->
            if (response.isSuccessful) {
                response.body()?.let { Myadapter.setData(it) }
            } else {
                Log.d("Response List Error", response.errorBody().toString())
            }
        })*/

        binding.btnRegister.setOnClickListener {
            val test = UserDatas("Sparkling Knights", "klee@gmail.com", "bombombakudan")
            var resultUserName = "Masih null sep"
            viewModel.pushDataUser(test)
            viewModel.myPushUserData.observe(this, { response ->
                Log.d("Response", response.body().toString())
                Log.d("Response", response.code().toString())
                Log.d("Response", response.message().toString())
                resultUserName = response.body()!!.name_User
                binding.namaBahanMakanan.setText("Nama user nya adalah " + resultUserName)
            })
        }

        binding.btnLogin.setOnClickListener{
            val testUserRegistered = UserDatas("Sparkling Knights", "klee@gmail.com", "bombombakudan")
            val testUserNotRegistered = UserDatas("Xiao the Vigilant Yaksha","xiao@gmail.com","kiero")

            val testUser = testUserNotRegistered
            viewModel.getListUserDatas()
            viewModel.myListUserDataResponse.observe(this, Observer { response->
                if (response.isSuccessful){
                    var UserListDataBase = response.body()
                    var DatanyaAda : Boolean = false
                    Log.d("userDatabase",UserListDataBase.toString())
                    for (i in UserListDataBase!!.indices){
                        var userDatabase = UserListDataBase[i]
                        Log.d("userDatabase",userDatabase.toString())
                        if (testUser.email_User == userDatabase.email_User && testUser.pass_User == userDatabase.pass_User){
                            Log.d("userDatabase",userDatabase.toString())
                            DatanyaAda = true
                        }
                        else{
                            Log.d("userDatabase",userDatabase.toString())
                        }
                    }
                    displayToastForDebug(DatanyaAda)
                }
            })
        }

    }

    private fun displayToastForDebug(ada: Boolean){
        if (ada==true){
            Toast.makeText(this, "Login Berhasil", Toast.LENGTH_LONG).show()
        }
        else{
            Toast.makeText(this, "Login Gagal", Toast.LENGTH_LONG).show();
        }
    }

    private fun setupRecyclerview() {
        binding.rvBahanBahanMakanan.adapter = Myadapter
        binding.rvBahanBahanMakanan.layoutManager = LinearLayoutManager(this)
    }
}