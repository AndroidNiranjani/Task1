package com.example.task1.model

data class EndLessScrollModel(
    val CurrentPageIndex: Int,
    val Punches: List<Punche>,
    val Result: Result1,
    val TotalPageCount: Int
)

data class Punche(
    val DisplayWorkOrder: Boolean,
    val EmployeeID: Int,
    val EmployeeName: String,
    val Hours: Hours,
    val IsOverNightPunch: Boolean,
    val IsPunchInTimeChanged: Boolean,
    val IsPunchOutTimeChanged: Boolean,
    val PairedPunchId: Int,
    val PunchDate: PunchDate,
    val PunchInTime: PunchInTime,
    val PunchOutTime: PunchOutTime,
    val RateType: String,
    val SiteID: Int,
    val SiteName: String,
    val TaskID: Int,
    val TaskName: String,
    val WorkOrderID: Any,
    val WorkOrderName: String
)

data class Result1(
    val ErrorId: Int,
    val Message: String,
    val Status: Boolean,
    val TransactionId: String
)


data class Hours(
    val Hour: Int,
    val Minute: Int
)

data class PunchInTime(
    val Day: Int,
    val Hours: Int,
    val Milliseconds: Int,
    val Minutes: Int,
    val Month: Int,
    val Seconds: Int,
    val Year: Int
)

data class PunchOutTime(
    val Day: Int,
    val Hours: Int,
    val Milliseconds: Int,
    val Minutes: Int,
    val Month: Int,
    val Seconds: Int,
    val Year: Int
)

data class PunchDate(
    val Day: Int,
    val Hours: Int,
    val Milliseconds: Int,
    val Minutes: Int,
    val Month: Int,
    val Seconds: Int,
    val Year: Int
)