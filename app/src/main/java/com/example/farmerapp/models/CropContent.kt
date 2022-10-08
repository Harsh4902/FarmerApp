package com.example.farmerapp.models

import com.google.gson.annotations.SerializedName

data class CropContent(
    @SerializedName("title")
    val cropName: String,
    @SerializedName("image")
    val imageUrl : String,
    @SerializedName("id")
    val id : Int)