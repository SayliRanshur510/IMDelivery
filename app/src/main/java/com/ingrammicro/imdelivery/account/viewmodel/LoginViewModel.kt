package com.ingrammicro.imdelivery.account.viewmodel

import android.app.Application
import android.content.Context
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableInt
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ingrammicro.imdelivery.*
import com.ingrammicro.imdelivery.account.model.DriverDetails
import com.ingrammicro.imdelivery.account.model.LoginForm
import com.ingrammicro.imdelivery.account.model.Status
import com.ingrammicro.imdelivery.tasks.model.Task

class LoginViewModel(application: Application): AndroidViewModel(application) {
    // TODO: Implement the ViewModel
    private val sharedPrefFile = "com.ingrammicro.coverplus.preference"
    var getDriverDetails = MediatorLiveData<Resource<Status>>()
    private var repository:Repository = Repository()
    private var NO_CONTENT = "No content"
    var snackbar = MutableLiveData<Event<String>>()
    var isLoading = ObservableBoolean(false)
    var loginForm:LoginForm = LoginForm()
//    private  var context:Context
//    init {
//        this.context
//    }
////
   var preferenceHelper:PreferenceHelper = PreferenceHelper(application)
//


    var contactError = ObservableInt()
    var passwordError = ObservableInt()
    var isLoginClick: Boolean = false


    var  contactNo:String = ""
        get() = field
        set(value) {
            field = value
            if (isLoginClick){
                isContactValid()
            }
        }

    var passwordText: String =""
        get() = field
        set(value) {
            field = value
            if (isLoginClick) {
                isPasswordValid()
            }
        }

    fun isValid(): Boolean {
        return (isContactValid() && isPasswordValid())
    }

    private fun isContactValid(): Boolean {
        contactError.set(0)
        if (contactNo.isNotEmpty()){
            if(contactNo.matches((Regex("[0-9]{10}")))){
                return true
            }else{
                contactError.set(R.string.error_contact_not_valid)
            }
        }else{
            contactError.set(R.string.error_required)

        }
        return false
    }

    private fun isPasswordValid(): Boolean {
        passwordError.set(0)
        if (passwordText.isNotEmpty()){
            if(passwordText.matches((Regex("^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@\$%^&*-]).{8,15}\$")))){
                return true
            }else{
                passwordError.set(R.string.error_password_not_valid)
            }
        }else{
            passwordError.set(R.string.error_required)
        }
        return false
    }



    fun onLoginClick(){
        loginForm.isLoginClick = true
        if (loginForm.isValid()) {
            loginForm.setLoginClick(true)
            callLoginPassword()

        }
    }
    fun loginClick(): MutableLiveData<Boolean> {
        return loginForm.getLoginClick()
    }
    fun saveDriverDetails(driverDetails: DriverDetails){
        preferenceHelper.contactNo = driverDetails.driverContact
        preferenceHelper.driverName = driverDetails.driverName
        preferenceHelper.emailId = driverDetails.driverEmailId
    }
    fun callLoginPassword() {
        getDriverDetails.addSource(
            repository.login(
                loginForm.contactNo
        )
        ) { getDriverDetails.value = it }

    }

    fun checkLoginResult(data: Status) {
        isLoading.set(true)
        if (data.status.equals(NO_CONTENT)) {
            snackbar.value = Event(data.message)
        }

    }
}
