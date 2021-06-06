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
        val viewModelFactory =  MainViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java )
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
        viewModel.getListCompDatas()
        viewModel.myListResponse.observe(this,Observer{response->
            if (response.isSuccessful){
                response.body()?.let { Myadapter.setData(it) }
            }
            else{
                Log.d("Response",response.errorBody().toString())
            }
        })
    }

    private fun setupRecyclerview(){
        binding.rvBahanBahanMakanan.adapter = Myadapter
        binding.rvBahanBahanMakanan.layoutManager  = LinearLayoutManager(this)
    }
}