package com.example.farmerapp


import com.example.farmerapp.models.User
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface Api {

    @FormUrlEncoded
    @POST("register-farmer")
    suspend fun registerFarmer(
        @Field("name") name: String,
        @Field("mobile") phone: String,
        @Field("village") village: String
    ): Call<ResponseBody>
}