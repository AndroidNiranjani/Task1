package com.example.task1.model

data class SaveRequestModel(
    val Amount: Double,
    val CategoryId: Int,
    val Details: String,
    val ExpiresOn: String,
    val IsInNCRImpressions: Boolean,
    val MemberNumber: String,
    val NCRPromotionCode: String,
    val OfferId: Int,
    val ProductName: String,
    val Title: String,
    val UPC: String,
    val UserFirstName: String,
    val UserToken: String,
    val ValidFrom: String
)