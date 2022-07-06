package com.pmberjaya.tvadsmanager.repository


import androidx.lifecycle.MutableLiveData
import com.pmberjaya.tvadsmanager.api.BaseDataResponse
import com.pmberjaya.tvadsmanager.api.TVAdsManagerApiServices
import com.pmberjaya.tvadsmanager.api.RequestHeaders
import com.pmberjaya.tvadsmanager.api.model.UserData
import com.pmberjaya.tvadsmanager.cache.model.*
import com.pmberjaya.tvadsmanager.cache.sharedpref.PreferencesHelper
import io.reactivex.Single
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton


/**
 * Repository that handles User objects.
 */
@Singleton
class UserRepository @Inject constructor(
    private val preferencesHelper: PreferencesHelper,
    private val adsManagerApiServices: TVAdsManagerApiServices,
    private val requestHeaders: RequestHeaders,
    private val loggedUser: LoggedUser,
    private val networkResource: NetworkResource

) {
    fun getLoggedInUser(): Single<UserData> {
        return preferencesHelper.getAccountRx()
    }

    fun setAccessKey(key: String?) {
        //load the current user into the system

//        val user = preferencesHelper.getAccountRx().subscribeOn(SchedulerProvider.instance.computation())?.blockingGet()
//        loggedUser.loggedInUser = user

        //load the current access token into all requests
        if (key != null)
            requestHeaders.accesstoken.accessToken = key
    }

    suspend fun loginUser(
        email: String?,
        password: String?,
        loginUserLiveData: MutableLiveData<Resource<LoginData>>
    ) {
        loginUserLiveData.postValue(Resource(Status.LOADING, null, ""))
        try {
            networkResource.processData(loginUserLiveData, adsManagerApiServices.loginUser(email, password), null)
        }catch (e : Exception){
            networkResource.processData(loginUserLiveData, null, e)
        }
    }
//
//    fun forgotPass(
//        email: String?,
//        loginUserLiveData: MutableLiveData<Resource<BaseResponse>>
//    ) {
//        loginUserLiveData.postValue(Resource(Status.LOADING, null, ""))
//        adsManagerApiServices.forgotPass(email).enqueue(object :
//            Callback<BaseResponse> {
//            override fun onFailure(call: Call<BaseResponse>, e: Throwable) {
//                networkResource.processBaseResponse(loginUserLiveData, null, e)
//            }
//
//
//            override fun onResponse(
//                call: Call<BaseResponse>,
//                response: Response<BaseResponse>
//            ) {
//                networkResource.processBaseResponse(loginUserLiveData, response, null)
//            }
//        })
//
//    }
//
//    fun registerUser(
//        paramsData: HashMap<String, String>,
//        registerUserLiveData: MutableLiveData<Resource<RegisterData>>
//    ) {
//        registerUserLiveData.postValue(Resource(Status.LOADING, null, ""))
//        adsManagerApiServices.registerUser(paramsData).enqueue(object :
//            Callback<BaseDataResponse<RegisterData>> {
//            override fun onFailure(call: Call<BaseDataResponse<RegisterData>>, e: Throwable) {
//                networkResource.processData(registerUserLiveData, null, e)
//            }
//            override fun onResponse(
//                call: Call<BaseDataResponse<RegisterData>>,
//                response: Response<BaseDataResponse<RegisterData>>
//            ) {
//                setAccessKey(response.body()?.data?.accessToken)
//                networkResource.processData(registerUserLiveData, response, null)
//            }
//        })
//    }

    suspend fun getProfileUserData(
        userLiveData: MutableLiveData<Resource<UserData>>
    ) {
        userLiveData.postValue(Resource(Status.LOADING, null, ""))
        try {
            networkResource.processData(userLiveData, adsManagerApiServices.getProfileUserData(), null)
        }catch (e : Exception){
            networkResource.processData(userLiveData, null, e)
        }
    }
//    fun getSubDistrictData(
//            districtId: String?,
//            subdistrictLiveData: MutableLiveData<Resource<List<SubDistrictData>>>
//    ) {
//        subdistrictLiveData.postValue(Resource(Status.LOADING, null, ""))
//        adsManagerApiServices.getSubDistrictList(districtId)
//                .enqueue(object : Callback<BaseListResponse<SubDistrictData>> {
//                    override fun onFailure(
//                            call: Call<BaseListResponse<SubDistrictData>>,
//                            e: Throwable
//                    ) {
//                        networkResource.processList(subdistrictLiveData, null, e)
//                    }
//
//                    override fun onResponse(
//                            call: Call<BaseListResponse<SubDistrictData>>,
//                            response: Response<BaseListResponse<SubDistrictData>>
//                    ) {
//                        networkResource.processList(subdistrictLiveData, response, null)
//                    }
//
//                })
//
//    }
//
//    fun sendVerificationEmail(
//            verificationLiveData: MutableLiveData<Resource<BaseResponse>>,
//            email: String,
//    ) {
//        verificationLiveData.postValue(Resource(Status.LOADING, null, ""))
//        adsManagerApiServices.sendVerificationEmail(email)
//                .enqueue(object : Callback<BaseResponse> {
//                    override fun onFailure(
//                            call: Call<BaseResponse>,
//                            e: Throwable
//                    ) {
//                        networkResource.processBaseResponse(verificationLiveData, null, e)
//                    }
//
//                    override fun onResponse(
//                            call: Call<BaseResponse>,
//                            response: Response<BaseResponse>
//                    ) {
//                        networkResource.processBaseResponse(verificationLiveData, response, null)
//                    }
//
//                })
//
//    }

}
