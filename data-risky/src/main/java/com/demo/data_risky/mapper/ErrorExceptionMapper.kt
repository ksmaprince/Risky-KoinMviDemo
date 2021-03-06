package com.demo.data_risky.mapper

import com.demo.data_risky.exception.NetworkException
import java.io.IOException
import java.net.SocketTimeoutException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ErrorExceptionMapper @Inject constructor(){
    fun mapException(throwable: Throwable): NetworkException {
        return when (throwable) {
            is IOException -> NetworkException.ApiException(throwable.message.toString())
            is SocketTimeoutException -> NetworkException.TimeOutException
            is NetworkException.NoInternetException -> NetworkException.NoInternetException()
            is NetworkException.NoConnectivityException -> NetworkException.NoConnectivityException()
            else -> NetworkException.DefaultException(throwable.message.toString())
        }
    }
}