package com.maliarrayandev.newsapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.maliarrayandev.newsapi.databinding.ActivityMainBinding
import com.maliarrayandev.newsapi.model.ResponseNews
import com.maliarrayandev.newsapi.service.RetrofitBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var adapterRv: CdvNewsHeadlineAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        adapterRv = CdvNewsHeadlineAdapter()
        binding.run {
            setContentView(root)
            setSupportActionBar(toolBar)

            //untuk membuild Recyclerview
            mainRv.apply {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = adapterRv

            }

            val call = RetrofitBuilder.getService().fetchHeadlines()
            call.enqueue(object : Callback<ResponseNews> {
                override fun onFailure(call: Call<ResponseNews>, t: Throwable) {
                    Timber.e(t)
                    Toast.makeText(applicationContext, t.message.toString(), Toast.LENGTH_SHORT)
                }

                override fun onResponse(
                    call: Call<ResponseNews>,
                    response: Response<ResponseNews>
                ) {
                    response.body()?.articles?.let {
                        adapterRv.addData(it)
                    }
                }
            })
        }
    }}

