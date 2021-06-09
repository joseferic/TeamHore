package com.bangkitsubmission.pastiin_ui.repository

import com.bangkitsubmission.pastiin_ui.api.RetrofitInstance
import com.bangkitsubmission.pastiin_ui.model.CompDatas
import com.bangkitsubmission.pastiin_ui.model.FruitDatas
import com.bangkitsubmission.pastiin_ui.model.UserDatas
import com.example.login_post.kuis.Quizzes
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

    suspend fun getFruitData(message: String): Response<FruitDatas>? {
        return RetrofitInstance.api.getFruitData(message)
    }

    suspend fun getListFruit(): Response<List<FruitDatas>>{
        return RetrofitInstance.api.getListFruitData()
    }
    suspend fun getListQuiz(): Response<List<Quizzes>>{
        return RetrofitInstance.api.getListQuiz()
    }

}