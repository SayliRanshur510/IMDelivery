package com.ingrammicro.imdelivery.tasks.viewmodel

import android.app.Application
import android.content.Intent
import android.net.Uri
import androidx.core.content.ContextCompat.startActivity
import androidx.databinding.ObservableBoolean
import androidx.lifecycle.*
import com.ingrammicro.imdelivery.Event
import com.ingrammicro.imdelivery.PreferenceHelper
import com.ingrammicro.imdelivery.Repository
import com.ingrammicro.imdelivery.Resource
import com.ingrammicro.imdelivery.account.model.Status
import com.ingrammicro.imdelivery.tasks.model.JobDetailsForm
import com.ingrammicro.imdelivery.tasks.model.Task
import com.ingrammicro.imdelivery.tasks.model.TaskStatus
import com.paginate.Paginate
import kotlin.coroutines.coroutineContext


internal const val PARAM_PENDING="pending"
internal const val PARAM_COMPLETED="completed"
internal const val PARAM_ALL="all"

class TaskAllotedViewModel(application: Application) : AndroidViewModel(application) {

    var jobDetails = MediatorLiveData<Event<Resource<TaskStatus>>>()
    var getJobDetails: LiveData<Event<Resource<TaskStatus>>> = jobDetails
    var repository:Repository = Repository()
    var isLoading = ObservableBoolean(false)
    var getMap= MutableLiveData<Event<Unit>>()
    var getLocationCoordinates:LiveData<Event<Unit>> = getMap
    var getContactNumber = MutableLiveData<Event<Unit>>()
    var getContact:LiveData<Event<Unit>> = getContactNumber
    var preferenceHelper: PreferenceHelper = PreferenceHelper(application)
    var snackbar = MutableLiveData<Event<String>>()
    private var NO_CONTENT = "No content"
    var page = 0
     var loading = true
     var totalPages = 1
    var totalRecords:Int?=0

    var jobDetailsForm = JobDetailsForm()



    init {
        getTaskAllotedList(PARAM_PENDING)
    }

    var  contactNo:String = "8700512412"
        get() = field
        set(value) {
            field = value
        }


     fun getTaskAllotedList(deliveryStatus:String){
        jobDetails.addSource(repository.tasksAlloted(
            preferenceHelper.contactNo!!,
            preferenceHelper.driverName!!,
            deliveryStatus,
            page
        )){jobDetails.value = Event(it)}
    }


    fun hasLoadedAllData(): Boolean {
        return page >= totalPages
    }
    fun loadMore(){
        page++
        getTaskAllotedList(PARAM_PENDING)
    }


    fun onTaskClick(){

        jobDetailsForm.populateJobInfo()
    }

    fun checkTaskDetails(data: TaskStatus) {
        isLoading.set(true)
        if (data.status.equals(NO_CONTENT)) {
            snackbar.value = Event(data.message)
        }

    }

}