package com.bangkitsubmission.pastiin_ui.api

import com.bangkitsubmission.pastiin_ui.model.CompDatas
import com.bangkitsubmission.pastiin_ui.model.FruitDatas
import com.bangkitsubmission.pastiin_ui.model.UserDatas
import com.example.login_post.kuis.Quizzes
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface Api {

    @GET("compsdatas/1")
    suspend fun getCompDatas(): Response<CompDatas>

    @GET("compsdatas")
    suspend fun getListCompDatas(
    ): Response<List<CompDatas>>

    @POST("users")
    suspend fun pushUserData(
            @Body userDatas: UserDatas
    ): Response<UserDatas>

    @GET("users")
    suspend fun getListUserData(): Response<List<UserDatas>>

    @GET("fruits/{message}")
    suspend fun getFruitData(@Path(value = "message", encoded = true)message: String) : Response<FruitDatas>

    @GET("fruits")
    suspend fun getListFruitData(): Response<List<FruitDatas>>

    @GET("quizzes")
    suspend fun getListQuiz(): Response<List<Quizzes>>
}