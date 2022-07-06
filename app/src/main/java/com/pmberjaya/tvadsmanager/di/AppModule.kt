package com.pmberjaya.tvadsmanager.di

import com.google.gson.Gson
import com.piposmart.app.util.LiveDataCallAdapterFactory
import com.pmberjaya.tvadsmanager.api.TVAdsManagerApiServices
import com.pmberjaya.tvadsmanager.api.RequestHeaders
import com.pmberjaya.tvadsmanager.api.RequestInterceptor
import com.pmberjaya.tvadsmanager.api.model.AccessToken
import com.pmberjaya.tvadsmanager.api.network.ApiService
import com.pmberjaya.tvadsmanager.cache.model.NetworkResource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.CipherSuite
import okhttp3.ConnectionSpec
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.ArrayList
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppModule {
//    }

    //    @Provides
//    fun provideLogDao(database: AppDatabase): LogDao {
//        return database.logDao()
//    }
//
//    @Provides
//    @Singleton
//    fun provideDatabase(@ApplicationContext appContext: Context): AppDatabase {
//        return Room.databaseBuilder(
//            appContext,
//            AppDatabase::class.java,
//            "logging.db"
//        ).build()
    @Provides
    @Singleton
    fun provideHttpLogingInterceptor(): HttpLoggingInterceptor {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        return logging
    }

    @Provides
    @Singleton
    fun provideRequestHeader(): RequestHeaders {
        return RequestHeaders(AccessToken(), "application/json")
    }

    @Provides
    @Singleton
    fun provideRequestInterceptor(requestHeaders: RequestHeaders): RequestInterceptor {
        return RequestInterceptor(requestHeaders)
    }
    @Provides
    @Singleton
    fun provideOkhttpClient(logging: HttpLoggingInterceptor, requestInterceptor: RequestInterceptor): OkHttpClient {
        var cipherSuites: MutableList<CipherSuite>? = ConnectionSpec.MODERN_TLS.cipherSuites as MutableList<CipherSuite>?
        if (!cipherSuites!!.contains(CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_256_CBC_SHA)) {
            cipherSuites = ArrayList(cipherSuites)
            cipherSuites.add(CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_256_CBC_SHA)
        }
        val spec = listOf(ConnectionSpec.CLEARTEXT, ConnectionSpec.MODERN_TLS)
        return OkHttpClient.Builder()
            .connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
//                .connectionSpecs(Collections.singletonList(spec))
            .connectionSpecs(spec)
            .addInterceptor(logging)
            .addInterceptor(requestInterceptor)
            .build()
    }

    @Provides
    @Singleton
    fun provideGithubService(client: OkHttpClient): TVAdsManagerApiServices {
        return Retrofit.Builder()
            .baseUrl(TVAdsManagerApiServices.URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(LiveDataCallAdapterFactory())
            .build()
            .create(TVAdsManagerApiServices::class.java)
    }

    @Provides
    @Singleton
    fun provideGson(): Gson {
        return Gson()
    }

    @Provides
    @Singleton
    fun provideNetworkResource(): NetworkResource {
        return NetworkResource()
    }

    /*
    // new code
    @Provides
    @Singleton
    fun provideNetworkRes(): NetworkRes{
        return NetworkRes()
    }
     */

    @Provides
    @Singleton
    fun provideApiService(client: OkHttpClient): ApiService {
        return Retrofit.Builder()
            .baseUrl(ApiService.URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(LiveDataCallAdapterFactory())
            .build()
            .create(ApiService::class.java)
    }

}
