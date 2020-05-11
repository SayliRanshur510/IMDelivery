package com.ingrammicro.imdelivery

import androidx.lifecycle.MutableLiveData
import com.google.gson.JsonObject
import com.ingrammicro.imdelivery.account.model.Status
import com.ingrammicro.imdelivery.account.model.forgotPasswordStatus
import com.ingrammicro.imdelivery.network.ApiService
import com.ingrammicro.imdelivery.network.NetworkCall
import com.ingrammicro.imdelivery.network.RetrofitClient
import com.ingrammicro.imdelivery.tasks.model.TaskStatus
import com.ingrammicro.imdelivery.tasks.model.TripStatus


internal const val PARAM_PASSWORD = "Password"
internal const val PARAM_CONTACT_NO = "DriverContactNo"
internal const val PARAM_DRIVER_NAME="DriverName"
internal const val PARAM_DELIVERY_STATUS="DeliveryStatus"
internal const val PARAM_PAGE_NO = "pageNo"
internal const val PARAM_TRIP_NO = "Trip_No"
internal const val PARAM_ORDER_NO = "Order_No"

class Repository {

    private val retrofitClient:RetrofitClient = RetrofitClient()
    private var apiService: ApiService = retrofitClient.getRetrofitInstance()!!.create(ApiService::class.java)

    fun login(contactNo: String): MutableLiveData<Resource<Status>> {
        val body = JsonObject()
        body.addProperty(PARAM_CONTACT_NO, contactNo)
        return NetworkCall<Status>().makeCall(apiService.driverDetails(body))
    }

    fun forgotPassword(contactNo: String,driverName:String):MutableLiveData<Resource<forgotPasswordStatus>>{
        val body = JsonObject()
        body.addProperty(PARAM_CONTACT_NO, contactNo)
        body.addProperty(PARAM_DRIVER_NAME,driverName)
        return NetworkCall<forgotPasswordStatus>().makeCall(apiService.forgotPassword(body))
    }

    fun tasksAlloted(contactNo: String,driverName:String,deliveryStatus:String,pageNo:Int):
            MutableLiveData<Resource<TaskStatus>>{
        val body = JsonObject()
        body.addProperty(PARAM_CONTACT_NO, contactNo)
        body.addProperty(PARAM_DRIVER_NAME,driverName)
        body.addProperty(PARAM_DELIVERY_STATUS,deliveryStatus)
        body.addProperty(PARAM_PAGE_NO,pageNo)
        return NetworkCall<TaskStatus>().makeCall(apiService.taskAlloted(body))
    }

    fun jobDetails(tripNo:Int,orderNo:String):MutableLiveData<Resource<TripStatus>>{
        val body = JsonObject()
        body.addProperty(PARAM_TRIP_NO,tripNo)
        body.addProperty(PARAM_ORDER_NO,orderNo)
        return NetworkCall<TripStatus>().makeCall(apiService.jobDetails(body))
    }
}


