package com.example.farmerapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.farmerapp.models.User
import kotlinx.android.synthetic.main.activity_register.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import okhttp3.Dispatcher
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.create

class RegisterActivity : AppCompatActivity() {

    lateinit var user: User

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        supportActionBar?.hide()

        val name = etName.text.trim().toString()
        val phone = etPhone.text.trim().toString()
        val village = etVillage.text.trim().toString()




        btSubmit.setOnClickListener {
            if(name.isEmpty() || phone.isEmpty() || village.isEmpty() ){
                Toast.makeText(this, "Please fill all the fields", Toast.LENGTH_SHORT).show()
            }
            else{
                user = User(name, phone, village)
                GlobalScope.launch(Dispatchers.IO) {
                    registerFarmer()
                }
            }
        }

    }

    private suspend fun registerFarmer() {
        val retIn = RetrofitInstance.getRetrofitInstance().create(Api::class.java)


        retIn.registerFarmer(user.name!!,user.phone!!,user.village!!).enqueue(object : retrofit2.Callback<ResponseBody> {
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                if(response.isSuccessful){
                    Toast.makeText(this@RegisterActivity, "Registered Successfully", Toast.LENGTH_SHORT).show()
                }
                else{
                    Toast.makeText(this@RegisterActivity, "Registration Failed", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                Toast.makeText(this@RegisterActivity, "Registration Failed", Toast.LENGTH_SHORT).show()
            }

        })
    }


}