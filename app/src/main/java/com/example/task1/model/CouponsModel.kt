package com.example.task1.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


data class CouponsModel(
    val AndroidVersion: String,
    val ErrorMessage: ErrorMessage1,
    val NewTermsAcceptanceRequired: Boolean,
    val RequiredAcceptTerms: Boolean,
    val RequiredAndroidAppUpdate: Boolean,
    val RequiredIOSAppUpdate: Boolean,
    val SettingsLastChangeDate: String,
    val Specials: List<Special>,
    val UpdateConfigSettings: Boolean,
    val iPhoneVersion: String
)
data class ErrorMessage1(
    val ErrorCode: Int,
    val ErrorDetails: String,
    val OfflineMessage: String
)
@Parcelize
data class Special(
    val Amount: Double,
    val CouponLimit: Int,
    val CustomerID: Int,
    val CustomerName: String,
    val DepartmentName: String,
    val Details: String,
    val DiscountAmount: Double,
    val ExpiresOn: String,
    val ImagePath: String,
    val IsDiscountPercentage: Boolean,
    val IsFeatured: Boolean,
    var IsInCart: Boolean,
    val IsInNCRImpressions: Boolean,
    val IsRedeem: Boolean,
    val IsWeeklyCoupons: Boolean,
    val NCRPromotionCode: String,
    val NewsCategoryDescription: String,
    val NewsCategoryId: Int,
    val PLUCode: String,
    val ProductCategoryId: Int,
    val ProductName: String,
    val RedeemDate: String,
    val SSNewsId: Int,
    val SendNotification: Boolean,
    val SpecialPrice: String,
    val Title: String,
    val ValidFromDate: String,
    val productId: Int
): Parcelable