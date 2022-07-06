package com.pmberjaya.tvadsmanager.ui.layanan

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.pmberjaya.tvadsmanager.R
import com.pmberjaya.tvadsmanager.api.network.model.LayananPaket
import com.pmberjaya.tvadsmanager.cache.model.Status
import com.pmberjaya.tvadsmanager.databinding.DetailpaketActivityBinding
import com.pmberjaya.tvadsmanager.util.Utility
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailPaket: AppCompatActivity(){
    private val layananViewModel: LayananViewModel by viewModels()
    private lateinit var binding : DetailpaketActivityBinding
    var id = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DetailpaketActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Utility.initToolbar(this, binding.toolbarDetailPaket.toolbar, "Paket", null)

        initObserver()

        var bundle = intent.getParcelableExtra<LayananPaket>("layananPaket")
        if(bundle != null){
            setData(bundle)
        }else{
            id = intent.getStringExtra("id")!!
            layananViewModel.getPaketDetailData(id)
        }

        val manager = GridLayoutManager(this, 1, GridLayoutManager.HORIZONTAL, false)
        binding.recylerviewDetailPaketPemeriksaan.layoutManager = manager
    }

    private fun initObserver() {
        layananViewModel.paketdetailLiveData.observe(this, Observer {
            when (it.status) {
                Status.LOADING -> {
                    binding.detailPaketLoading.loading.visibility = View.VISIBLE
                    binding.detailPaketError.error.visibility = View.GONE
                    binding.detailPaketScrollView.visibility = View.GONE
                    binding.tambahButton.visibility = View.GONE
                }
                Status.SUCCESS -> {
                    binding.detailPaketLoading.loading.visibility = View.GONE
                    binding.detailPaketScrollView.visibility = View.VISIBLE
                    binding.tambahButton.visibility = View.VISIBLE

                    setData(it.data!!)
                }
                Status.ERROR -> {
                    binding.detailPaketError.error.visibility = View.VISIBLE
                    binding.detailPaketLoading.loading.visibility = View.GONE
                    binding.detailPaketScrollView.visibility = View.GONE

                    binding.detailPaketError.errorButton.setOnClickListener{
                        layananViewModel.getPaketDetailData(id)
                    }

                    Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                }
                Status.UNAUTHORIZED -> {
                    Toast.makeText(this, "401", Toast.LENGTH_LONG).show()
                }
            }
        })
    }

    fun setData(bundle : LayananPaket){
        Glide.with(this)
            .load(bundle.cover_image_link)
            .apply(
                RequestOptions()
                    .placeholder(R.drawable.ic_no_images))
            .into(binding.detailPaketImage)

        binding.detailPaketNameText.text = bundle.name
        binding.detailPaketPriceText.text = Utility.convertPrice(bundle.price)

        binding.detailPaketDeskripsi.text = bundle.description
        binding.detailPaketDeskripsiLayout.setOnClickListener{
            showhideView(binding.detailPaketDeskripsi)
        }

        binding.detailPaketPemeriksaanLayout.setOnClickListener{
            showhideView(binding.recylerviewDetailPaketPemeriksaan)
        }
        binding.recylerviewDetailPaketPemeriksaan.adapter = DetailPaketAdapter(bundle.package_link, DetailPaketAdapter.OnClickListener{
            val intent = Intent (this, DetailLayanan::class.java)
            intent.putExtra("layananPaket", it.service)
            intent.putExtra("isfromPackage", true)
            startActivity(intent)
        })

        if(bundle.benefit == null){
            binding.detailPaketManfaatLayout.visibility = View.GONE
            binding.detailPaketManfaat.visibility = View.GONE
            binding.detailPaketManfaatLine.visibility = View.GONE
        }else{
            binding.detailPaketManfaatLayout.visibility = View.VISIBLE
            binding.detailPaketManfaat.visibility = View.VISIBLE
            binding.detailPaketManfaatLine.visibility = View.VISIBLE
            binding.detailPaketManfaat.text = bundle.benefit
            binding.detailPaketManfaatLayout.setOnClickListener{
                showhideView(binding.detailPaketManfaat)
            }
        }

        if(bundle.preparation == null){
            binding.detailPaketPersiapanLayout.visibility = View.GONE
            binding.detailPaketPersiapan.visibility = View.GONE
            binding.detailPaketPersiapanLine.visibility = View.GONE
        }else{
            binding.detailPaketPersiapanLayout.visibility = View.VISIBLE
            binding.detailPaketPersiapan.visibility = View.VISIBLE
            binding.detailPaketPersiapanLine.visibility = View.VISIBLE
            binding.detailPaketPersiapan.text = bundle.preparation
            binding.detailPaketPersiapanLayout.setOnClickListener{
                showhideView(binding.detailPaketPersiapan)
            }
        }
    }

    fun showhideView(view : View){
        if(view.visibility == View.VISIBLE){
            view.visibility = View.GONE
        }else{
            view.visibility = View.VISIBLE
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.layanan_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }
}