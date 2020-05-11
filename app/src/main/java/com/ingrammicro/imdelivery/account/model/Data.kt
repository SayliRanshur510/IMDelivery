package com.ingrammicro.imdelivery.account.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Data(

    @SerializedName("driverDetails")
    @Expose
    var driverDetails: DriverDetails?=null

)