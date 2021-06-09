package com.example.repository

import com.example.login_post.api.RetrofitInstance
import com.example.login_post.kuis.Quizzes
import com.example.login_post.model.CompDatas
import com.example.login_post.model.UserDatas
import retrofit2.Response

class Repository {

    suspend fun getCompDatas(): Response<CompDatas>{
        return RetrofitInstance.api.getCompDatas()
    }

    suspend fun getListCompDatas(): Response<List<CompDatas>>{
        return RetrofitInstance.api.getListCompDatas()
    }

    suspend fun pushUserData(userDatas: UserDatas): Response<UserDatas>{
        return RetrofitInstance.api.pushUserData(userDatas)
    }

    suspend fun getListUserDatas(): Response<List<UserDatas>>{
        return RetrofitInstance.api.getListUserData()
    }

    suspend fun getListQuiz(): Response<List<Quizzes>>{
        return RetrofitInstance.api.getListQuiz()
    }

}