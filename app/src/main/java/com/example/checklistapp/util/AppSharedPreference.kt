package com.example.checklistapp.util

import android.content.Context
import android.content.SharedPreferences
import com.example.checklistapp.IS_LOGGED_IN
import com.example.checklistapp.SHARED_PREFERENCE_NAME
import com.example.checklistapp.model.ResponseMessage
import retrofit2.Callback

class AppSharedPreference(context: Context) {
    val sf = context.getSharedPreferences(SHARED_PREFERENCE_NAME,Context.MODE_PRIVATE)

    fun setUserSession(isLoggedIn : Boolean) {
        val editor = sf.edit()
        editor.putBoolean(IS_LOGGED_IN,isLoggedIn)
        editor.apply()
    }

    fun getUserSession() : Boolean {
        return sf.getBoolean(IS_LOGGED_IN,false)
    }



}