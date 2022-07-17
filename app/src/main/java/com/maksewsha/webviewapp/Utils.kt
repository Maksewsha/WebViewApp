package com.maksewsha.webviewapp

import android.content.Context
import android.content.Context.MODE_PRIVATE

import android.content.SharedPreferences
import android.util.Log


private const val SHARED_PREFS = "sharedPrefs"
private const val KEY = "myKey"

fun saveData(context: Context, url: String) {
    val sharedPreferences: SharedPreferences =
        context.getSharedPreferences(SHARED_PREFS, MODE_PRIVATE)
    val editor = sharedPreferences.edit()
    editor.putString(KEY, url)
    editor.apply()

    Log.d("MY_TAG", url)
}

fun loadData(context: Context): String {
    val sharedPreferences: SharedPreferences =
        context.getSharedPreferences(SHARED_PREFS, MODE_PRIVATE)
    Log.d("MY_TAG", sharedPreferences.getString(KEY, "")!!)
    return sharedPreferences.getString(KEY, "")!!
}