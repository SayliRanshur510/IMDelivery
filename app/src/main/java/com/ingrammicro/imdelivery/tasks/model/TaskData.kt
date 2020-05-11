package com.ingrammicro.imdelivery.tasks.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


data class TaskData (
    @SerializedName("Task")
    @Expose
    var task: ArrayList<Task>

)