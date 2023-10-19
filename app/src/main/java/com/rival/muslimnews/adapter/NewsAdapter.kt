package com.rival.muslimnews.adapter

import android.annotation.SuppressLint
import android.content.Intent
import android.icu.text.SimpleDateFormat
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.rival.muslimnews.R
import com.rival.muslimnews.data.model.ArticlesItem
import com.rival.muslimnews.databinding.ItemNewsBinding
import com.rival.muslimnews.ui.detail.DetailNewsActivity
import com.squareup.picasso.Picasso
import java.util.Calendar
import java.util.Locale

class NewsAdapter : RecyclerView.Adapter<NewsAdapter.MyViewHolder>()  {

    private val listNews = ArrayList<ArticlesItem>()

    // Mengatur data berita yang akan ditampilkan di RecyclerView
    fun setData(list: List<ArticlesItem>?) {
        listNews.clear()
        if (list != null) {
            listNews.addAll(list)
        }
        notifyItemRangeChanged(0, listNews.size)
    }
    class MyViewHolder (val binding: ItemNewsBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder = MyViewHolder(
        ItemNewsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: NewsAdapter.MyViewHolder, position: Int) {
        val news = listNews[position]

        val dateTimeString = news.publishedAt
        val dateTimeFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault())
        val date = dateTimeFormat.parse(dateTimeString)
        val dateFormat = SimpleDateFormat("EEE, dd MMMM | HH:mm", Locale.getDefault())

        val newsDate = dateFormat.format(date)

        holder.binding.apply {
            tvSource.text = news.source.name
            tvTitle.text = news.title
            tvDate.text = newsDate


           Picasso.get()
               .load(news.urlToImage)
               .fit()
               .centerInside()
               .placeholder(R.drawable.ic_logo)
               .into(imgNews)
        }

        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, DetailNewsActivity::class.java)
            intent.putExtra(DetailNewsActivity.DATA_EXTRA, news)
            intent.putExtra(DetailNewsActivity.DATE_TIME, newsDate)
            holder.itemView.context.startActivity(intent)
        }

    }

    override fun getItemCount() = listNews.size
}