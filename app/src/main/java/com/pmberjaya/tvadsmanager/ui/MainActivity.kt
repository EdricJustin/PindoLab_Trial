package com.pmberjaya.tvadsmanager.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.pmberjaya.tvadsmanager.R
import com.pmberjaya.tvadsmanager.databinding.ActivityMainBinding
import com.pmberjaya.tvadsmanager.ui.beranda.BerandaFragment
import com.pmberjaya.tvadsmanager.ui.user.Login
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        switchFragment()

        binding.bottomToolbar.setOnNavigationItemSelectedListener{
            val intent = Intent(this, Login::class.java)
            startActivity(intent)
            true
        }

    }

    private fun switchFragment(){
        var fragment : Fragment?= null
        fragment = BerandaFragment()

        val fragmentTransaction : FragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragment_container, fragment)
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()

    }

}