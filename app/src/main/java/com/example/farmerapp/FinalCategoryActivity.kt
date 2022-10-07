package com.example.farmerapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.farmerapp.models.FinalCategory
import kotlinx.android.synthetic.main.activity_final_category.*
import kotlinx.android.synthetic.main.activity_home_screen.*
import kotlinx.android.synthetic.main.activity_home_screen.drawerLayout

class FinalCategoryActivity : AppCompatActivity(), CategoryClicked {

    lateinit var toggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_final_category)

        toggle = ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val ctList = mutableListOf(
            FinalCategory("Disease"),
            FinalCategory("Pest"),
            FinalCategory("Irrigation")
        )

        rvFinal.layoutManager = LinearLayoutManager(this)
        val adapter = FcAdapter(ctList, this)
        rvFinal.adapter = adapter
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(toggle.onOptionsItemSelected(item)){
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onItemClicked(item: FinalCategory) {
        Toast.makeText(this, "Clicked on ${item.ctName}", Toast.LENGTH_SHORT).show()
        val intent = Intent(this, QuestionActivity::class.java)
        startActivity(intent)
    }

}