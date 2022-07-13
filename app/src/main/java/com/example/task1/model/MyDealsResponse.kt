package com.example.task1.model

data class MyDealsResponse(
    val AndroidVersion: String,
    val ErrorMessage: ErrorMessage222,
    val NewTermsAcceptanceRequired: Boolean,
    val RequiredAcceptTerms: Boolean,
    val RequiredAndroidAppUpdate: Boolean,
    val RequiredIOSAppUpdate: Boolean,
    val SettingsLastChangeDate: String,
    val Specials: List<Special1>,
    val UpdateConfigSettings: Boolean,
    val iPhoneVersion: String
)

data class Special1(
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
)

data class ErrorMessage222(
    val ErrorCode: Int,
    val ErrorDetails: String,
    val OfflineMessage: String
)