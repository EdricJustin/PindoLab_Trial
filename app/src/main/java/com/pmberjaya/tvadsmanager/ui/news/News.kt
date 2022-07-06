package com.pmberjaya.tvadsmanager.ui.news

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.pmberjaya.tvadsmanager.cache.model.Status
import com.pmberjaya.tvadsmanager.databinding.NewsActivityBinding
import com.pmberjaya.tvadsmanager.util.Utility
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class News : AppCompatActivity() {
    private lateinit var binding : NewsActivityBinding
    private val newsViewModel: NewsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = NewsActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Utility.initToolbar(this, binding.toolbarNews.toolbar, "Edukasi Kesehatan", null)

        initObserver()
        newsViewModel.getAllNewsData()

        val manager = GridLayoutManager(this, 1, GridLayoutManager.VERTICAL, false)
        binding.recylerviewAllNews.layoutManager = manager
    }

    private fun initObserver() {
        newsViewModel.allnewsLiveData.observe(this, Observer {
            when (it.status) {
                Status.LOADING -> {
                    binding.allNewsLoading.loading.visibility = View.VISIBLE
                    binding.allNewsError.error.visibility = View.GONE
                }
                Status.SUCCESS -> {
                    binding.allNewsLoading.loading.visibility = View.GONE
                    binding.recylerviewAllNews.visibility = View.VISIBLE

                    binding.recylerviewAllNews.adapter = AllNewsAdapter(it.data!!, AllNewsAdapter.OnClickListener{
                        val intent = Intent(this, DetailNews::class.java)
                        intent.putExtra("link", it.link)
                        startActivity(intent)
                    })
                }
                Status.ERROR -> {
                    binding.allNewsError.error.visibility = View.VISIBLE
                    binding.allNewsLoading.loading.visibility = View.GONE
                    binding.recylerviewAllNews.visibility = View.GONE

                    binding.allNewsError.errorButton.setOnClickListener{
                        newsViewModel.getAllNewsData()
                    }

                    Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                }
                Status.UNAUTHORIZED -> {
                    Toast.makeText(this, "401", Toast.LENGTH_LONG).show()
                }
            }
        })
    }
}