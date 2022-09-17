package com.example.farmerapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        supportActionBar?.hide()

        btSubmit.setOnClickListener {
            val mobileNo = etMobile.text.toString()

            if(mobileNo=="9510884102"){
                val intent = Intent(this,OtpActivity::class.java)
                startActivity(intent)
            }
            else{
                Toast.makeText(this,"Invalid Mobile Number", Toast.LENGTH_LONG).show()
            }
        }

        btRegister.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }


    }
}