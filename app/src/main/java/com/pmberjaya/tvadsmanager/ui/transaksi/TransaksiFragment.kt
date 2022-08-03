package com.pmberjaya.tvadsmanager.ui.transaksi

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.pmberjaya.tvadsmanager.databinding.FragmentTransaksiBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TransaksiFragment : Fragment() {
    private lateinit var binding: FragmentTransaksiBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTransaksiBinding.inflate(layoutInflater)
        return binding.root
    }

}