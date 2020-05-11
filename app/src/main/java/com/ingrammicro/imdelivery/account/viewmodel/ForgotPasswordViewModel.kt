package com.ingrammicro.imdelivery.account.viewmodel

import androidx.databinding.ObservableBoolean
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ingrammicro.imdelivery.Event
import com.ingrammicro.imdelivery.Repository
import com.ingrammicro.imdelivery.Resource
import com.ingrammicro.imdelivery.account.model.Status
import com.ingrammicro.imdelivery.account.model.forgotPasswordStatus

class ForgotPasswordViewModel : ViewModel() {

    var getNewPassword = MediatorLiveData<Resource<forgotPasswordStatus>>()
    private var repository: Repository = Repository()
    private var NO_CONTENT = "No content"
    var snackbar = MutableLiveData<Event<String>>()
    var isLoading = ObservableBoolean(false)



    var  contactNo:String = ""
        get() = field
        set(value) {
            field = value
        }

    fun onSubmitClick(){
        callForgotPasswordApi()
    }
    fun callForgotPasswordApi(){
        getNewPassword.addSource(
            repository.forgotPassword(contactNo,"VIJAY"))
        {
            getNewPassword.value = it
        }
    }
}
