package com.eyepax.newsapp.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.eyepax.newsapp.NewsDetailFullView
import com.eyepax.newsapp.databinding.LatestNewsListItemBinding
import com.eyepax.newsapp.models.Article


class BreakingNewsAdapter() : RecyclerView.Adapter<BreakingNewsAdapter.BreakingNewsViewHolder>() {

    inner class BreakingNewsViewHolder(val binding: LatestNewsListItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BreakingNewsViewHolder {
        val binding = LatestNewsListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BreakingNewsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BreakingNewsViewHolder, position: Int) {

        val article = differ.currentList[position]

        holder.binding.tvBreakingNWPublisher.text = article.author
        holder.binding.tvBreakingNWDes.text = article.description
        holder.binding.tvBreakingNWTitle.text = article.title
        Glide.with(holder.itemView.context).load(article.urlToImage).into(holder.binding.ivBreakingNWImage)

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