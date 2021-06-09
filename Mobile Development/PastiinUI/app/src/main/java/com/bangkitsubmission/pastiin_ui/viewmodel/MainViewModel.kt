package com.bangkitsubmission.pastiin_ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bangkitsubmission.pastiin_ui.model.CompDatas
import com.bangkitsubmission.pastiin_ui.model.FruitDatas
import com.bangkitsubmission.pastiin_ui.model.UserDatas
import com.bangkitsubmission.pastiin_ui.repository.Repository
import kotlinx.coroutines.launch
import retrofit2.Response

class MainViewModel(private val repository: Repository): ViewModel() {

    val myResponse: MutableLiveData<Response<CompDatas>> = MutableLiveData()
    val myListResponse: MutableLiveData<Response<List<CompDatas>>> = MutableLiveData()
    val myGetFruitData: MutableLiveData<Response<FruitDatas>> = MutableLiveData()
    val myPushUserData: MutableLiveData<Response<UserDatas>> = MutableLiveData()

    fun pushDataUser(userDatas: UserDatas) {
        viewModelScope.launch {
            val response = repository.pushUserData(userDatas)
            myPushUserData.value = response
        }
    }

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

    val myListUserDataResponse: MutableLiveData<Response<List<UserDatas>>> = MutableLiveData()

    fun getListUserDatas(){
        viewModelScope.launch {
            val response = repository.getListUserDatas()
            myListUserDataResponse.value = response
        }
    }

    fun getFruitData(message: String) {
        viewModelScope.launch {
            val response = repository.getFruitData(message)
            myGetFruitData.value = response
        }
    }

    val myListFruitGlorasium: MutableLiveData<Response<List<FruitDatas>>> = MutableLiveData()

    fun getListFruit(){
        viewModelScope.launch {
            val response = repository.getListFruit()
            myListFruitGlorasium.value = response
        }
    }
}