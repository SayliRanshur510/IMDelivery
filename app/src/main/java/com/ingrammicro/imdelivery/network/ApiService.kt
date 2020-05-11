package com.ingrammicro.imdelivery.network

import com.google.gson.JsonObject
import com.ingrammicro.imdelivery.account.model.Status
import com.ingrammicro.imdelivery.account.model.forgotPasswordStatus
import com.ingrammicro.imdelivery.tasks.model.TaskStatus
import com.ingrammicro.imdelivery.tasks.model.TripStatus
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST


internal const val API_DRIVER_DETAILS = "DriverDetails"
internal const val API_FORGOT_PASSWORD = "ForgotPassword"
internal const val API_TASK_ALLOTED = "DriverTaskDet"
internal const val API_TRIP_DETAILS = "TripDetails"

interface ApiService {

    @POST(API_DRIVER_DETAILS)
    fun driverDetails(@Body credentials: JsonObject): Call<Status>

    @POST(API_FORGOT_PASSWORD)
    fun forgotPassword(@Body credentials: JsonObject): Call<forgotPasswordStatus>

    @POST(API_TASK_ALLOTED)
    fun taskAlloted(@Body credentials: JsonObject): Call<TaskStatus>

    @POST(API_TRIP_DETAILS)
    fun jobDetails(@Body credentials: JsonObject): Call<TripStatus>
}