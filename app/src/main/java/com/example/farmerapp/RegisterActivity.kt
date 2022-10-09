package com.example.farmerapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.farmerapp.apis.Api
import com.example.farmerapp.models.User
import kotlinx.android.synthetic.main.activity_register.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response

class RegisterActivity : AppCompatActivity() {

    lateinit var user: User

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        supportActionBar?.hide()




        btSubmit.setOnClickListener {

            val name = etName.text.trim().toString()
            val phone = etPhone.text.trim().toString()
            val village = etVillage.text.trim().toString()
            val taluka = etTaluka.text.trim().toString()

            if(name.isEmpty() || phone.isEmpty() || village.isEmpty() || taluka.isEmpty()) {
                Toast.makeText(this, "Please fill all the fields", Toast.LENGTH_SHORT).show()
            }
            else{
                user = User(name, phone, village, taluka)
                GlobalScope.launch(Dispatchers.IO) {
                    registerFarmer()
                }
            }
        }
    }

    private suspend fun registerFarmer() {
        val retIn = RetrofitInstance.getRetrofitInstance().create(Api::class.java)


        retIn.registerFarmer(user.name!!,user.phone!!,user.village!!,user.taluka!!).enqueue(object : retrofit2.Callback<ResponseBody> {
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