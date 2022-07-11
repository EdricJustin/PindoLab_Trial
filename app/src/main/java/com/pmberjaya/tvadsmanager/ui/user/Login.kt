package com.pmberjaya.tvadsmanager.ui.user

import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.pmberjaya.tvadsmanager.R
import com.pmberjaya.tvadsmanager.cache.sharedpref.PreferencesHelper
import com.pmberjaya.tvadsmanager.databinding.LoginActivityBinding
import com.pmberjaya.tvadsmanager.util.Utility
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class Login : AppCompatActivity() {
    private val loginViewModel: LoginViewModel by viewModels()
    private lateinit var binding: LoginActivityBinding

    @Inject
    lateinit var preferencesHelper: PreferencesHelper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = LoginActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Utility.initToolbar(this, binding.toolbarLogin, "", null)

        initObserver()

        /*if(binding.passwordEditText.getTransformationMethod().equals(PasswordTransformationMethod.getInstance())){
            binding.passwordVisibility.setImageResource(R.drawable.ic_baseline_visibility_off_24)
            binding.passwordEditText.setTransformationMethod(HideReturnsTransformationMethod.getInstance())
        }else{
            binding.passwordVisibility.setImageResource(R.drawable.ic_baseline_visibility_24)
            binding.passwordEditText.setTransformationMethod(PasswordTransformationMethod.getInstance())
        }*/

        /*binding.btnLogin.setOnClickListener {
            loginViewModel.postLogin("huangedwin123@gmail.com", "test12345")
        }
        if (preferencesHelper.isLogin) {
            loginViewModel.getUserData()
        }*/
    }

    private fun initObserver() {
        /*loginViewModel.loginLiveData.observe(this, Observer {
            when (it.status) {
                Status.LOADING -> {
                    binding.pgLogin.visibility = View.VISIBLE
                }
                Status.SUCCESS -> {
                    binding.pgLogin.visibility = View.GONE
                    if (it.data?.isActive == 1 && it.data.isVerified == 1) {
                        loginViewModel.setAccessKey(it.data?.accessToken ?: "")
                        val userData = UserData(
                            it.data.id,
                            it.data.name,
                            it.data.phone,
                            it.data.email,
                            it.data.isActive,
                            it.data.isVerified,
                            it.data.isOwner,
                            it.data.projectsId,
                            it.data.createdAt,
                            it.data.updatedAt,
                            it.data.tokenType,
                            it.data.expiresIn,
                            it.data.accessToken,
                            it.data.coverPath
                        )
                        preferencesHelper.saveAccount(userData, Constants.LOGIN_USER)
                        preferencesHelper.isLogin = true
                        loginViewModel.getUserData()
                    }
                }

                Status.ERROR -> {
                    Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
                }
            }
        })
        loginViewModel.userLiveData.observe(this, Observer {
            when (it.status) {
                Status.LOADING -> {
                    binding.pgUserDetail.visibility = View.VISIBLE
                }
                Status.SUCCESS -> {
                    binding.pgUserDetail.visibility = View.GONE
                    binding.tvName.text = it.data?.name!!
                }
                Status.ERROR -> {
                    Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
                }
                Status.UNAUTHORIZED -> {
                    Toast.makeText(this, "401", Toast.LENGTH_SHORT).show()
                }
            }
        })*/
    }

}