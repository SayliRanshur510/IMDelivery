package com.ingrammicro.imdelivery.tasks.model

import androidx.databinding.BaseObservable
import androidx.databinding.ObservableField

class JobDetailsField : BaseObservable() {

    var invoiceNo = ObservableField("")
    var custName = ObservableField("")
    var custEmailId = ObservableField("")
    var custContactNo = ObservableField("")
    var deliveryAddress = ObservableField("")
}