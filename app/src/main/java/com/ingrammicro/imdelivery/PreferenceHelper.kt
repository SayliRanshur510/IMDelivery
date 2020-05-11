package com.ingrammicro.imdelivery

import android.content.Context
import android.content.SharedPreferences
import com.ingrammicro.imdelivery.account.model.DriverDetails

private const val PREFS_FILENAME = "com.ingrammicro.imdelivery.preference"
private const val PREFS_NAME = "prefs_name"
private const val PREFS_CONTACT_NO = "prefs_contact_no"
private const val PREFS_EMAIL_ID = "prefs_email_id"

class PreferenceHelper(context: Context) {


    val prefs: SharedPreferences = context.getSharedPreferences(PREFS_FILENAME, Context.MODE_PRIVATE)


    var driverName: String?
    get() = prefs.getString(PREFS_NAME, "")
    set(value) = prefs.edit().putString(PREFS_NAME, value).apply()

    var contactNo: String?
        get() = prefs.getString(PREFS_CONTACT_NO, "")
        set(value) = prefs.edit().putString(PREFS_CONTACT_NO, value).apply()

    var emailId: String?
        get() = prefs.getString(PREFS_EMAIL_ID, "")
        set(value) = prefs.edit().putString(PREFS_EMAIL_ID, value).apply()



}