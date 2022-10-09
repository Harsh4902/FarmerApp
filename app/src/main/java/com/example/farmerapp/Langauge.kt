package com.example.farmerapp

import android.content.Intent
import android.content.res.Configuration
import android.content.res.Resources
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.bumptech.glide.load.engine.Resource
import kotlinx.android.synthetic.main.activity_langauge.*
import java.util.*

class Langauge : AppCompatActivity() {

    lateinit var language : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_langauge)
        supportActionBar?.hide()

        rgLanguage.setOnCheckedChangeListener() { group, checkedId ->
            if (checkedId == R.id.rbEnglish) {
                Toast.makeText(this, "English", Toast.LENGTH_SHORT).show()
                language = "en"
                setLocale(language)

            } else if (checkedId == R.id.rbHindi) {
                Toast.makeText(this, "Hindi", Toast.LENGTH_SHORT).show()
                language = "hi"
                setLocale(language)
            }
            else{
                Toast.makeText(this,"Gujarati",Toast.LENGTH_SHORT).show()
                language = "gu"
                setLocale(language)
            }
        }
    }

    private fun setLocale(language: String) {
        val locale = Locale(language)
        Locale.setDefault(locale)
        val config = Configuration()
        config.locale = locale
        baseContext.resources.updateConfiguration(config, baseContext.resources.displayMetrics)
        val intent = Intent(this, HomeScreenActivity::class.java)
        Toast.makeText(this, "Language Changed ", Toast.LENGTH_SHORT).show()
        intent.putExtra("language",language)
        startActivity(intent)
        finish()
    }
}