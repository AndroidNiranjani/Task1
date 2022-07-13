package com.example.task1.model

data class ExpandableRequestModel(
    val app_username: String,
    val appname: String,
    val device_type: String,
    val is_mealkit: String,
    val member_number: String,
    val order_id: String,
    val picker_id: String,
    val rsa_client_id: String,
    val sort_by: String,
    val store_id: String,
    val token: String,
    val version: String
)