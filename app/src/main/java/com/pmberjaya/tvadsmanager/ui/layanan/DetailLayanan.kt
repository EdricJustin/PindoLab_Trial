package com.pmberjaya.tvadsmanager.ui.layanan

import android.os.Bundle
import android.view.Menu
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.pmberjaya.tvadsmanager.R
import com.pmberjaya.tvadsmanager.api.network.model.LayananPaket
import com.pmberjaya.tvadsmanager.databinding.DetaillayananActivityBinding
import com.pmberjaya.tvadsmanager.util.Utility
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailLayanan : AppCompatActivity(){
    private lateinit var binding : DetaillayananActivityBinding
    var bundle = LayananPaket()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DetaillayananActivityBinding.inflate(layoutInflater)

        if(intent.getBooleanExtra("isfromPackage", false))
            binding.tambahButton.visibility = View.GONE

        bundle = intent.getParcelableExtra("layananPaket")!!

        Glide.with(this)
            .load(bundle.cover_image_link)
            .apply(
                RequestOptions()
                    .placeholder(R.drawable.ic_no_images))
            .into(binding.detailLayananImage)
        binding.detailLayananNameText.text = bundle.name
        binding.detailLayananPriceText.text = Utility.convertPrice(bundle.price)

        binding.detailLayananDeskripsi.text = bundle.description
        binding.detailLayananDeskripsiLayout.setOnClickListener{
            showhideView(binding.detailLayananDeskripsi)
        }

        if(bundle.benefit == null){
            binding.detailLayananManfaatLayout.visibility = View.GONE
            binding.detailLayananManfaat.visibility = View.GONE
            binding.detailLayananManfaatLine.visibility = View.GONE
        }else{
            binding.detailLayananManfaatLayout.visibility = View.VISIBLE
            binding.detailLayananManfaat.visibility = View.VISIBLE
            binding.detailLayananManfaatLine.visibility = View.VISIBLE
            binding.detailLayananManfaat.text = bundle.benefit
            binding.detailLayananManfaatLayout.setOnClickListener{
                showhideView(binding.detailLayananManfaat)
            }
        }

        if(bundle.preparation == null){
            binding.detailLayananPersiapanLayout.visibility = View.GONE
            binding.detailLayananPersiapan.visibility = View.GONE
            binding.detailLayananPersiapanLine.visibility = View.GONE
        }else{
            binding.detailLayananPersiapanLayout.visibility = View.VISIBLE
            binding.detailLayananPersiapan.visibility = View.VISIBLE
            binding.detailLayananPersiapanLine.visibility = View.VISIBLE
            binding.detailLayananPersiapan.text = bundle.preparation
            binding.detailLayananPersiapanLayout.setOnClickListener{
                showhideView(binding.detailLayananPersiapan)
            }
        }

        setContentView(binding.root)

        Utility.initToolbar(this, binding.toolbarDetailLayanan.toolbar, "", null)
    }

    fun showhideView(view : View){
        if(view.visibility == View.VISIBLE){
            view.visibility = View.GONE
        }else{
            view.visibility = View.VISIBLE
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        if(intent.getBooleanExtra("isfromPackage", false))
        else{
            val inflater = menuInflater
            inflater.inflate(R.menu.layanan_menu, menu)
        }
        return super.onCreateOptionsMenu(menu)
    }
}