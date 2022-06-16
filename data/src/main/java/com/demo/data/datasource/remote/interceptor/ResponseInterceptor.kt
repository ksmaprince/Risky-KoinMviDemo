package com.demo.data.datasource.remote.interceptor

import android.util.Log
import okhttp3.Interceptor
import okhttp3.Response

class ResponseInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain?): Response {
        val request = chain?.request()
        Log.i("REQUEST: ", request.toString())
        val response = chain?.proceed(request)
        Log.e("Bus", "intercept ${response?.code()}")

        if (response?.code() == 401) {
            HttpErrorEvent.emit(
                HttpErrorEvent.Event(
                    "",
                    HttpErrorEvent.Type.UNAUTHORIZED
                )
            )
        }

        if ((response?.code() ?: 0) >= 500) {
            HttpErrorEvent.emit(
                HttpErrorEvent.Event(
                    "",
                    HttpErrorEvent.Type.SERVER_ERROR
                )
            )
        }

        if (response?.code() == 503) {
            HttpErrorEvent.emit(
                HttpErrorEvent.Event(
                    "",
                    HttpErrorEvent.Type.MAINTENANCE
                )
            )
        }
        return response!!
    }
}