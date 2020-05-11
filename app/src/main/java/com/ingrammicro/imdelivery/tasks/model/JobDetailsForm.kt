package com.ingrammicro.imdelivery.tasks.model

import androidx.databinding.BaseObservable
import com.ingrammicro.imdelivery.SingleLiveEvent

class JobDetailsForm : BaseObservable(){

    var field = JobDetailsField()
    var error = JobDetailsError()
var jobInfo = SingleLiveEvent<JobDetailsField>()

    fun populateJobInfo(){
        jobInfo.value = field
    }


}