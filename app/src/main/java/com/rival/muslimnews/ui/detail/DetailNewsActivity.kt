package com.rival.muslimnews.ui.detail

import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import com.rival.muslimnews.R
import com.rival.muslimnews.data.model.ArticlesItem
import com.rival.muslimnews.databinding.ActivityDetailNewsBinding
import com.squareup.picasso.Picasso

class DetailNewsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailNewsBinding

    companion object {
        const val DATA_EXTRA = "data_extra"
        const val DATE_TIME = "date_time"
    }

    private var data: ArticlesItem? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailNewsBinding.inflate(layoutInflater)
        setSupportActionBar(binding.toolbarDetail)
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            title = getString(R.string.news_detail)
        }
        setContentView(binding.root)

        val dataNews = intent.getParcelableExtra<ArticlesItem>(DATA_EXTRA)
        val dateTime = intent.getStringExtra(DATE_TIME)
        data = dataNews
        binding.apply {
            detailTitle.text = dataNews!!.title
            detailAuthor.text = dataNews.author
            detailPublishedAt.text = dateTime

            Picasso.get()
                .load(dataNews.urlToImage)
                .fit()
                .centerInside()
                .placeholder(R.drawable.ic_logo)
                .into(detailImage)
            setWebView(dataNews)
        }
    }

    private fun setWebView(dataNews: ArticlesItem?) {
        val webSettings = binding.wvDetail.settings
        webSettings.mixedContentMode = WebSettings.MIXED_CONTENT_ALWAYS_ALLOW

        binding.wvDetail.webViewClient = object : WebViewClient() {
            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                super.onPageStarted(view, url, favicon)
                binding.loadingView.root.visibility = View.VISIBLE
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                binding.loadingView.root.visibility = View.GONE
            }
        }

        dataNews?.url?.let { binding.wvDetail.loadUrl(it) }
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_detail, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }

            R.id.menu_favorite_detail -> {
                val githubUrl = "https://github.com/emRival"
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(githubUrl))
                startActivity(intent)
                true
            }

            R.id.menu_share_detail -> {
                val shareIntent = Intent()
                shareIntent.action = Intent.ACTION_SEND
                shareIntent.putExtra(
                    Intent.EXTRA_TEXT,
                    "Yuk Baca berita berikut! \n *${data?.title}*  \n${data?.url}"
                )
                shareIntent.type = "text/plain"
                startActivity(Intent.createChooser(shareIntent, "Share to: "))
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }
}