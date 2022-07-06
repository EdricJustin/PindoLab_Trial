package com.pmberjaya.tvadsmanager.cache.sharedpref


import android.content.Context
import android.content.SharedPreferences
import androidx.annotation.Nullable
import com.pmberjaya.tvadsmanager.api.model.UserData
import com.pmberjaya.tvadsmanager.util.Constants
import dagger.hilt.android.qualifiers.ApplicationContext
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton


/**
 * General Preferences Helper class, used for storing preference values using the Preference API
 */
@Singleton
class PreferencesHelper @Inject constructor(@ApplicationContext context: Context) {

    companion object {
        private const val PREF_BUFFER_PACKAGE_NAME = "com.pmberjaya.tvadsmanager"
        private val PREF_KEY_IS_LOGIN = "login"
        private val PREF_KEY_USER_ID = "user_id"
        private val PREF_KEY_NAME = "name"
        private val PREF_KEY_USER_EMAIL = "email"
        private val PREF_KEY_PROFILE_PHOTO = "profile_photo"
        private val PREF_KEY_PHONE = "no_telepon"
        private val PREF_KEY_ACCESS_TOKEN = "access_token"
    }

    private val pipoPref: SharedPreferences

    init {
        pipoPref = context.getSharedPreferences(PREF_BUFFER_PACKAGE_NAME, Context.MODE_PRIVATE)
    }

    var isLogin: Boolean
        get() = pipoPref.getBoolean(PREF_KEY_IS_LOGIN, false)
        set(isLogin) = pipoPref.edit().putBoolean(PREF_KEY_IS_LOGIN, isLogin).apply()


    fun saveAccount(userData : UserData, @Nullable type : String?) {
//        pipoPref.edit().putBoolean(PREF_KEY_IS_LOGIN, true).apply()
        pipoPref.edit().putString(PREF_KEY_USER_ID, userData.id.toString()).apply()
        pipoPref.edit().putString(PREF_KEY_NAME, userData.name).apply()
        pipoPref.edit().putString(PREF_KEY_PHONE, userData.phone).apply()
        pipoPref.edit().putString(PREF_KEY_USER_EMAIL, userData.email).apply()
        pipoPref.edit().putString(PREF_KEY_PROFILE_PHOTO, userData.cover_path).apply()
        if (type!=null && type == Constants.LOGIN_USER){
            pipoPref.edit().putString(PREF_KEY_ACCESS_TOKEN, userData.access_token).apply()
        }
    }

//    fun getAccount() : LiveData<UserData> {
//        if(isLogin) {
//            val user = UserData(
//                sdmPref.getString(PREF_KEY_USER_ID, null),
//                sdmPref.getString(PREF_KEY_USER_NAME, null),
//                sdmPref.getString(PREF_KEY_USER_EMAIL, null),
//                sdmPref.getString(PREF_KEY_USER_PHONE, null)
//            )
//            val userLiveData: MutableLiveData<UserData> = MutableLiveData()
//            userLiveData.value = user
//            return userLiveData
//        }
//        return AbsentLiveData.create()
//    }

    fun getAccountRx() : Single<UserData> {
        val user = UserData()
        user.id = pipoPref.getString(PREF_KEY_USER_ID, null)?.toInt()
        user.name = pipoPref.getString(PREF_KEY_NAME, null)
        user.phone = pipoPref.getString(PREF_KEY_PHONE,null)
        user.email = pipoPref.getString(PREF_KEY_USER_EMAIL,null)
        user.cover_path = pipoPref.getString(PREF_KEY_PROFILE_PHOTO,null)
        user.access_token = pipoPref.getString(PREF_KEY_ACCESS_TOKEN,null)
        return Single.just(user)
    }

    fun signOut() {
        pipoPref.edit().putBoolean(PREF_KEY_IS_LOGIN, false).apply()
        pipoPref.edit().putString(PREF_KEY_USER_ID,null).apply()
        pipoPref.edit().putString(PREF_KEY_NAME, null).apply()
        pipoPref.edit().putString(PREF_KEY_PHONE, null).apply()
        pipoPref.edit().putString(PREF_KEY_USER_EMAIL, null).apply()
        pipoPref.edit().putString(PREF_KEY_PROFILE_PHOTO, null).apply()
        pipoPref.edit().putString(PREF_KEY_ACCESS_TOKEN, null).apply()
    }

    var userId : String
        get() = pipoPref.getString(PREF_KEY_USER_ID,"")?:""
        set(userId)= pipoPref.edit().putString(PREF_KEY_USER_ID, userId).apply()

    var userName : String
        get() = pipoPref.getString(PREF_KEY_NAME,"")?:""
        set(userName)= pipoPref.edit().putString(PREF_KEY_NAME, userName).apply()

    var email : String
        get() = pipoPref.getString(PREF_KEY_USER_EMAIL,"")?:""
        set(email)= pipoPref.edit().putString(PREF_KEY_USER_EMAIL, email).apply()

    var phone : String
        get() = pipoPref.getString(PREF_KEY_PHONE,"")?:""
        set(phone)= pipoPref.edit().putString(PREF_KEY_PHONE, phone).apply()

    var profilePhoto : String
        get() = pipoPref.getString(PREF_KEY_PROFILE_PHOTO,"")?:""
        set(profilePhoto)= pipoPref.edit().putString(PREF_KEY_PROFILE_PHOTO, profilePhoto).apply()


}
