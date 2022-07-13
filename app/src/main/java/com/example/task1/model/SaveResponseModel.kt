package com.example.task1.model

data class SaveResponseModel(
    val ErrorMessage: ErrorMessage22
)
data class ErrorMessage22(
    val ErrorCode: Int,
    val ErrorDetails: String,
    val OfflineMessage: Any
)