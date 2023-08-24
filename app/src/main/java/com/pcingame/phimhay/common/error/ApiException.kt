package com.pcingame.phimhay.common.error

import retrofit2.Response
import java.io.IOException

class ApiException(
    val type: Type,
    val errorResponse: BaseErrorResponse? = null,
    val response: Response<*>? = null,
    val exception: Throwable? = null
) : RuntimeException() {

    val errorMessage get() = errorResponse?.statusMessage

    enum class Type {
        /** An [IOException] or [UnknownHostException] occurred while communicating to the server.  */
        NETWORK,

        /** A non-200 HTTP status code was received from the server.  */
        HTTP,
        SERVER,

        /**
         * An internal error occurred while attempting to execute a request. It is best practice to
         * re-throw this exception so your application crashes.
         */
        UNEXPECTED
    }

    companion object {
        fun httpError(response: Response<*>, error: BaseErrorResponse? = null) =
            ApiException(Type.HTTP, error, response)

        fun serverError(serverErrorResponse: BaseErrorResponse) =
            ApiException(Type.SERVER, serverErrorResponse)

        fun networkError(throwable: Throwable) =
            ApiException(Type.NETWORK, null, null, throwable)

        fun unexpectedError(throwable: Throwable) =
            ApiException(Type.UNEXPECTED, null, null, throwable)
    }
}
