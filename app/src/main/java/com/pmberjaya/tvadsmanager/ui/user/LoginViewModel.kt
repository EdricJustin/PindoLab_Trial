package com.pmberjaya.tvadsmanager.ui.user

import android.util.Patterns
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.material.textfield.TextInputLayout
import com.pmberjaya.tvadsmanager.api.model.UserData
import com.pmberjaya.tvadsmanager.cache.model.LoginData
import com.pmberjaya.tvadsmanager.cache.model.Resource
import com.pmberjaya.tvadsmanager.cache.sharedpref.PreferencesHelper
import com.pmberjaya.tvadsmanager.repository.UserRepository
import com.pmberjaya.tvadsmanager.util.Constants.TYPE_EMAIL
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    var preferencesHelper: PreferencesHelper,
    var userRepository: UserRepository
) :
    ViewModel() {
//    fun setShowConfirmPass(){
//        showConfirmPasswordLiveData.postValue(!showConfirmPasswordLiveData.value!!)
//
//    }
//    var registerLiveData = MutableLiveData<Resource<RegisterData>>()
//
//    fun registerUser(params: HashMap<String, String>) {
//        userRepository.registerUser(params, registerLiveData)
//    }
//
//    var loginLiveData = MutableLiveData<Resource<String>>()
//
//    fun loginUser(params: HashMap<String, String>){
//        userRepository.loginUser(params,loginLiveData)
//    }
//
//    var userLiveData = MutableLiveData<Resource<UserData>>()
//
//    fun getUserData(){
//        userRepository.getUserData(userLiveData)
//    }
//
//    var forgotPassLiveData = MutableLiveData<Resource<String>>()
//
//    fun forgotPass(email : String){
//        userRepository.forgotPass(email,forgotPassLiveData)
//    }
//

    var loginLiveData: MutableLiveData<Resource<LoginData>> = MutableLiveData()
    fun postLogin(email : String?, password :String?){
        viewModelScope.launch {
            userRepository.loginUser(email,password,loginLiveData)
        }
    }

    var userLiveData = MutableLiveData<Resource<UserData>>()
    fun getUserData() {
        viewModelScope.launch {
            userRepository.getProfileUserData(userLiveData)
        }
    }
    fun setAccessKey(key : String){
        userRepository.setAccessKey(key)
    }

    fun validate(
    type: Int, text: String, textInputLayout: TextInputLayout,
    errorText: String
    ): Boolean {
        if (type == TYPE_EMAIL) {
            if (!text.isNullOrEmpty() && Patterns.EMAIL_ADDRESS.matcher(text).matches()) {
                textInputLayout.error = null
                return true
            } else {
                textInputLayout.error = errorText
                return false
            }
        } else {
            if (!text.isEmpty()) {
                textInputLayout.error = null
                return true
            } else {
                textInputLayout.error = errorText
                return false
            }
        }
    }
}