package com.ingrammicro.imdelivery.network

import androidx.lifecycle.MutableLiveData
import com.ingrammicro.imdelivery.ApiError
import com.ingrammicro.imdelivery.R
import com.ingrammicro.imdelivery.Resource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.net.ConnectException
import java.net.SocketTimeoutException

open class NetworkCall<T> {

    lateinit var call: Call<T>

    fun makeCall(call: Call<T>): MutableLiveData<Resource<T>> {
        this.call = call
        val callBackKt = CallBackKt<T>()
        callBackKt.result.value = Resource.Loading()
        this.call.clone().enqueue(callBackKt)
        return callBackKt.result
    }

    class CallBackKt<T> : Callback<T> {

        var result: MutableLiveData<Resource<T>> = MutableLiveData()

        override fun onFailure(call: Call<T>, t: Throwable) {
            val error = when (t) {
                is SocketTimeoutException -> ApiError(R.string.error_rest_timeout, ApiError.Type.TIMEOUT)
                is ConnectException -> ApiError(R.string.error_rest_network, ApiError.Type.NETWORK)
                else -> ApiError(R.string.error_rest_unexpected, ApiError.Type.UNEXPECTED)
            }

            result.value = Resource.Error(apiError = error)
        }

        override fun onResponse(call: Call<T>, response: Response<T>) {
            if (response.isSuccessful)
                result.value = response.body()?.let { Resource.Success(it) }
            else {
                val error = ApiError(R.string.error_rest_unexpected, ApiError.Type.UNEXPECTED)
                result.value = Resource.Error(apiError = error)
            }
        }
    }

    fun cancel() {
        if (::call.isInitialized) {
            call.cancel()
        }
    }
}