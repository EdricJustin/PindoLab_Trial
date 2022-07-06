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
import com.google.android.material.tabs.TabLayout
import com.pmberjaya.tvadsmanager.R
import com.pmberjaya.tvadsmanager.cache.model.Status
import com.pmberjaya.tvadsmanager.databinding.LayananActivityBinding
import com.pmberjaya.tvadsmanager.util.Utility
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class Layanan : AppCompatActivity() {
    private val layananViewModel: LayananViewModel by viewModels()
    private lateinit var binding : LayananActivityBinding
    var type = "1"
    var categoryId = "1"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = LayananActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Utility.initToolbar(this, binding.toolbarLayanan.toolbar, "Layanan", null)

        binding.tablayoutLayanan.addTab(binding.tablayoutLayanan.newTab().setText("Layanan"))
        binding.tablayoutLayanan.addTab(binding.tablayoutLayanan.newTab().setText("Paket"))
        if(intent.getBooleanExtra("isfromAllPackage", false)) {
            var tab : TabLayout.Tab ?= null
            tab = binding.tablayoutLayanan.getTabAt(1)
            tab?.select()
            type = "2"
            binding.layananKategoriText.visibility = View.GONE
            binding.recylerviewLayananKategori.visibility = View.GONE
            layananViewModel.getLayananServiceData(categoryId, type)
        }
        binding.tablayoutLayanan.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                if(binding.tablayoutLayanan.selectedTabPosition == 0){
                    type = "1"
                    binding.layananKategoriText.visibility = View.VISIBLE
                    binding.recylerviewLayananKategori.visibility = View.VISIBLE
                    layananViewModel.getLayananServiceData(categoryId, type)
                }else if(binding.tablayoutLayanan.selectedTabPosition == 1){
                    type = "2"
                    binding.layananKategoriText.visibility = View.GONE
                    binding.recylerviewLayananKategori.visibility = View.GONE
                    layananViewModel.getLayananServiceData(categoryId, type)
                }
            }
            override fun onTabUnselected(tab: TabLayout.Tab) {}
            override fun onTabReselected(tab: TabLayout.Tab) {}
        })

        initObserver()
        layananViewModel.getLayananKategoriData()

        val kategori_manager = GridLayoutManager(this, 1, GridLayoutManager.HORIZONTAL, false)
        binding.recylerviewLayananKategori.layoutManager = kategori_manager

        val layanan_manager = GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false)
        binding.recylerviewLayananKategoriService.layoutManager = layanan_manager

    }

    private fun initObserver() {
        layananViewModel.layanankategoriLiveData.observe(this, Observer {
            when (it.status) {
                Status.LOADING -> {
                    binding.layananKategoriLoading.loading.visibility = View.VISIBLE
                    binding.layananKategoriError.error.visibility = View.GONE
                    binding.layananKategoriText.visibility = View.GONE
                    binding.tablayoutLayanan.visibility = View.GONE
                }
                Status.SUCCESS -> {
                    binding.layananKategoriLoading.loading.visibility = View.GONE
                    if(type == "1"){
                        binding.layananKategoriText.visibility = View.VISIBLE
                        binding.recylerviewLayananKategori.visibility = View.VISIBLE
                    }

                    binding.tablayoutLayanan.visibility = View.VISIBLE

                    // jika menggunakan cara adapter == null maka kelemahannya adalah ketika server update dan user retry pada saat yang bersamaan maka update yang dilakukan server tidak ada muncul di tempat user ini disebabkan karena retry tidak lagi triggered adapter melainkan hanya load ulang data
                    if(binding.recylerviewLayananKategori.adapter == null){
                        binding.recylerviewLayananKategori.adapter = KategoriAdapter(it.data!!, KategoriAdapter.OnClickListener{
                            categoryId = it.id!!
                            layananViewModel.getLayananServiceData(categoryId, type)
                        })
                    }
                    layananViewModel.getLayananServiceData(categoryId, type)
                }
                Status.ERROR -> {
                    binding.layananKategoriError.error.visibility = View.VISIBLE
                    binding.layananKategoriLoading.loading.visibility = View.GONE
                    binding.layananKategoriServiceLoading.loading.visibility = View.GONE

                    binding.layananKategoriError.errorButton.setOnClickListener{
                        layananViewModel.getLayananKategoriData()
                    }

                    Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                }
                Status.UNAUTHORIZED -> {
                    Toast.makeText(this, "401", Toast.LENGTH_LONG).show()
                }
            }
        })
        layananViewModel.layananserviceLiveData.observe(this, Observer {
            when (it.status) {
                Status.LOADING -> {
                    binding.layananKategoriServiceLoading.loading.visibility = View.VISIBLE
                    binding.recylerviewLayananKategoriService.visibility = View.GONE
                    binding.layananKategoriServicePlaceHolder.visibility = View.GONE
                }
                Status.SUCCESS -> {
                    binding.layananKategoriServiceLoading.loading.visibility = View.GONE

                    if(it.data!!.isEmpty()){
                        binding.recylerviewLayananKategoriService.visibility = View.GONE
                        binding.layananKategoriServicePlaceHolder.visibility = View.VISIBLE
                    }else{
                        binding.recylerviewLayananKategoriService.visibility = View.VISIBLE
                        binding.layananKategoriServicePlaceHolder.visibility = View.GONE
                        binding.recylerviewLayananKategoriService.adapter = LayananAdapter(it.data, LayananAdapter.OnClickListener {
                            if(type == "1"){
                                val intent = Intent (this, DetailLayanan::class.java)
                                intent.putExtra("layananPaket", it)
                                startActivity(intent)
                            }else if (type == "2"){
                                val intent = Intent(this, DetailPaket::class.java)
                                intent.putExtra("id", it.id)
                                startActivity(intent)
                            }
                        })
                    }
                }
                Status.ERROR -> {
                    binding.layananKategoriError.error.visibility = View.VISIBLE
                    binding.layananKategoriText.visibility = View.GONE
                    binding.tablayoutLayanan.visibility = View.GONE
                    binding.recylerviewLayananKategori.visibility = View.GONE
                    binding.layananKategoriLoading.loading.visibility = View.GONE
                    binding.layananKategoriServiceLoading.loading.visibility = View.GONE

                    binding.layananKategoriError.errorButton.setOnClickListener{
                        layananViewModel.getLayananKategoriData()
                    }

                    Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                }
                Status.UNAUTHORIZED -> {
                    Toast.makeText(this, "401", Toast.LENGTH_LONG).show()
                }
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.layanan_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }
}
