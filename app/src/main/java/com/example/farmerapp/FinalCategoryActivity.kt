package com.example.farmerapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.farmerapp.apis.Api
import com.example.farmerapp.models.CropContent
import com.example.farmerapp.models.FinalCategory
import kotlinx.android.synthetic.main.activity_crop_activty.*
import kotlinx.android.synthetic.main.activity_final_category.*
import kotlinx.android.synthetic.main.activity_home_screen.*
import kotlinx.android.synthetic.main.activity_home_screen.drawerLayout
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class FinalCategoryActivity : AppCompatActivity(), CategoryClicked {

    lateinit var adapter: FcAdapter
    lateinit var ln : String
     var id : Int = 0
    lateinit var category: List<FinalCategory>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_final_category)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        ln = intent.getStringExtra("ln").toString()
        id = intent.getStringExtra("cropId")!!.toInt()

        rvFinal.layoutManager = LinearLayoutManager(this)
        fetchData()
    }

    private fun fetchData() {

        val retrofit = Retrofit.Builder()
            .baseUrl("http://192.168.145.215/ssip/public/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(Api::class.java)

        val call = retrofit.getCata(ln, id)

        call.enqueue(object : retrofit2.Callback<List<FinalCategory>> {
            override fun onResponse(
                call: Call<List<FinalCategory>>,
                response: Response<List<FinalCategory>>
            ) {
                if (response.isSuccessful) {
                    category = response.body()!!
                    adapter = FcAdapter(category, this@FinalCategoryActivity)
                    adapter.notifyDataSetChanged()
                    rvFinal.adapter = adapter

                }
            }

            override fun onFailure(call: Call<List<FinalCategory>>, t: Throwable) {
                Toast.makeText(this@FinalCategoryActivity, "${t.message}", Toast.LENGTH_SHORT)
                    .show()
            }

        })
    }


    override fun onItemClicked(item: FinalCategory) {
        Toast.makeText(this, "Clicked on ${item.ctName}", Toast.LENGTH_SHORT).show()
        val intent = Intent(this, QuestionActivity::class.java)
        startActivity(intent)
    }

}