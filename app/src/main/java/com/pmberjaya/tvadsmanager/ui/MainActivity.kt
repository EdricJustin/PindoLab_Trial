package com.pmberjaya.tvadsmanager.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.pmberjaya.tvadsmanager.R
import com.pmberjaya.tvadsmanager.databinding.ActivityMainBinding
import com.pmberjaya.tvadsmanager.ui.akun.AkunFragment
import com.pmberjaya.tvadsmanager.ui.beranda.BerandaFragment
import com.pmberjaya.tvadsmanager.ui.pesan.PesanFragment
import com.pmberjaya.tvadsmanager.ui.transaksi.TransaksiFragment
import com.pmberjaya.tvadsmanager.ui.user.Login
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    private val berandaFragment = BerandaFragment()
    private val transkasiFragment = TransaksiFragment()
    private val pesanFragment = PesanFragment()
    private val akunFragment = AkunFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        replaceFragment(berandaFragment)

        /*binding.bottomToolbar.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.nav_beranda -> replaceFragment(berandaFragment)
                R.id.nav_transaksi -> replaceFragment(transkasiFragment)
                R.id.nav_pesan -> replaceFragment(pesanFragment)
                R.id.nav_akun -> replaceFragment(akunFragment)
            }
            true
        }*/

        binding.bottomToolbar.setOnNavigationItemSelectedListener{
            val intent = Intent(this, Login::class.java)
            startActivity(intent)
            true
        }

    }

    private fun replaceFragment(fragment: Fragment){
        if(fragment != null){
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fragment_container, fragment)
            transaction.commit()
        }
    }

}