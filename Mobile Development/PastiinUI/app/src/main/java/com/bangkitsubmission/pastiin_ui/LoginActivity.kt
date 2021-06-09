package com.bangkitsubmission.pastiin_ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bangkitsubmission.pastiin_ui.databinding.ActivityLoginBinding
import com.bangkitsubmission.pastiin_ui.model.UserDatas
import com.bangkitsubmission.pastiin_ui.repository.Repository
import com.bangkitsubmission.pastiin_ui.viewmodel.MainViewModel
import com.bangkitsubmission.pastiin_ui.viewmodel.MainViewModelFactory

class LoginActivity : AppCompatActivity() {
    private lateinit var viewModel: MainViewModel
    private lateinit var binding: ActivityLoginBinding
    private lateinit var name:String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val repository = Repository()
        val viewModelFactory = MainViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)
        binding.btnlogin.setOnClickListener {
            login()

        }
        binding.textRegister.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java)
                .also {
                    it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(it)
                })
            finish()
        }
    }
   private fun login(){

       val email = binding.Email.text.toString().trim()
       val password = binding.Password.text.toString().trim()


       if (email.isEmpty()){
           binding.textresult.setText("Email Must Filled")
           return
       }
       if (password.isEmpty()){
           binding.textresult.setText("password Must Filled")
           return
       }

       var testUserGas = UserDatas("null",email,password)
       val testUser = testUserGas
        viewModel.getListUserDatas()
        viewModel.myListUserDataResponse.observe(this, Observer { response->
            if (response.isSuccessful){
                var userListDataBase = response.body()
                var DatanyaAda : Boolean = false
                Log.d("userDatabase",userListDataBase.toString())
                for (i in userListDataBase!!.indices){
                    var userDatabase = userListDataBase[i]
                    Log.d("userDatabase",userDatabase.toString())
                    if (testUser.email_User == userDatabase.email_User && testUser.pass_User == userDatabase.pass_User){
                        Log.d("userDatabase",userDatabase.toString())
                        name = userDatabase.name_User.toString()
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
    private fun displayToastForDebug(ada: Boolean){
        if (ada==true){
            Toast.makeText(this, "Halo, "+name, Toast.LENGTH_LONG).show()
            var intent = Intent(this, MainActivity::class.java)
            intent.putExtra("uname",name)
            startActivity(intent)
            finish()
        }
        else{
            Toast.makeText(this, "Login Gagal", Toast.LENGTH_LONG).show();
        }
    }
}