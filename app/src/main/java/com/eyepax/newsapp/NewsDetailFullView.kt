package com.eyepax.newsapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.eyepax.newsapp.databinding.ActivityNewsDetailFullViewBinding
import com.eyepax.newsapp.models.Article

class NewsDetailFullView : AppCompatActivity() {

    private lateinit var binding: ActivityNewsDetailFullViewBinding
    private var article: Article? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityNewsDetailFullViewBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        article = intent.getSerializableExtra("article") as Article?
        if (article != null) {

            binding.tvDetailDescription.text = article!!.description
            val date = article!!.publishedAt?.chunked(10)
            binding.tvDetailPublishedDate.text = date!![0]
            binding.tvDetailTitle.text = article!!.title
            binding.tvDetailPublisher.text = article!!.author

            Glide.with(this).load(article!!.urlToImage).into(binding.ivDetailImage)


        }








//        if(article != null){
//
//            Glide.with(this).load(article.).into(holder.binding.ivNewsImage)
//            binding.
//
//        }


    }
}