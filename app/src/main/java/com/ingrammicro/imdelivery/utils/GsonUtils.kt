package com.ingrammicro.imdelivery.utils

import com.google.gson.Gson
import com.ingrammicro.imdelivery.account.model.DriverDetails


class GsonUtils{

    fun userInfoToJsonString(userInfo: DriverDetails): String {
        val gson = Gson()
        return gson.toJson(userInfo)
    }

    fun jsonStringToUserInfo(jsonString: String?): DriverDetails {
        val gson = Gson()
        return gson.fromJson<DriverDetails>(jsonString, DriverDetails::class.java)
    }
}