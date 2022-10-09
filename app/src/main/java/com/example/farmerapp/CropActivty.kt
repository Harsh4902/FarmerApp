package com.example.farmerapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.example.farmerapp.apis.Api
import com.example.farmerapp.models.CropContent
import com.example.farmerapp.models.FinalCategory
import kotlinx.android.synthetic.main.activity_crop_activty.*
import kotlinx.android.synthetic.main.activity_home_screen.*
import kotlinx.android.synthetic.main.activity_home_screen.drawerLayout
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import okhttp3.Callback
import okhttp3.Dispatcher
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class CropActivty : AppCompatActivity(), CropContentClicked {

    lateinit var ln : String
    lateinit var cropContent: List<CropContent>
    lateinit var adapter: CropContentAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crop_activty)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        ln = intent.getStringExtra("language").toString()
        rvCrop.layoutManager = GridLayoutManager(this,3)
        fetchData()


    }

    private fun fetchData() {

        val retrofit = Retrofit.Builder()
            .baseUrl("http://192.168.145.215/ssip/public/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(Api::class.java)

        val call = retrofit.getPost(ln)

        call.enqueue(object : retrofit2.Callback<List<CropContent>> {
            override fun onResponse(
                call: Call<List<CropContent>>,
                response: Response<List<CropContent>>
            ) {
                if (response.isSuccessful) {
                    cropContent = response.body()!!
                    adapter = CropContentAdapter(cropContent, this@CropActivty)
                    adapter.notifyDataSetChanged()
                    rvCrop.adapter = adapter

                }
            }

            override fun onFailure(call: Call<List<CropContent>>, t: Throwable) {
                Toast.makeText(this@CropActivty, "${t.message}", Toast.LENGTH_SHORT).show()
            }

        })

    }


    override fun onItemClicked(item: CropContent) {
        val intent = Intent(this, FinalCategoryActivity::class.java)
        intent.putExtra("cropId", item.id.toString())
        intent.putExtra("ln",ln)
        startActivity(intent)
    }
}