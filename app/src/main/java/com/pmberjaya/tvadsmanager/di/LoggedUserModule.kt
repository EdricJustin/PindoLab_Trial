package com.pmberjaya.tvadsmanager.di

import com.pmberjaya.tvadsmanager.cache.model.LoggedUser
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class LoggedUserModule {

    @Provides
    @Singleton
    fun provideLoggedInUser(): LoggedUser {
        return LoggedUser()
    }

}