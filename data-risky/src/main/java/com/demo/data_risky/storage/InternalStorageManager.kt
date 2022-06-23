package com.demo.data_risky.storage

import android.content.Context
import android.content.SharedPreferences
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class InternalStorageManager @Inject constructor( context: Context){
    private var mSharedPreferences: SharedPreferences = context.getSharedPreferences("risky_pref", Context.MODE_PRIVATE)

    private val KEY_ACCESS_TOKEN = "auth-token"

    fun clear(){
        val mEditor = mSharedPreferences.edit()
        mEditor.clear()
        mEditor.apply()
    }

    fun saveAccessToken(token: String) = mSharedPreferences.edit().putString(KEY_ACCESS_TOKEN, token).apply()

    fun loadAccessToken(): String = mSharedPreferences.getString(KEY_ACCESS_TOKEN, "") ?: ""

}