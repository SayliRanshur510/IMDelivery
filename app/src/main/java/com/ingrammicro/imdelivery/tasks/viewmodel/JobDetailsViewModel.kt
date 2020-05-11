package com.ingrammicro.imdelivery.tasks.viewmodel

import android.database.Observable
import android.graphics.Bitmap
import android.util.Base64
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import com.ingrammicro.imdelivery.Event
import com.ingrammicro.imdelivery.Repository
import com.ingrammicro.imdelivery.Resource
import com.ingrammicro.imdelivery.tasks.model.JobDetailsForm
import com.ingrammicro.imdelivery.tasks.model.TaskStatus
import com.ingrammicro.imdelivery.tasks.model.TripDetails
import com.ingrammicro.imdelivery.tasks.model.TripStatus
import java.io.ByteArrayOutputStream

class JobDetailsViewModel : ViewModel() {
    var jobDetails = MediatorLiveData<Event<Resource<TripStatus>>>()
    var getJobDetails: LiveData<Event<Resource<TripStatus>>> = jobDetails
    var repository: Repository = Repository()
    var isLoading = ObservableBoolean(false)
    var tripNumber:Int?=0
    var OrderNumber:String?=null
    var jobInfoClick = ObservableBoolean(false)
    var jobStatusClick = ObservableBoolean(false)
    var customerSignatureClick = ObservableBoolean(false)
    var jobDetailsForm = JobDetailsForm()



    init {
        jobDetail()
    }

    fun jobDetail(){
        jobDetails.addSource(repository.jobDetails(
            3173,
            "21-31001-11"
        )){jobDetails.value = Event(it) }
    }

    fun onJobInfoClick(){
            val state = jobInfoClick.get()
            jobInfoClick.set(!state)
        }

    fun onJobStatusClick(){
        val state=jobStatusClick.get()
        jobStatusClick.set(!state)
    }

    fun onCustomerSignClick(){
        val state = customerSignatureClick.get()
        customerSignatureClick.set(!state)
            }

    fun onClearClick(){

    }

//    var invoiceNo = ObservableField("")
//    var custName = ObservableField("")
//    var custEmailId = ObservableField("")
//    var custContactNo = ObservableField("")
//    var deliveryAddress = ObservableField
    var invoiceNo: String = ""

        set(value) {
            field = value

        }

    var custName: String = ""

        set(value) {
            field = value

        }
    var custEmailId: String = ""

        set(value) {
            field = value

        }
    var custContactNo: String = ""
        set(value) {
            field = value

        }
    var deliveryAddress: String = ""
        set(value) {
            field = value

        }


//    fun populateData(tripDetails: TripDetails){
//        jobDetailsForm.field.invoiceNo = tripDetails.invoiceNo
//        custName = tripDetails.driverName
//        custEmailId = tripDetails.custEmail
//        custContactNo = tripDetails.driverNo
//        deliveryAddress = tripDetails.deliveryAdd
//    }

}
