package com.ingrammicro.imdelivery.tasks.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


data class TaskStatus (
    @SerializedName("status")
    @Expose
    var status: String,

    @SerializedName("code")
    @Expose
    var code: String,

    @SerializedName("message")
    @Expose
    var message: String,

    @SerializedName("Data")
    @Expose
    var taskData: TaskData,
    @SerializedName("TotalCount")
    @Expose
    var totalCount: Int


)