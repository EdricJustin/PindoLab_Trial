package com.pmberjaya.tvadsmanager.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.pmberjaya.tvadsmanager.R
import com.pmberjaya.tvadsmanager.databinding.ActivityMainBinding
import com.pmberjaya.tvadsmanager.ui.beranda.BerandaFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        switchFragment()

//        startActivity(Intent(this,Login::class.java))
//        //startActivity(Intent(this,Login::class.java))
//        finish()

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