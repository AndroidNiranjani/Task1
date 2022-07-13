package com.example.task1.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.task1.model.CouponsModel
import com.example.task1.webapi.ApiInterface

class CouponsListRepository(private val apiInterface: ApiInterface) {

    private val couponsLiveData=MutableLiveData<CouponsModel>()

    val coupons: LiveData<CouponsModel>
    get() = couponsLiveData


    suspend fun getCoupons(product_id: Int,user_token: String,is_weekly_coupon: Int)
    {
        val result=apiInterface.getCoupouns(product_id,user_token,is_weekly_coupon)
        if(result?.body() != null){
            couponsLiveData.postValue(result.body())
        }
    }
}