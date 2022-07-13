package com.example.task1.model

data class LoginRequestModel(
    val CustomerId: String,
    val DeviceId: String,
    val DeviceInfo: String,
    val DeviceRegistrationId: String,
    val MobileModel: String,
    val MobileOS: String,
    val MobileOSVersion: String,
    val Password: String,
    val UserName: String
)