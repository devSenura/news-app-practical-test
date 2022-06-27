package com.eyepax.newsapp.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.eyepax.newsapp.NewsDetailFullView
import com.eyepax.newsapp.databinding.GeneralNewsListItemBinding
import com.eyepax.newsapp.models.Article

class NewsAdapter() : RecyclerView.Adapter<NewsAdapter.ArticleViewHolder>() {

    inner class ArticleViewHolder(val binding: GeneralNewsListItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        val binding = GeneralNewsListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ArticleViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {

        val article = differ.currentList[position]

        holder.binding.tvNewsTitle
        holder.binding.tvPublisher.text = article.author
        val date = article.publishedAt?.chunked(10)
        holder.binding.tvPublishedDate.text = date!![0]
        holder.binding.tvNewsTitle.text = article.title
        Glide.with(holder.itemView.context).load(article.urlToImage).into(holder.binding.ivNewsImage)

        holder.binding.root.setOnClickListener {

            val intent = Intent(holder.itemView.context, NewsDetailFullView::class.java)
            intent.putExtra("article",article)
            holder.itemView.context?.startActivity(intent)

        }

    }

    private val differCallback = object : DiffUtil.ItemCallback<Article>() {
        override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem.url == newItem.url
        }

        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)


    override fun getItemCount(): Int {
        return differ.currentList.size
    }

}