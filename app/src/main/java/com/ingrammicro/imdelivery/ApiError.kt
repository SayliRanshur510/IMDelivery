package com.ingrammicro.imdelivery



class ApiError(
    var strId: Int = 0,
    var type: Type? = null,
    var message: String? = null,
    var statusCode: Int = 0
) {
    enum class Type(val stateTag: String) {
        NETWORK(STATE_ERROR_NETWORK),
        TIMEOUT(STATE_ERROR_TIMEOUT),
        UNEXPECTED(STATE_ERROR_UNEXPECTED)
    }
}