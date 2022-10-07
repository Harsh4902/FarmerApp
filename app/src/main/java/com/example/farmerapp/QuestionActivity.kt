package com.example.farmerapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView

class QuestionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_question)

        supportActionBar?.hide()
        val imageView = findViewById<ImageView>(R.id.ivBack)

        imageView.setOnClickListener{
            finish()
        }

    }
}