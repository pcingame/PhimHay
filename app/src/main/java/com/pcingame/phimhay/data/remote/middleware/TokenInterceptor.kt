package com.pcingame.phimhay.data.remote.middleware

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import java.net.HttpURLConnection
import java.util.*

class TokenInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val builder = initializeHeader(chain)
        val request = builder.build()
        var response = chain.proceed(request)

        if (response.code == HttpURLConnection.HTTP_UNAUTHORIZED) {
            // refresh token
            val newToken = UUID.randomUUID().toString()
            val newRequest = initNewRequest(request, newToken)
            response = chain.proceed(newRequest)
        }
        return response
    }

    private fun initNewRequest(request: Request, accessToken: String?): Request {
        val builder = request.newBuilder()
            .removeHeader(HEADER_AUTHORIZATION)
        accessToken?.let {
            builder.header(HEADER_AUTHORIZATION, it)
        }
        return builder.build()
    }


    private fun initializeHeader(chain: Interceptor.Chain): Request.Builder {
        val originRequest = chain.request()
        // get token
        val token = UUID.randomUUID().toString()
        val builder = originRequest.newBuilder()
            .header(HEADER_ACCEPT, "application/json")
            .addHeader(HEADER_CACHE_CONTROL, "no-cache")
            .addHeader(HEADER_CACHE_CONTROL, "no-store")
            .method(originRequest.method, originRequest.body)
        builder.addHeader(HEADER_AUTHORIZATION, token)
        return builder
    }

    companion object {
        const val HEADER_AUTHORIZATION = "Authorization"
        const val HEADER_CACHE_CONTROL = "Cache-Control"
        const val HEADER_ACCEPT = "Accept"
    }
}
