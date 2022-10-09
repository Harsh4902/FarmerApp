package com.example.farmerapp

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.lifecycle.ViewModel
import com.example.farmerapp.apis.Api
import com.example.farmerapp.models.FileViewMode
import com.example.farmerapp.models.Question
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_otp.*
import kotlinx.android.synthetic.main.activity_question.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class QuestionActivity : AppCompatActivity() {


    lateinit var auth : FirebaseAuth

    var selectedImage : Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_question)
        var editQuestion : EditText
        var btpost : Button
        supportActionBar?.hide()

        auth = FirebaseAuth.getInstance()
        val imageView = findViewById<ImageView>(R.id.ivBack)
        editQuestion = findViewById<EditText>(R.id.etQuestion)
        btpost = findViewById<Button>(R.id.btPost)
        imageView.setOnClickListener{
            finish()
        }

        val viewModel = viewModel<FileViewMode>()


        ivAddImage.setOnClickListener {

        }

        btpost.setOnClickListener {
            val text = editQuestion.text.toString()
            Toast.makeText(this, text, Toast.LENGTH_SHORT).show()

            if (text.isEmpty()) {
                editQuestion.error = "Enter A valid Question"
            } else {

                GlobalScope.launch (Dispatchers.IO){
                    postQuestion(text)
                }

            }
        }

        ivAddImage.setOnClickListener {
            openImageChooser()
        }

    }


    private fun openImageChooser() {
        Intent(Intent.ACTION_PICK).also {
            it.type = "image/*"


        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(resultCode == Activity.RESULT_OK){

            videoView.visibility = View.GONE
            audioPost.visibility = View.GONE

            when(requestCode){
                100 -> {
                    selectedImage = data?.data
                    imgPost.setImageURI(selectedImage)

                }
            }
        }

    }

    private suspend fun postQuestion(text : String) {

        val retrofit = Retrofit.Builder()
            .baseUrl("http://192.168.145.215/ssip/public/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(Api::class.java)

        val question = Question(
            text,
            10)

        retrofit.postQuestion(question.text!!,question.createdBy).enqueue(object : retrofit2.Callback<ResponseBody> {
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                if(response.isSuccessful){
                    Toast.makeText(this@QuestionActivity, "Registered Successfully", Toast.LENGTH_SHORT).show()
                }
                else{
                    Toast.makeText(this@QuestionActivity, "Registration Failed", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                Toast.makeText(this@QuestionActivity, "Registration Failed", Toast.LENGTH_SHORT).show()
            }

        })

    }
}