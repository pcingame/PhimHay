package com.pcingame.phimhay.data.remote.middleware

import com.pcingame.phimhay.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

class ApiKeyInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val origin = chain.request()
        val url = origin.url.newBuilder()
            .addQueryParameter(QUERY_API_KEY, BuildConfig.TMBD_API_KEY)
            .addQueryParameter(QUERY_LANGUAGE, LANGUAGE_VIETNAMESE)
            .build()
        val newRequest = origin.newBuilder().url(url).build()
        return chain.proceed(newRequest)
    }

    companion object {
        private const val QUERY_API_KEY = "api_key"
        private const val QUERY_LANGUAGE = "language"
        private const val LANGUAGE_VIETNAMESE = "vi"
    }
}
