package com.example.farmerapp.apis

import android.media.Image
import com.example.farmerapp.models.PostResponce
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface FileApi {

    @Multipart
    @POST("question-post")
    fun uploadImage(
        @Part image : MultipartBody.Part
    ) : Call<PostResponce>

    companion object{
        operator fun invoke() : FileApi {
            return Retrofit.Builder()
                .baseUrl("http://192.168.145.215/ssip/public/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(FileApi::class.java)

        }
    }

}