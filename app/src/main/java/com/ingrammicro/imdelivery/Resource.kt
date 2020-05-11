package com.ingrammicro.imdelivery


// A generic class that contains data and status about loading this data.
sealed class Resource<T>(
    val data: T? = null,
    val message: String? = null,
    val apiError: ApiError? = null
) {
    class Success<T>(data: T) : Resource<T>(data)
    class Loading<T>(data: T? = null) : Resource<T>(data)
    class Error<T>(
        message: String? = null,
        apiError: ApiError? = null,
        data: T? = null
    ) : Resource<T>(data, message, apiError)
}