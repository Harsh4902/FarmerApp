package com.example.farmerapp.models

import com.google.gson.annotations.SerializedName

data class FinalCategory(
    @SerializedName("title")
    val ctName : String,
    @SerializedName("id")
    val id : Int
    )
