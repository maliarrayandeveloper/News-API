package com.maliarrayandev.newsapi.service

import com.maliarrayandev.newsapi.model.ResponseNews
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers

object RetrofitBuilder {
    private val client = OkHttpClient.Builder().build()
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://newsapi.org")
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()

    fun getService() = retrofit.create(TopHeadlines::class.java)
}

interface TopHeadlines{
    @Headers("Authorization: 9b97fe0389954936a7cd3e4b2ff054d0")
    @GET("/v2/top-headlines?country=id")
    fun fetchHeadlines(): Call<ResponseNews>
}