package com.example.farmerapp.apis


import com.example.farmerapp.R
import com.example.farmerapp.models.CropContent
import com.example.farmerapp.models.FinalCategory
import com.example.farmerapp.models.User
import okhttp3.MultipartBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.http.*

interface Api {


    @FormUrlEncoded
    @POST("register-farmer")
    suspend fun registerFarmer(
        @Field("name") name: String,
        @Field("mobile") phone: String,
        @Field("village") village: String,
        @Field("taluka") taluka: String
    ): Call<ResponseBody>


    @FormUrlEncoded
    @POST("question-post")
    suspend fun sendQuestion(
        @Field("question") text: String,
    )

    @GET("crops?")
    fun getPost(@Query("lang") ln: String):Call<List<CropContent>>

    @GET("crop-query?")
    fun getCata(@Query("lang") ln: String, @Query("crop_id") cid :Int):Call<List<FinalCategory>>

    @Multipart
    @FormUrlEncoded
    @POST("question-post")
    suspend fun postQuestion(
        @Field("question") text: String,
        @Field("farmer_id") createdBy : Int
    ) : Call<ResponseBody>

    companion object{
        val instance by lazy{
            Retrofit.Builder()
                .baseUrl("http://192.168.145.215/ssip/public/api/")
                .build()
                .create(Api::class.java)
        }
    }

    @Multipart
    suspend fun uploadImage(
        @Part image: okhttp3.MultipartBody.Part
    )

}