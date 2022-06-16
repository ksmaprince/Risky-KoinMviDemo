package com.demo.data.datasource.remote

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.demo.data.BuildConfig
import com.demo.data.datasource.remote.interceptor.NoConnectionInterceptor
import com.demo.data.datasource.remote.interceptor.ResponseInterceptor
import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.util.concurrent.TimeUnit

class RestAdapter {
    companion object{
        private var retrofit: Retrofit? = null

        fun get(context: Context): Retrofit{
            if (retrofit==null){
                synchronized(RestAdapter::class){
                    if (retrofit==null){
                        val httpClient = OkHttpClient.Builder()
                        httpClient.connectTimeout(60, TimeUnit.SECONDS)
                        httpClient.readTimeout(60, TimeUnit.SECONDS)
                        httpClient.writeTimeout(60, TimeUnit.SECONDS)
                        if (BuildConfig.DEBUG){
                            val httpLogginInterceptor = HttpLoggingInterceptor()
                            httpLogginInterceptor.level = HttpLoggingInterceptor.Level.HEADERS
                            httpLogginInterceptor.level = HttpLoggingInterceptor.Level.BODY
                            httpClient.addInterceptor(httpLogginInterceptor)
                        }
                        httpClient.addInterceptor(NoConnectionInterceptor(context))
                        httpClient.addInterceptor(ResponseInterceptor())
                        httpClient.addInterceptor(ChuckerInterceptor(context))
                        retrofit = buildRetrofit(httpClient)
                    }
                }
            }
        }

        private fun buildRetrofit(httpClient: OkHttpClient.Builder): Retrofit{
            return Retrofit.Builder()
                .baseUrl(BuildConfig.BASEURL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(Gson()))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(httpClient.build())
                .build()

        }
    }
}