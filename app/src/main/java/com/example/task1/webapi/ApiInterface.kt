package com.example.task1.webapi

import com.example.task1.model.*
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*


interface ApiInterface {
    @POST("GetDetailsForCoupons/{PRODUCT_ID}/{USER_TOKEN}/{IS_WEEKLY_COUPON}")
    suspend fun getCoupouns(@Path(value="PRODUCT_ID")  product_id: Int,
                            @Path(value="USER_TOKEN")user_token: String,
                            @Path(value="IS_WEEKLY_COUPON") is_weekly_coupon: Int) : Response<CouponsModel>

    @GET("WeeklyAdGallery/1385525211/1")
    suspend fun getDepartment() : Response<DepartmentModel>

    @GET("AccountResource?AccountResourceRequest={\"DevicePhoneNumber\":\"%20\",\"EmployeeID\":987875295,\"ResourceUpdatedOn\":{\"Day\":9,\"Hours\":5,\"Milliseconds\":780,\"Minutes\":56,\"Month\":6,\"Seconds\":18,\"Year\":2022},\"Credential\":{\"CorpID\":\"AmitT\",\"LoginID\":\"webtest\",\"Password\":\"test4321\",\"ClientMobileAppVersion\":\"5.8.1\",\"DeviceOSVersion\":\"9\",\"DeviceModelNumber\":\"POCO%20F1\",\"DeviceOS\":\"Android\",\"DeviceCulture\":\"\",\"ParentRequestId\":\"311234\",\"EmployeeCulture\":\"Default\",\"FormName\":\"Home_Menu\"}}")
    suspend fun getLocalData() : Response<LocalisationResponse>

    @POST("SaveMyCart")
    suspend fun savecart(@Body request: SaveRequestModel) : Response<SaveResponseModel>

    @POST("RemoveFromCart/{PRODUCT_ID}/{USER_TOKEN}")
    suspend fun removecart(@Path(value="PRODUCT_ID")  product_id: Int,
                         @Path(value="USER_TOKEN")user_token: String) : Response<SaveResponseModel>

    @POST("ValidateUser")
    suspend fun loginapi(@Body loginRequestModel: LoginRequestModel) : Response<LoginResponseModel>

    @POST("GetDetailsForCoupons/{product_id}/{user_token}/{is_weekly_coupon}")
    suspend fun getDeals(@Path(value="product_id")  product_id: Int,
                         @Path(value="user_token")user_token: String,
                         @Path(value="is_weekly_coupon") is_weekly_coupon: Int) : Response<MyDealsResponse>

    @POST("GetMyCartDetails/{user_token}")
    suspend fun getshoppingDetails(@Path(value="user_token")user_token: String) : Response<ShoppingListModel>

   @POST("GetPOSOrderDetails")
    suspend fun getExpandableListItems(@Body expandableRequestModel: ExpandableRequestModel): Response<ExapandableModel>




    companion object {
        var BASE_URL = "https://valliproduce.rsaamerica.com/services/SSWebRestApi.svc/"

        fun create() : ApiInterface {

            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()
            return retrofit.create(ApiInterface::class.java)

        }
    }
}