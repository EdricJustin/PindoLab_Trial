package com.pmberjaya.tvadsmanager.ui.beranda

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.pmberjaya.tvadsmanager.cache.model.Status
import com.pmberjaya.tvadsmanager.databinding.FragmentBerandaBinding
import com.pmberjaya.tvadsmanager.ui.layanan.DetailLayanan
import com.pmberjaya.tvadsmanager.ui.layanan.DetailPaket
import com.pmberjaya.tvadsmanager.ui.layanan.Layanan
import com.pmberjaya.tvadsmanager.ui.news.DetailNews
import com.pmberjaya.tvadsmanager.ui.news.News
import dagger.hilt.android.AndroidEntryPoint
import okhttp3.internal.assertThreadHoldsLock

@AndroidEntryPoint
class BerandaFragment : Fragment() {
    private lateinit var binding: FragmentBerandaBinding
    private val berandaViewModel: BerandaViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBerandaBinding.inflate(layoutInflater)

        binding.menuLayananImage.setOnClickListener(){
            val intent = Intent (getActivity(), Layanan::class.java)
            getActivity()?.startActivity(intent)
        }

        binding.detailEdukasiInformasiImageButton.setOnClickListener{
            val intent = Intent(getActivity(), News::class.java)
            getActivity()?.startActivity(intent)
        }

        binding.detailPaketRekomendasiImageButton.setOnClickListener{
            val intent = Intent(getActivity(), Layanan::class.java)
            intent.putExtra("isfromAllPackage", true)
            getActivity()?.startActivity(intent)
        }

        initObserver()
        berandaViewModel.getBannerData()
        berandaViewModel.getNewsData()
        berandaViewModel.getPaketData()

        val edukasi_manager = GridLayoutManager(activity, 1, GridLayoutManager.HORIZONTAL, false)
        binding.recylerviewNews.layoutManager = edukasi_manager

        val paket_manager = GridLayoutManager(activity, 1, GridLayoutManager.HORIZONTAL, false)
        binding.recylerviewPaket.layoutManager = paket_manager

        return binding.root

    }

    private fun initObserver() {
        berandaViewModel.bannerLiveData.observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Status.LOADING -> {
                    binding.bannerLoading.loading.visibility = View.VISIBLE
                    binding.viewpagerBeranda.visibility = View.GONE
                    binding.bannerError.error.visibility = View.GONE
                }
                Status.SUCCESS -> {
                    binding.bannerLoading.loading.visibility = View.GONE
                    binding.viewpagerBeranda.visibility = View.VISIBLE
                    binding.viewpagerBeranda.adapter = BannerAdapter(it.data!!)
                }
                Status.ERROR -> {
                    binding.bannerError.error.visibility = View.VISIBLE
                    binding.bannerLoading.loading.visibility = View.GONE
                    binding.viewpagerBeranda.visibility = View.GONE

                    binding.bannerError.errorButton.setOnClickListener{
                        berandaViewModel.getBannerData()
                    }
                    Toast.makeText(requireActivity(), it.message, Toast.LENGTH_LONG).show()
                }
                Status.UNAUTHORIZED -> {
                    Toast.makeText(requireActivity(), "401", Toast.LENGTH_LONG).show()
                }
            }
        })

        berandaViewModel.newsLiveData.observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Status.LOADING -> {
                    binding.newsLoading.loading.visibility = View.VISIBLE
                    binding.newsError.error.visibility = View.GONE
                }
                Status.SUCCESS -> {
                    binding.newsLoading.loading.visibility = View.GONE

                    binding.recylerviewNews.adapter = NewsAdapter(it.data!!, NewsAdapter.OnClickListener{
                        val intent = Intent(getActivity(), DetailNews::class.java)
                        intent.putExtra("link", it.link)
                        getActivity()?.startActivity(intent)
                    })
                }
                Status.ERROR -> {
                    binding.newsError.error.visibility = View.VISIBLE
                    binding.newsLoading.loading.visibility = View.GONE

                    binding.newsError.errorButton.setOnClickListener{
                        berandaViewModel.getNewsData()
                    }
                    Toast.makeText(requireActivity(), it.message, Toast.LENGTH_LONG).show()
                }
                Status.UNAUTHORIZED -> {
                    Toast.makeText(requireActivity(), "401", Toast.LENGTH_LONG).show()
                }
            }
        })

        berandaViewModel.paketLiveData.observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Status.LOADING -> {
                    binding.paketLoading.loading.visibility = View.VISIBLE
                    binding.paketError.error.visibility = View.GONE
                }
                Status.SUCCESS -> {
                    binding.paketLoading.loading.visibility = View.GONE

                    binding.recylerviewPaket.adapter = PaketAdapter(it.data!!, PaketAdapter.OnClickListener{
                        val intent = Intent(getActivity(), DetailPaket::class.java)
                        intent.putExtra("layananPaket", it)
                        getActivity()?.startActivity(intent)
                    })

                }
                Status.ERROR -> {
                    binding.paketError.error.visibility = View.VISIBLE
                    binding.paketLoading.loading.visibility = View.GONE

                    binding.paketError.errorButton.setOnClickListener{
                        berandaViewModel.getPaketData()
                    }
                    Toast.makeText(requireActivity(), it.message, Toast.LENGTH_LONG).show()
                }
                Status.UNAUTHORIZED -> {
                    Toast.makeText(requireActivity(), "401", Toast.LENGTH_LONG).show()
                }
            }
        })
    }
}