package com.ingrammicro.imdelivery.tasks.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


data class TripDetails (
    @SerializedName("Trip_sheet_no")
    @Expose
    var tripSheetNo: Int,

    @SerializedName("Date_Of_Dispach")
    @Expose
    var dateOfDispach: String,

    @SerializedName("Carrier_Name")
    @Expose
    var carrierName: String,

    @SerializedName("Truck_No")
    @Expose
    var truckNo: String,

    @SerializedName("Driver_Name")
    @Expose
    var driverName: String,

    @SerializedName("Driver_No")
    @Expose
    var driverNo: String,

    @SerializedName("Route")
    @Expose
    var route: String,

    @SerializedName("Special_Instructions")
    @Expose
    var specialInstructions: String,

    @SerializedName("Prepared_By")
    @Expose
    var preparedBy: String,

    @SerializedName("Prepared_Date")
    @Expose
    var preparedDate: String,

    @SerializedName("Prepared_Time")
    @Expose
    var preparedTime: String,

    @SerializedName("Appr_By")
    @Expose
    var apprBy: String,

    @SerializedName("Warehouse_Code")
    @Expose
    var warehouseCode: String,

    @SerializedName("Is_Appr")
    @Expose
    var isAppr: String,

    @SerializedName("Appr_date")
    @Expose
    var apprDate: String,

    @SerializedName("Appr_time")
    @Expose
    var apprTime: String,

    @SerializedName("comp_cd")
    @Expose
    var compCd: String,

    @SerializedName("Warehouse_name")
    @Expose
    var warehouseName: Any,

    @SerializedName("actual_dispatch_date")
    @Expose
    var actualDispatchDate: String,

    @SerializedName("actual_dispatch_time")
    @Expose
    var actualDispatchTime: Any,

    @SerializedName("delivery_date")
    @Expose
    var deliveryDate: Any,

    @SerializedName("delivery_time")
    @Expose
    var deliveryTime: Any,

    @SerializedName("expected_deliv_date")
    @Expose
    var expectedDelivDate: String,

    @SerializedName("expected_deliv_time")
    @Expose
    var expectedDelivTime: String,

    @SerializedName("trip_end_date")
    @Expose
    var tripEndDate: Any,

    @SerializedName("trip_end_time")
    @Expose
    var tripEndTime: Any,

    @SerializedName("e_status")
    @Expose
    var eStatus: String,

    @SerializedName("Shipped_Through")
    @Expose
    var shippedThrough: Any,

    @SerializedName("Carrier_Code")
    @Expose
    var carrierCode: Any,

    @SerializedName("sub_carrier_name")
    @Expose
    var subCarrierName: Any,

    @SerializedName("gps_status")
    @Expose
    var gpsStatus: Any,

    @SerializedName("gps_tripno")
    @Expose
    var gpsTripno: Any,

    @SerializedName("trip_type")
    @Expose
    var tripType: Any,

    @SerializedName("Sales_Representative")
    @Expose
    var salesRepresentative: Any,

    @SerializedName("Modify_date")
    @Expose
    var modifyDate: Any,

    @SerializedName("Modify_time")
    @Expose
    var modifyTime: Any,

    @SerializedName("Trip_Created_Type")
    @Expose
    var tripCreatedType: Any,

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
    var phone: Any,

    @SerializedName("cust_email")
    @Expose
    var custEmail: String,

    @SerializedName("DeliveryRemarks")
    @Expose
    var deliveryRemarks: Any,

    @SerializedName("DelvSignature")
    @Expose
    var delvSignature: Any,

    @SerializedName("Delivery_Status")
    @Expose
    var deliveryStatus: String,

    @SerializedName("order_number")
    @Expose
    var orderNumber: String

)