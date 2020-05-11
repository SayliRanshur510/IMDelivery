package com.ingrammicro.imdelivery

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ingrammicro.imdelivery.account.model.Status
import com.ingrammicro.imdelivery.account.model.forgotPasswordStatus
import com.ingrammicro.imdelivery.tasks.model.Task

class SharedViewModel : ViewModel(){
    var message = MutableLiveData<Event<Resource<forgotPasswordStatus>>>()
    val selectedTask = MutableLiveData<Task>()

}