package com.ingrammicro.imdelivery.account.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class forgotPasswordStatus(
    @SerializedName("status")
    @Expose
    var status:String,
@SerializedName("code")
@Expose
var code:String,
    @SerializedName("message")
@Expose
var message:String
)