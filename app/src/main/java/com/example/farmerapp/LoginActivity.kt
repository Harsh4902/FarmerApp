package com.example.farmerapp

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.Toast
import com.google.firebase.FirebaseException
import com.google.firebase.FirebaseTooManyRequestsException
import com.google.firebase.auth.*
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_login.*
import java.util.concurrent.TimeUnit

class LoginActivity : AppCompatActivity() {

    lateinit var mobileNo : String
    lateinit var progressBar : ProgressBar
    lateinit var auth : FirebaseAuth

    private var storedVerificationId: String? = ""
    private lateinit var resendToken: PhoneAuthProvider.ForceResendingToken

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        supportActionBar?.hide()

        init()

        btSubmit.setOnClickListener {

            mobileNo = etOtp.text.trim().toString()
            if(mobileNo.length == 10){
                mobileNo = "+91$mobileNo"
                progressBar.visibility = View.VISIBLE
                btSubmit.isClickable = false
                val options = PhoneAuthOptions.newBuilder(auth)
                    .setPhoneNumber(mobileNo)       // Phone number to verify
                    .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
                    .setActivity(this)                 // Activity (for callback binding)
                    .setCallbacks(callbacks)          // OnVerificationStateChangedCallbacks
                    .build()
                PhoneAuthProvider.verifyPhoneNumber(options)

            }
            else{
                Toast.makeText(this,"Enter Valid Mobile Number", Toast.LENGTH_SHORT).show()
            }
        }

        tvRegister.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }

    }

    val callbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

        override fun onVerificationCompleted(credential: PhoneAuthCredential) {
            // This callback will be invoked in two situations:
            // 1 - Instant verification. In some cases the phone number can be instantly
            //     verified without needing to send or enter a verification code.
            // 2 - Auto-retrieval. On some devices Google Play services can automatically
            //     detect the incoming verification SMS and perform verification without
            //     user action.
            Log.d(TAG, "onVerificationCompleted:$credential")
            signInWithPhoneAuthCredential(credential)
        }

        override fun onVerificationFailed(e: FirebaseException) {
            // This callback is invoked in an invalid request for verification is made,
            // for instance if the the phone number format is not valid.
            Log.w(TAG, "onVerificationFailed", e)

            if (e is FirebaseAuthInvalidCredentialsException) {
                // Invalid request
            } else if (e is FirebaseTooManyRequestsException) {
                // The SMS quota for the project has been exceeded
            }

            // Show a message and update the UI
        }

        override fun onCodeSent(
            verificationId: String,
            token: PhoneAuthProvider.ForceResendingToken
        ) {

            val intent = Intent(this@LoginActivity, OtpActivity::class.java)
            storedVerificationId = verificationId
            resendToken = token

            intent.putExtra("OTP",storedVerificationId)
            intent.putExtra("resendToken",resendToken)
            intent.putExtra("mobileNo",mobileNo)
            startActivity(intent)
            progressBar.visibility = View.GONE
        }
    }

    override fun onStart() {
        super.onStart()
        if(auth.currentUser != null){
            val intent = Intent(this@LoginActivity, HomeScreenActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun init(){

        progressBar = findViewById(R.id.pbLogin)
        progressBar.visibility = View.GONE
        auth = FirebaseAuth.getInstance()
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
                progressBar.visibility = View.INVISIBLE
            }
    }

    private fun sendToMain(){
        startActivity(Intent(this , HomeScreenActivity::class.java))
    }

}