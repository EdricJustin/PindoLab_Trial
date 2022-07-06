package com.pmberjaya.tvadsmanager.api

import com.pmberjaya.tvadsmanager.api.model.UserData
import com.pmberjaya.tvadsmanager.cache.model.LoginData
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST


interface TVAdsManagerApiServices {

    //------------------------------------------------------ACCOUNT-----------------------------------------------------------------
    companion object {
        //        const val URL = "https://piposmart-adm.kotasatelit.com/"
        const val URL = "https://piposmart-adm-testing.kotasatelit.com/"

    }

    @FormUrlEncoded
    @POST("api/login")
    suspend fun loginUser(
        @Field("email") email: String?,
        @Field("password") password: String?
    ): Response<BaseDataResponse<LoginData>>
//
//    @FormUrlEncoded
//    @POST("api/reset-password")
//    fun forgotPass(
//            @Field("email") email: String?,
//    ): Call<BaseResponse>
//
//    @FormUrlEncoded
//    @POST("api/user/register")
//    fun registerUser(@FieldMap params: HashMap<String, String>): Call<BaseDataResponse<RegisterData>>
//
//    @GET("api/outlet/user-outlet")
//    fun getOutletList(@QueryMap params: HashMap<String, String>): Call<BaseListResponse<OutletData>>
//
//    @FormUrlEncoded
//    @POST("api/outlet/create")
//    fun createOutlet(@FieldMap params: HashMap<String, String>): Call<BaseDataResponse<OutletData>>
//
//    @GET("api/service-categories/list-with-service")
//    fun getOrderServiceList(): Call<BaseListResponse<OrderServiceCategoryData>>
//
//    @FormUrlEncoded
//    @POST("api/transaction/insert")
//    fun postCreateTransaction(@FieldMap params: HashMap<String, String>): Call<BaseDataResponse<TransactionData>>
//
//    @GET("api/service-categories/list")
//    fun getServiceCategoryList(@QueryMap params: HashMap<String, String>): Call<BaseListResponse<ServiceCategoryData>>
//
//    @FormUrlEncoded
//    @POST("api/service-categories/create")
//    fun createServiceCategory(@FieldMap params: HashMap<String, String>): Call<BaseDataResponse<ServiceCategoryData>>
//
//    @FormUrlEncoded
//    @POST("api/service-categories/update/{service_categories_id}")
//    fun updateServiceCategory(
//            @FieldMap params: HashMap<String, String>,
//            @Path("service_categories_id") serviceCategoriesId: String
//    ): Call<BaseDataResponse<ServiceCategoryData>>
//
//    @GET("api/service/by-category/{service_category_id}}")
//    fun getServiceList(@Path("service_category_id") serviceCategoryId: String): Call<BaseListResponse<ServiceData>>
//
//    @FormUrlEncoded
//    @POST("api/service/create")
//    fun createService(@FieldMap params: HashMap<String, String>): Call<BaseDataResponse<ServiceData>>
//
//    @FormUrlEncoded
//    @POST("api/service/update/{service_id}")
//    fun updateService(
//            @FieldMap params: HashMap<String, String>,
//            @Path("service_id") serviceId: String
//    ): Call<BaseDataResponse<ServiceData>>
//
//    @FormUrlEncoded
//    @POST("api/customer/create")
//    fun createCustomer(@FieldMap params: HashMap<String, String>): Call<BaseDataResponse<CustomerData>>
//
//    @FormUrlEncoded
//    @POST("api/customer/edit/{customer_id}")
//    fun updateCustomer(
//            @FieldMap params: HashMap<String, String>,
//            @Path("customer_id") customerId: String
//    ): Call<BaseDataResponse<CustomerData>>
//
//    @GET("api/customer/list")
//    fun getCustomerList(@QueryMap params: HashMap<String, String>): Call<BaseListResponse<CustomerData>>
//
//    @FormUrlEncoded
//    @POST("api/outlet/update/{outlet_id}")
//    fun updateOutlet(
//            @Path("outlet_id") id: Int?,
//            @FieldMap params: HashMap<String, String>
//    ): Call<BaseDataResponse<OutletData>>
//
//    @GET("api/outlet/employee/{outlet_id}")
//    fun getOutletEmployeee(@Path("outlet_id") outletId: Int?): Call<BaseListResponse<OutletEmployeeData>>
//
//    @FormUrlEncoded
//    @POST("api/employee/create")
//    fun createEmployee(@FieldMap params: HashMap<String, String>): Call<BaseDataResponse<OutletEmployeeData>>
//
//    @FormUrlEncoded
//    @POST("api/employee/update/{employee_id}")
//    fun updateEmployee(
//            @Path("employee_id") outletId: Int?,
//            @FieldMap params: HashMap<String, String>
//    ): Call<BaseDataResponse<OutletEmployeeData>>
//
//    @GET("api/report/sales/{outlet_id}")
//    fun getSalesReport(
//            @Path("outlet_id") outletId: Int?,
//            @QueryMap params: HashMap<String, String>,
//    ): Call<BaseDataResponse<ReportData>>
//
//    @GET("api/report/customer/{outlet_id}")
//    fun getCustomerReport(
//            @Path("outlet_id") outletId: Int?,
//            @Query("outlet_id") outlet_id: Int?,
//            @QueryMap params : HashMap<String,String>
//    ): Call<BaseDataResponse<ReportData>>
//
//    @GET("api/report/profit/{outlet_id}")
//    fun getProfitReport(
//        @Path("outlet_id") outletId: Int?,
//        @Query("from_date") startDate: String?,
//        @Query("end_date") endDate: String?
//    ): Call<BaseDataResponse<ReportProfitData>>
//
//    @GET("api/report/pay/{outlet_id}")
//    fun getTransactionReport(
//            @Path("outlet_id") outletId: Int?,
//            @Query("date") date: String?
//    ): Call<BaseDataResponse<TransactionReportData>>
//
//    @GET("api/report/outcome/{outlet_id}")
//    fun getOutComeReport(
//        @Path("outlet_id") outletId: Int?,
//        @QueryMap params: HashMap<String, String>,
//    ): Call<BaseDataResponse<ReportData>>
//
//    @FormUrlEncoded
//    @POST("api/employee/update-status/{employee_id}")
//    fun updateEmployeeStatus(
//            @Path("employee_id") outletId: Int?,
//            @Field("status") status: Int?
//    ): Call<BaseDataResponse<OutletEmployeeData>>
//
//    @GET("api/balance/history")
//    fun getBalanceHistory(@QueryMap params: HashMap<String, String>): Call<BaseListResponse<BalanceHistoryData>>
//
//    @GET("api/top-up/product-list")
//    fun getTopupPackage(): Call<BaseListResponse<TopUpPackageData>>
//
//    @FormUrlEncoded
//    @POST("api/top-up/create")
//    fun postTopUpPayment(
//            @FieldMap params: HashMap<String, String>
//    ): Call<BaseDataResponse<TopUpPaymentData>>
//
//    @GET("api/top-up/history")
//    fun getTopUpHistory(@QueryMap params: HashMap<String, String>): Call<BaseListResponse<TopUpHistoryData>>
//
//    @GET("api/transaction/list/{outlet_id}")
//    fun getTransactionList(
//            @Path("outlet_id") outletId: String,
//            @QueryMap params: HashMap<String, String>
//    ): Call<BaseListResponse<TransactionData>>
//
//    @GET("api/transaction/detail/{id}")
//    fun getTransactionDetail(@Path("id") transactionId: String): Call<BaseDataResponse<TransactionData>>
//
//    @GET("api/membership/product-list")
//    fun getMembershipProductData(): Call<BaseListResponse<MembershipProductData>>
//
//    @GET("api/project")
//    fun getProjectInfo(): Call<BaseDataResponse<ProjectInfoData>>
//
//    @FormUrlEncoded
//    @POST("api/membership/create")
//    fun postCreatePaymentMemberShip(@Field("membership_packages_id") status: Int?, @Field("outlets_id") outlets_id: Int?): Call<BaseDataResponse<CreateMemberShipData>>
//
//    @FormUrlEncoded
//    @POST("api/transaction/update/{transaction_id}")
//    fun updateTransaction(
//            @Path("transaction_id") transactionid: String,
//            @FieldMap params: HashMap<String, String>
//    ): Call<BaseDataResponse<TransactionData>>
//
//    @GET("api/membership/history/{outletId}")
//    fun getMemberShipHistoryList(@Path("outletId") outletId: String, @QueryMap params: HashMap<String, String>): Call<BaseListResponse<MemberShipHistoryData>>
//
    @GET("api/user/data")
    suspend fun getProfileUserData(): Response<BaseDataResponse<UserData>>
//
//    @FormUrlEncoded
//    @POST("api/user/update-data")
//    fun postUpdateUserData(@FieldMap params: HashMap<String, String>): Call<BaseDataResponse<UserData>>
//
//    @FormUrlEncoded
//    @POST("api/user/change-password")
//    fun postChangePassword(@FieldMap params: HashMap<String, String>): Call<BaseDataResponse<UserData>>
//
//    @FormUrlEncoded
//    @POST("api/outcome/create")
//    fun createOutcome(@FieldMap params: HashMap<String, String>): Call<BaseDataResponse<OutcomeData>>
//
//    @FormUrlEncoded
//    @POST("api/outcome/update/{outcome_id}")
//    fun updateOutcome(
//            @Path("outcome_id") outcomeId: String,
//            @FieldMap params: HashMap<String, String>
//    ): Call<BaseDataResponse<OutcomeData>>
//
//    @POST("api/outcome/delete/{outcome_id}")
//    fun updateOutcome(@Path("outcome_id") outcomeId: String): Call<BaseResponse>
//
//    @GET("api/outcome/list/{outlet_id}")
//    fun getOutcomeList(
//            @Path("outlet_id") outletId: String,
//            @QueryMap params: HashMap<String, String>
//    ): Call<BaseListResponse<OutcomeData>>
//
//    @GET("api/outcome-categories/list")
//    fun getOutcomeCategoryList(@QueryMap params : HashMap<String,String>): Call<BaseListResponse<OutcomeCategoryData>>
//
//    @FormUrlEncoded
//    @POST("api/outcome-categories/create")
//    fun createOutcomeCategory(@FieldMap params: HashMap<String, String>): Call<BaseDataResponse<OutcomeCategoryData>>
//
//    @FormUrlEncoded
//    @POST("api/outcome-categories/update/{outcome_categories_id}")
//    fun updateOutcomeCategory(
//            @Path("outcome_categories_id") outcomeCategoriesId: String,
//            @FieldMap params: HashMap<String, String>
//    ): Call<BaseDataResponse<OutcomeCategoryData>>
//
//    @POST("api/outcome-categories/delete/{outcome_categories_id}")
//    fun deleteOutcomeCategory(
//            @Path("outcome_categories_id") outcomeCategoriesId: String
//    ): Call<BaseResponse>
//
//    @GET("api/inbox/list")
//    fun getInboxMessageList(@QueryMap params: HashMap<String, String>): Call<BaseListResponse<InboxData>>
//
//    @GET("api/inbox/detail/{id}")
//    fun getInboxMessageDetail(@Path("id") id: String): Call<BaseDataResponse<InboxData>>
//
//    @GET("api/transaction/reminder/{outlet_id}")
//    fun getInboxTransactionMessageList(@Path("outlet_id") outletId: String): Call<BaseListResponse<InboxTransactionData>>
//
//    @FormUrlEncoded
//    @POST("api/service-categories/delete/{service_id}")
//    fun postDeleteServiceCategories(
//            @Path("service_id") serviceId: String?,
//            @Field("id") id: String?
//    ): Call<BaseResponse>
//
//    @POST("api/service/delete/{service_id}")
//    fun postDeleteService(@Path("service_id") serviceId: String?): Call<BaseResponse>
//
//    @POST("api/customer/delete/{customer_id}")
//    fun postDeleteCustomer(@Path("customer_id") customerId: String?): Call<BaseResponse>
//
//    @POST("api/outlet/delete/{outlet_id}")
//    fun postDeleteOutlet(
//            @Path("outlet_id") customerId: String?,
//    ): Call<BaseResponse>
//
//    @GET("api/index/{outlet_id}")
//    fun getHomeData(@Path("outlet_id") outletId: String?): Call<BaseDataResponse<HomeData>>
//
//    @FormUrlEncoded
//    @POST("api/top-up/cancel-transaction")
//    fun deleteTopUpPayment(
//            @Field("id") id: String
//    ): Call<BaseResponse>
//
//    @Multipart
//    @POST("api/top-up/upload-payment-proof/{topup_logs_id}")
//    fun uploadTopupProof(@Part image: MultipartBody.Part, @Path("topup_logs_id") id: String) : Call<BaseResponse>
//
//    @GET("api/banner/list")
//    fun getBannerList(): Call<BaseListResponse<BannerData>>
//
//
//    @Multipart
//    @POST("api/upload-media/{type}")
//    fun uploadFile(@Path("type") type: String?, @Part image: MultipartBody.Part) : Call<BaseDataResponse<UploadFileData>>
//
//    // option 1: a resource relative to your base URL
////    @GET("/resource/example.zip")
////    fun downloadFileWithFixedUrl(): Call<ResponseBody?>?
//
//    // option 2: using a dynamic URL
//    @GET
//    fun downloadFileWithDynamicUrlSync(@Url fileUrl: String?,@QueryMap params: HashMap<String, String>): Call<ResponseBody?>?
//
//    @FormUrlEncoded
//    @POST("api/user/sent-verification-email")
//    fun sendVerificationEmail(
//        @Field("email") email: String
//    ): Call<BaseResponse>
//
//    @GET("api/bank")
//    fun getBankList(): Call<BaseListResponse<BankData>>
//
//    @GET("api/location/province")
//    fun getProvinceList(): Call<BaseListResponse<ProvinceData>>
//
//    @GET("api/location/city/{province_id}")
//    fun getCityList(@Path("province_id") type: String?): Call<BaseListResponse<CityData>>
//
//    @GET("api/location/district/{city_id}")
//    fun getDistrictList(@Path("city_id") type: String?): Call<BaseListResponse<DistrictData>>
//
//    @GET("api/location/sub-district/{district_id}")
//    fun getSubDistrictList(@Path("district_id") type: String?): Call<BaseListResponse<SubDistrictData>>
//
//    @FormUrlEncoded
//    @POST("api/service-perfume/create")
//    fun createPerfume(@FieldMap params: HashMap<String, String>): Call<BaseDataResponse<PerfumeData>>
//
//    @FormUrlEncoded
//    @POST("api/service-perfume/update/{perfume_id}")
//    fun updatePerfume(
//        @FieldMap params: HashMap<String, String>,
//        @Path("perfume_id") perfumeId: String
//    ): Call<BaseDataResponse<PerfumeData>>
//
//    @POST("api/service-perfume/delete/{perfume_id}")
//    fun postDeletePerfume(
//        @Path("perfume_id") perfumeId: String?
//    ): Call<BaseResponse>
//
//    @GET("api/service-perfume/list")
//    fun getPerfumeList(): Call<BaseListResponse<PerfumeData>>
}