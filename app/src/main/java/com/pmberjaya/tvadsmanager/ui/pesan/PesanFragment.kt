package com.pmberjaya.tvadsmanager.ui.pesan

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.pmberjaya.tvadsmanager.databinding.FragmentPesanBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PesanFragment : Fragment() {
    private lateinit var binding: FragmentPesanBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPesanBinding.inflate(layoutInflater)
        return binding.root
    }

}