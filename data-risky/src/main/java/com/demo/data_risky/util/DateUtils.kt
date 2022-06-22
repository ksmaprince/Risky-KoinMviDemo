package com.demo.data_risky.util

import java.text.SimpleDateFormat
import java.util.*

class DateUtils {
    fun convertDate(value: String): String{
        val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)
        val date = sdf.parse(value)
        return SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH).format(date).toString()
    }
}