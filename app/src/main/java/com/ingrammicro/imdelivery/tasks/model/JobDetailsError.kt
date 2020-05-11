package com.ingrammicro.imdelivery.tasks.model

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.library.baseAdapters.BR

class JobDetailsError : BaseObservable(){
    @get:Bindable
    var invoiceNo: String = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.invoiceNo)

        }
    @get:Bindable
    var custName: String = ""

        set(value) {
            field = value

        }
    @get:Bindable
    var custEmailId: String = ""

        set(value) {
            field = value

        }
    @get:Bindable
    var custContactNo: String = ""
        set(value) {
            field = value

        }
    @get:Bindable
    var deliveryAddress: String = ""
        set(value) {
            field = value

        }

}