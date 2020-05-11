package com.ingrammicro.imdelivery.account.model

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.ObservableInt
import androidx.lifecycle.MutableLiveData
import com.ingrammicro.imdelivery.BR
import com.ingrammicro.imdelivery.R
import com.ingrammicro.imdelivery.SingleLiveEvent

class LoginForm: BaseObservable(){

    var mobilePatterns:String = "[0-9]{10}"
    var contactError = ObservableInt()
    var passwordError = ObservableInt()
    var isLoginClick: Boolean = false
    private val loginClick = SingleLiveEvent<Boolean>()
    var loginError = LoginError()




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





    @Bindable
    fun isValid(): Boolean {
        return (isContactValid() && isPasswordValid())
    }

    fun getLoginClick(): MutableLiveData<Boolean> {
        return loginClick
    }

    fun setLoginClick(loginClick: Boolean?) {
        this.loginClick.setValue(loginClick)
    }

    private fun isContactValid(): Boolean {
        loginError.mobileNo  = 0
        if (contactNo.isNotEmpty()){
            if(contactNo.matches((Regex("[0-9]{10}")))){
                return true
            }else{
                    loginError.mobileNo = (R.string.error_contact_not_valid)
            }
        }else{
            loginError.mobileNo = (R.string.error_required)
        }
        return false
    }

    private fun isPasswordValid(): Boolean {
        loginError.password = 0
        if (passwordText.isNotEmpty()){
            return true
//            if(passwordText.matches((Regex("^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@\$%^&*-]).{8,15}\$")))){
//                return true
//            }else{
               // passwordError.set(R.string.error_password_not_valid)
            //}
        }else{
            loginError.password=(R.string.error_required)
        }
        return false
    }
}