package com.ingrammicro.imdelivery.tasks.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


data class TripData (
    @SerializedName("TripDetails")
    @Expose
    var tripDetails: TripDetails

)