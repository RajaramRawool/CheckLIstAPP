package com.example.checklistapp.api

import com.example.checklistapp.model.Item
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

private val BASE_URL = "http://192.168.1.12/CheckListAppApi/"

private val retrofit = Retrofit.Builder()
    .addConverterFactory(GsonConverterFactory.create())
    .baseUrl(BASE_URL)
    .build()

interface ApiService {

    @FormUrlEncoded
    @POST("index.php")
    fun addItem(@Field("name") name : String, @Field("Checked") checked : String): Call<Item>

}

object Api {
    val RETROFIT_SERVICE : ApiService by lazy { retrofit.create(ApiService::class.java) }
}