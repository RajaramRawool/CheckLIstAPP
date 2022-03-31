package com.example.checklistapp.appdatabase

import com.example.checklistapp.model.Item
import com.example.checklistapp.model.ItemList
import com.example.checklistapp.model.ResponseMessage
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

private const val URL: String = "https://websitecreator.co.in/CheckListApi/"
private val retrofit = Retrofit.Builder()
    .baseUrl(URL)
    .addConverterFactory(GsonConverterFactory.create())
    .build()

interface ApiService {
    @FormUrlEncoded
    @POST("register_api.php")
    fun userRegistration(
        @Field("name") name:String,
        @Field("email") email:String,
        @Field("password") password: String
    ): Call<ResponseMessage>

    @FormUrlEncoded
    @POST("login_api.php")
    fun userLogin(
        @Field("email") email:String,
        @Field("password") password:String
    ): Call<ResponseMessage>

    @GET("get_items_api.php")
    fun getItems() : Call<MutableList<Item>>

}

object Api {
    val retrofitService: ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }
}
