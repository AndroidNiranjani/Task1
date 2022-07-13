package com.example.task1.model

data class DepartmentModel(
    val AdEndDate: String,
    val AdStartDate: String,
    val ErrorMessage: ErrorMessage2,
    val GalleryItems: List<GalleryItems>
)

data class ErrorMessage2(
    val ErrorCode: Int,
    val ErrorDetails: String,
    val OfflineMessage: Any
)

data class GalleryItems(
    val GalleryType: String,
    val PageNumber: String,
    val URL: String
)