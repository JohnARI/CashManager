package com.example.moulamanagerclient.utils

import android.content.Context
import androidx.preference.PreferenceManager


object SharedPreferences {
    fun setValue(context: Context, key: String, value: String){
        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
        sharedPreferences.edit().putString(key, value).apply()
    }
    fun getKey(context: Context, key:String): String? {
        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
        return sharedPreferences.getString(key, "")
    }
}
