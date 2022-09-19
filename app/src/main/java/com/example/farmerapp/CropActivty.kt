package com.example.farmerapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.farmerapp.models.CropContent
import kotlinx.android.synthetic.main.activity_crop_activty.*
import kotlinx.android.synthetic.main.activity_home_screen.*
import kotlinx.android.synthetic.main.activity_home_screen.drawerLayout

class CropActivty : AppCompatActivity(), CropContentClicked {

    lateinit var toggle : ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crop_activty)

        toggle = ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        var cropList = mutableListOf(
            CropContent("Wheat"),
            CropContent("Corn"),
            CropContent("Sugarcane"),
            CropContent("Cotton"),
            CropContent("Rice"),
            CropContent("Soya"),
            CropContent("Millet"),
            CropContent("Sorghum")
        )

        rvCrop.layoutManager = LinearLayoutManager(this)
        val adapter = CropContentAdapter(cropList,this)
        rvCrop.adapter = adapter


    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(toggle.onOptionsItemSelected(item)){
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onItemClicked(item: CropContent) {
        val intent = Intent(this, FinalCategoryActivity::class.java)
        startActivity(intent)
    }
}