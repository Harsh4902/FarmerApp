package com.example.farmerapp.models

data class User(val name: String? = "",
                val email: String? = "",
                val phone: String? = "",
                val password: String? = "",
                val address: String? = "",
                val profileImage: String? = "",
                val city: String = "",
                val state: String = "",
                val pinCode: String = "", )
