package com.demo.data.mapper

import com.demo.data.exception.NetworkException
import java.io.IOException
import java.net.SocketTimeoutException

class ErrorExceptionMapper {
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