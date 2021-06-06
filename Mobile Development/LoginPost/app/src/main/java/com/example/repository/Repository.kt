package com.example.repository

import com.example.login_post.api.RetrofitInstance
import com.example.login_post.model.CompDatas
import retrofit2.Response

class Repository {

    suspend fun getCompDatas(): Response<CompDatas>{
        return RetrofitInstance.api.getCompDatas()
    }

    suspend fun getListCompDatas(): Response<List<CompDatas>>{
        return RetrofitInstance.api.getListCompDatas()
    }

}