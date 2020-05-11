package com.ingrammicro.imdelivery.account.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class DriverDetails(
    @SerializedName("Branch_no")
    @Expose
    var branchNo: String,

    @SerializedName("Branch_name")
    @Expose
    val branchName: String,

    @SerializedName("driver_contact")
    @Expose
    val driverContact: String,

    @SerializedName("Carrier_name")
    @Expose
    val carrierName: String,

    @SerializedName("route_no")
    @Expose
    val routeNo: String,

    @SerializedName("vechile_no")
    @Expose
    val vechileNo: String,

    @SerializedName("carrier_code")
    @Expose
    val carrierCode: Any,

    @SerializedName("CarrierOrDTD")
    @Expose
    val carrierOrDTD: String,

    @SerializedName("type_of")
    @Expose
    var typeOf: String,
    @SerializedName("DriverAlternate_no")
    @Expose
    var driverAlternateNo: String,
    @SerializedName("DriverEmailId")
    @Expose
    var driverEmailId: String,

    @SerializedName("driver_name")
    @Expose
var driverName:String
)