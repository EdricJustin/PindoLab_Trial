package com.pmberjaya.tvadsmanager

import android.app.Application
import com.pmberjaya.tvadsmanager.api.RequestHeaders
import com.pmberjaya.tvadsmanager.cache.model.LoggedUser
import com.pmberjaya.tvadsmanager.repository.UserRepository
import com.pmberjaya.tvadsmanager.util.SchedulerProvider
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber
import javax.inject.Inject

@HiltAndroidApp
class TvAdsManagertApp : Application() {
    @Inject
    lateinit var loggedUser: LoggedUser
    @Inject
    lateinit var userRepository: UserRepository
    @Inject
    lateinit var requestHeaders: RequestHeaders

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
        initializeApplication()
    }

    private fun initializeApplication() {
        //load the current user into the system
        val user = userRepository.getLoggedInUser().subscribeOn(SchedulerProvider.instance.computation()).blockingGet()
        loggedUser.loggedInUser = user

        //load the current access token into all requests
        if (user?.access_token != null)
            requestHeaders.accesstoken.accessToken =  user.access_token!!

    }

}
