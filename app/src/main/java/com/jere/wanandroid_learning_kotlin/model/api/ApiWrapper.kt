package com.jere.wanandroid_learning_kotlin.model.api

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object ApiWrapper {

    private var instance: ApiService? = null

    //const 用于修饰常量
    private const val BASE_URL: String = "https://www.wanandroid.com/"

    fun getInstance(): ApiService? {
        if (instance == null) {
            val okHttpClient = OkHttpClient.Builder()
                .connectTimeout(1, TimeUnit.MINUTES)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(15, TimeUnit.SECONDS)
                .build()

            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build()
            instance = retrofit.create(ApiService::class.java)
        }
        return instance
    }
}