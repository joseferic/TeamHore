package com.example.login_post.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.login_post.model.CompDatas
import com.example.repository.Repository
import kotlinx.coroutines.launch
import retrofit2.Response

class MainViewModel(private val repository: Repository): ViewModel() {

    val myResponse: MutableLiveData<Response<CompDatas>> = MutableLiveData()
    val myListResponse: MutableLiveData<Response<List<CompDatas>>> = MutableLiveData()



    fun getCompDatas(){
        viewModelScope.launch {
            val response = repository.getCompDatas()
            myResponse.value = response
        }
    }

    fun getListCompDatas(){
        viewModelScope.launch {
            val response = repository.getListCompDatas()
            myListResponse.value = response
        }
    }
}