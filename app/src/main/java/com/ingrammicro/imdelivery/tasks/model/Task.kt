package com.ingrammicro.imdelivery.tasks.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


data class Task(
    @SerializedName("trip_sheet_no")
    @Expose
    var tripSheetNo: Int,

    @SerializedName("id")
    @Expose
    var id:Int,

    @SerializedName("date_of_dispach")
    @Expose
    var dateOfDispach: String,

    @SerializedName("carrier_name")
    @Expose
    var carrierName: Any,

    @SerializedName("truck_no")
    @Expose
    var truckNo: String,

    @SerializedName("driver_name")
    @Expose
    var driverName: String,

    @SerializedName("driver_no")
    @Expose
    var driverNo: String,

    @SerializedName("route")
    @Expose
    var route: String,

    @SerializedName("warehouse_code")
    @Expose
    var warehouseCode: String,

    @SerializedName("actual_dispatch_date")
    @Expose
    var actualDispatchDate: String,

    @SerializedName("actual_dispatch_time")
    @Expose
    var actualDispatchTime: String,

    @SerializedName("delivery_date")
    @Expose
    var deliveryDate: Any,

    @SerializedName("delivery_time")
    @Expose
    var deliveryTime: Any,

    @SerializedName("expected_Deliv_date")
    @Expose
    var expectedDelivDate: String,

    @SerializedName("expected_deliv_time")
    @Expose
    var expectedDelivTime: String,

    @SerializedName("trip_end_date")
    @Expose
    var tripEndDate: String,

    @SerializedName("trip_end_time")
    @Expose
    var tripEndTime: String,

    @SerializedName("carrier_code")
    @Expose
    var carrierCode: Any,

    @SerializedName("trip_type")
    @Expose
    var tripType: Any,

    @SerializedName("Consignment_Delivery_Status")
    @Expose
    var consignmentDeliveryStatus: String,

    @SerializedName("invoice_no")
    @Expose
    var invoiceNo: String,
    @SerializedName("invoice_date")
    @Expose
    var invoiceDate: String,
    @SerializedName("delivery_add")
    @Expose
    var deliveryAdd: String,
    @SerializedName("customer_name")
    @Expose
    var customerName: String,
    @SerializedName("phone")
    @Expose
    var phone: String,
    @SerializedName("cust_email")
    @Expose
    var custEmail: String,
    @SerializedName("order_number")
    @Expose
    var orderNo:String
)