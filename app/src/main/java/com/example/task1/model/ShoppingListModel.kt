package com.example.task1.model

data class ShoppingListModel(
    val ErrorMessage: ErrorMessage,
    val MyCartInfo: ArrayList<MyCartInfo>,
    val MyCartProductInfo: List<Any>
)
data class ErrorMessage(
    val ErrorCode: Int,
    val ErrorDetails: String,
    val OfflineMessage: String
)

data class MyCartInfo(
    var AddToCart: Boolean,
    val Amount: Double,
    val CategoryId: Int,
    val CustomerId: Int,
    val DepartmentName: String,
    val Details: String,
    val DiscountAmount: Double,
    val ExpiresOn: String,
    val FlippItemId: Int,
    val ImagePath: String,
    val IsChecked: Boolean,
    val IsDiscountPercentage: Boolean,
    val IsInNCRImpressions: Boolean,
    val MyCartId: Int,
    val NCRPromotionCode: String,
    val NewsCategoryId: Int,
    val NoDiscountCount: Int,
    val OfferId: Int,
    val PLUCode: String,
    val ProductCode: Any,
    val ProductId: Int,
    val ProductName: String,
    val RedeemDate: String,
    val RedeemOn: Boolean,
    val SalePrice: Int,
    val SpecialPrice: String,
    val Title: String,
    val UpSellCount: Int,
    val UpSellProductCode: String,
    val UpsellPrice: Int,
    val UpsellProductId: Int,
    val ValidFrom: String
)