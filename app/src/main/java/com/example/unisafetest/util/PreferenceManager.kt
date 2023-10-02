package com.example.unisafetest.util

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences

class PreferenceManager() {
    private var sharedPreferences: SharedPreferences? = null

    public fun preferenceManager(context: Context) {
        sharedPreferences =
            context.getSharedPreferences(Constants.KEY_PREFERENCE_NAME, Context.MODE_PRIVATE)
    }

    @SuppressLint("CommitPrefEdits")
    public fun putBoolean(key: String, value: Boolean) {
        val editor: SharedPreferences.Editor = sharedPreferences!!.edit()
        editor.putBoolean(key, value)
        editor.apply()
    }

    public fun getBoolean(key: String): Boolean {
        return sharedPreferences!!.getBoolean(key, false)
    }

    public fun putString(key: String, value: String) {
        val editor: SharedPreferences.Editor = sharedPreferences!!.edit()
        editor.putString(key, value)
        editor.apply()
    }

    public fun getString(key: String): String {
        return sharedPreferences!!.getString(key, null)!!
    }

    public fun clear() {
        val editor: SharedPreferences.Editor = sharedPreferences!!.edit()
        editor.clear()
        editor.apply()
    }

}
