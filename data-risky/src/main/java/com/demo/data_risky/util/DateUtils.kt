package com.demo.data_risky.util

import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DateUtils @Inject constructor() {
    fun convertDate(value: String): String{
        val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)
        val date = sdf.parse(value)
        return SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH).format(date).toString()
    }
}