package com.example.login_post.api

import com.example.login_post.kuis.Quizzes
import com.example.login_post.model.CompDatas
import com.example.login_post.model.UserDatas
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

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

    @GET("quizzes")
    suspend fun getListQuiz(): Response<List<Quizzes>>
}