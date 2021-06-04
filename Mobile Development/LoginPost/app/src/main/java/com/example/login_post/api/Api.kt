package com.example.login_post.api

import com.example.login_post.model.CompDatas
import retrofit2.Response
import retrofit2.http.GET

interface Api {

    @GET("compsdatas/1")
    suspend fun getCompDatas(): Response<CompDatas>

    @GET("compsdatas")
    suspend fun getListCompDatas(
    ): Response<List<CompDatas>>
}