package com.ingrammicro.imdelivery.account.model

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.library.baseAdapters.BR

class LoginError :BaseObservable(){


    @get:Bindable
    var mobileNo: Int = 0
        set(value) {
            field = value
            notifyPropertyChanged(BR.custContactNo)
        }

    @get:Bindable
    var password: Int = 0
    set(value) {
        field = value
        notifyPropertyChanged(BR.password)
    }
}