package com.example.shopapp.preference

import android.content.Context
import android.content.SharedPreferences
import com.example.shopapp.constants.Constants

class SharedPref(var _context: Context) {
    var pref: SharedPreferences
    var editor: SharedPreferences.Editor

    // Shared pref mode
    var PRIVATE_MODE = 0

    init {
        pref = _context.getSharedPreferences(Constants.PREF_NAME, PRIVATE_MODE)
        editor = pref.edit()
    }

    var backgroundColor: String?
        get() = pref.getString(Constants.PREF_BACKGROUND_COLOR, "")
        set(color) {
            editor.putString(Constants.PREF_BACKGROUND_COLOR, color)
            editor.commit()
        }
}