package com.example.farmerapp

import HomeContentAdapter
import HomeContentClicked
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.farmerapp.models.HomeContent
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_home_screen.*

class HomeScreenActivity : AppCompatActivity(), HomeContentClicked {

    lateinit var toggle : ActionBarDrawerToggle
    private lateinit var auth : FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_screen)

        toggle = ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        auth = FirebaseAuth.getInstance()

        var contentList = mutableListOf(
            HomeContent("Crop","Ask your query related to Crop",""),
            HomeContent("Credit","Ask your query related to Credit",""),
            HomeContent("Scheme","Ask your query related to Scheme",""),
            HomeContent("Fertilizer","Ask your query related to Fertilizer",""),
            HomeContent("Soil","Ask your query related to Soil",""),
            HomeContent("Irrigation","Ask your query related to Irrigation","")
        )

        val adapter = HomeContentAdapter(contentList, this)
        rvHome.adapter = adapter
        rvHome.layoutManager = LinearLayoutManager(this)

        navView.setNavigationItemSelectedListener {
            when(it.itemId){
                R.id.miItem1 -> Toast.makeText(applicationContext, "Home", Toast.LENGTH_SHORT).show()
                R.id.miItem2 -> Toast.makeText(applicationContext, "Profile", Toast.LENGTH_SHORT).show()
                R.id.miItem3 -> logout()
            }

            true
        }


    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if(toggle.onOptionsItemSelected(item)){
            return true
        }

        return super.onOptionsItemSelected(item)
    }

    override fun onItemClick(item: HomeContent) {
        if(item.title == "Crop"){

            val intent = Intent(this,CropActivty::class.java)
            startActivity(intent)
        }
    }

    private fun logout(){
        auth.signOut()
        finish()
        startActivity(Intent(this,LoginActivity::class.java))
    }

}