package com.example.farmerapp.models

import java.lang.reflect.Constructor

data class Question(
    val text: String?,
    val createdBy : Int
){
    constructor() : this(null,0)
}
