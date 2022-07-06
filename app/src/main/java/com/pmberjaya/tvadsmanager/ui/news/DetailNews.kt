package com.pmberjaya.tvadsmanager.ui.news

import android.os.Bundle
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import com.pmberjaya.tvadsmanager.databinding.DetailnewsActivityBinding
import com.pmberjaya.tvadsmanager.util.Utility
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailNews : AppCompatActivity() {
    private lateinit var binding : DetailnewsActivityBinding
    var link = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DetailnewsActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Utility.initToolbar(this, binding.toolbarDetailNews.toolbar, "Edukasi Kesehatan", null)

        link = intent.getStringExtra("link")!!

        binding.detailNewsWebView.webViewClient = WebViewClient()
        binding.detailNewsWebView.loadUrl(link)
    }

    inner class WebViewClient : android.webkit.WebViewClient(){
        override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
            view.loadUrl(url)
            return false
        }

        override fun onPageFinished(view: WebView?, url: String?) {
            super.onPageFinished(view, url)
            binding.detailNewsLoading.loading.visibility = View.GONE
            binding.detailNewsWebView.visibility = View.VISIBLE
        }
    }
}