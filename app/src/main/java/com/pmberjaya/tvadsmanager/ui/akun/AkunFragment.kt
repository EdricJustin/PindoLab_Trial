package com.pmberjaya.tvadsmanager.ui.akun

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.pmberjaya.tvadsmanager.databinding.FragmentAkunBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AkunFragment : Fragment() {
    private lateinit var binding: FragmentAkunBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAkunBinding.inflate(layoutInflater)
        return binding.root
    }
}