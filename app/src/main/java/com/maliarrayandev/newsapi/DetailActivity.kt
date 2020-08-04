package com.maliarrayandev.newsapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import coil.api.load
import coil.size.Scale
import com.maliarrayandev.newsapi.databinding.ActivityDetailBinding
import com.maliarrayandev.newsapi.model.ArticlesItem

class DetailActivity : AppCompatActivity(){

    companion object{
        const val DETAIL_NEWS = "DETAIL_NEWS"
    }

    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)

        val data = intent.getParcelableExtra(DETAIL_NEWS) as ArticlesItem

        binding.run {
            setContentView(root)

            supportActionBar?.setDisplayHomeAsUpEnabled(true)

            title = data.title

            imgToolbar.apply {
                load(data.urlToImage){
                    scale(Scale.FILL)
                }
                contentDescription = data.description
            }
            txtContent.text = data.content

            txtDate.text = data.publishedAt
        }
    }
}