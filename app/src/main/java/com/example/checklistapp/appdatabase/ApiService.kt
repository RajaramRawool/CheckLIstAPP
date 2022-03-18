package com.example.checklistapp.appdatabase

import com.example.checklistapp.model.User
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

private const val URL: String = "https://websitecreator.co.in/CheckListApi/"
private val retrofit = Retrofit.Builder()
    .baseUrl(URL)
    .addConverterFactory(GsonConverterFactory.create())
    .build()

interface ApiService {
    @FormUrlEncoded
    @POST("insert_api.php")
    fun insertUser(
        @Field("name") name:String,
        @Field("email") email:String,
        @Field("password") password: String
    ): Call<User>
}

object Api {
    val retrofitService: ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }
}
