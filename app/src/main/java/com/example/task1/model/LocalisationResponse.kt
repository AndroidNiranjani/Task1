package com.example.task1.model

data class LocalisationResponse(
    val ResourceUpdatedOn: ResourceUpdatedOn,
    val Resources: List<Resource>,
    val Result: Result
)
data class ResourceUpdatedOn(
    val Day: Int,
    val Hours: Int,
    val Milliseconds: Int,
    val Minutes: Int,
    val Month: Int,
    val Seconds: Int,
    val Year: Int
)
data class Resource(
    val Culture: String,
    val Data: List<Data1>
)

data class Result(
    val ErrorId: Int,
    val Message: String,
    val Status: Boolean,
    val TransactionId: String
)

data class Data1(
    val Key: String,
    val Value: String
)

