package com.example.task1.model

data class LoginResponseModel(
    val AOGApiEndPoint: Any,
    val AndroidHomeImage: Any,
    val AndroidVersion: Any,
    val ClientStoreId: Int,
    val ClientStoreName: Any,
    val CustomerCode: Any,
    val CustomerId: Int,
    val EnableClubs: Boolean,
    val EnableCustomersAlsoBoughtTheseItems: Boolean,
    val EnableProductCouponPrice: Boolean,
    val EnableRecipes: Boolean,
    val EnableStoreManagement: Boolean,
    val EnterpriseId: Any,
    val ErrorMesasge: ErrorMesasge,
    val FirstName: String,
    val IsPOSIntegrationEnabled: Boolean,
    val LastName: Any,
    val MemberNumber: Any,
    val MobileNumber: Any,
    val MyCardBarCodeImagePath: Any,
    val SecurityKey: Any,
    val ShowAddToCartForSpecials: Boolean,
    val ShowPriceInSpecials: Boolean,
    val ShowTotalSavings: Boolean,
    val SpecialsDisplayFormat: Int,
    val TotalSavingsAmount: Int,
    val UserDetailId: Int,
    val UserId: String,
    val UserToken: String,
    val Username: Any,
    val ZipCode: Any,
    val iPhoneHomeImage: Any,
    val iPhoneVersion: Any
)

data class ErrorMesasge(
    val ErrorCode: Int,
    val ErrorDetails: String,
    val OfflineMessage: Any
)