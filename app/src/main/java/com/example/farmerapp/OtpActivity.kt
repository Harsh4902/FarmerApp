package com.example.farmerapp

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import com.google.firebase.FirebaseException
import com.google.firebase.FirebaseTooManyRequestsException
import com.google.firebase.auth.*
import kotlinx.android.synthetic.main.activity_otp.*
import java.util.concurrent.TimeUnit

class OtpActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var storedVerificationId: String
    private lateinit var resendToken: PhoneAuthProvider.ForceResendingToken
    private lateinit var mobileNo : String
    private lateinit var progressBar : ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_otp)
        supportActionBar?.hide()

        auth = FirebaseAuth.getInstance()
        storedVerificationId = intent.getStringExtra("OTP").toString()
        resendToken = intent.getParcelableExtra("resendToken")!!
        mobileNo = intent.getStringExtra("mobileNo").toString()
        progressBar = findViewById(R.id.progressBar)

        btSubmit.setOnClickListener {
            val otp = etOtp.text.toString()
            if (otp.isNotEmpty()){
                if(otp.length == 6) {
                    val credential = PhoneAuthProvider.getCredential(storedVerificationId, otp)
                    signInWithPhoneAuthCredential(credential)
                    btSubmit.visibility = View.GONE
                    progressBar.visibility = View.VISIBLE
                }
                else{
                    etOtp.error = "Enter valid OTP"
                }
            }
            else{
                etOtp.error = "Enter OTP"
            }
        }

    }

    private fun signInWithPhoneAuthCredential(credential: PhoneAuthCredential) {
        auth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Toast.makeText(this , "Authenticate Successfully" , Toast.LENGTH_SHORT).show()
                    sendToMain()
                } else {
                    // Sign in failed, display a message and update the UI
                    Log.d("TAG", "signInWithPhoneAuthCredential: ${task.exception.toString()}")
                    if (task.exception is FirebaseAuthInvalidCredentialsException) {
                        // The verification code entered was invalid
                    }
                    // Update UI
                }
            }
    }

    private fun sendToMain(){
        startActivity(Intent(this , HomeScreenActivity::class.java))
        finish()
    }

}