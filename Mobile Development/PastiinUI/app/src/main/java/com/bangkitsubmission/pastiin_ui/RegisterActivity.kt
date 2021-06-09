package com.bangkitsubmission.pastiin_ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.bangkitsubmission.pastiin_ui.databinding.ActivityRegisterBinding
import com.bangkitsubmission.pastiin_ui.model.UserDatas
import com.bangkitsubmission.pastiin_ui.repository.Repository
import com.bangkitsubmission.pastiin_ui.viewmodel.MainViewModel
import com.bangkitsubmission.pastiin_ui.viewmodel.MainViewModelFactory

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val repository = Repository()
        val viewModelFactory = MainViewModelFactory(repository)

        viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)
        binding.btnGAS.setOnClickListener {
            register()
        }
        binding.textLogin.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java)
                .also {
                    it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(it)
                })
            finish()
        }
    }

    private fun register() {

        val name = binding.Name.text.toString()
        val email = binding.Email.text.toString().trim()
        val password = binding.Password.text.toString().trim()

        if (name.isEmpty()){
            binding.textresult.setText("Name Must Filled")
            return
        }
        if (email.isEmpty()){
            binding.textresult.setText("Email Must Filled")
            return
        }
        if (password.isEmpty()){
            binding.textresult.setText("password Must Filled")
            return
        }

            binding.textresult.setText("")
            val test = UserDatas(name,email,password)
            var resultUserName = "Masih null sep"
            viewModel.pushDataUser(test)
            var DatanyaAda : Boolean = false
            viewModel.myPushUserData.observe(this, { response ->
                if (response.isSuccessful){
                Log.d("Response", response.body().toString())
                Log.d("Response", response.code().toString())
                Log.d("Response", response.message().toString())
                resultUserName = response.body()!!.name_User
                Toast.makeText(this,"Pendaftaran "+resultUserName+" berhasil",Toast.LENGTH_SHORT).show()
                DatanyaAda = true
                } else {
                    Toast.makeText(this,"Pendaftaran Gagal, Something wong",Toast.LENGTH_SHORT).show()
                    Log.d("Response",response.errorBody().toString())
                }
                displayToastForDebug(DatanyaAda)
            })

    }
    private fun displayToastForDebug(ada: Boolean){
        if (ada==true){
            Toast.makeText(this, "Login Berhasil", Toast.LENGTH_LONG).show()
            var intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
        else{
            Toast.makeText(this, "Login Gagal", Toast.LENGTH_LONG).show();
        }
    }

}