package com.eyepax.newsapp.util

import android.content.Context

class SharedPreferenceHelper private constructor(private val context: Context) {

    companion object{

        private const val PREF_FILE = "PREF"
        private var mInstance: SharedPreferenceHelper? = null
        @Synchronized
        fun getInstance(mCtx: Context): SharedPreferenceHelper {
            if (mInstance == null) {
                mInstance = SharedPreferenceHelper(mCtx)
            }
            return mInstance as SharedPreferenceHelper
        }

    }


    /**
     * Set a string shared preference
     * @param key - Key to set shared preference
     * @param value - Value for the key
     */
    fun setSharedPreferenceString(key: String?, value: String?) {
        val settings = context.getSharedPreferences(PREF_FILE, Context.MODE_PRIVATE)
        val editor = settings.edit()
        editor.putString(key, value)
        editor.apply()
    }

    /**
     * Set a integer shared preference
     * @param key - Key to set shared preference
     * @param value - Value for the key
     */
    fun setSharedPreferenceInt(key: String?, value: Int) {
        val settings = context.getSharedPreferences(PREF_FILE, Context.MODE_PRIVATE)
        val editor = settings.edit()
        editor.putInt(key, value)
        editor.apply()
    }

    /**
     * Set a long shared preference
     * @param key - Key to set shared preference
     * @param value - Value for the key
     */
    fun setSharedPreferenceLong(key: String?, value: Long) {
        val settings = context.getSharedPreferences(PREF_FILE, Context.MODE_PRIVATE)
        val editor = settings.edit()
        editor.putLong(key, value)
        editor.apply()
    }

    /**
     * Set a Boolean shared preference
     * @param key - Key to set shared preference
     * @param value - Value for the key
     */
    fun setSharedPreferenceBoolean(key: String?, value: Boolean) {
        val settings = context.getSharedPreferences(PREF_FILE, Context.MODE_PRIVATE)
        val editor = settings.edit()
        editor.putBoolean(key, value)
        editor.apply()
    }

    /**
     * Get a string shared preference
     * @param key - Key to look up in shared preferences.
     * @param defValue - Default value to be returned if shared preference isn't found.
     * @return value - String containing value of the shared preference if found.
     */
    fun getSharedPreferenceString(key: String?, defValue: String?): String? {
        val settings = context.getSharedPreferences(PREF_FILE, Context.MODE_PRIVATE)
        return settings.getString(key, defValue)
    }

    /**
     * Get a integer shared preference
     * @param key - Key to look up in shared preferences.
     * @param defValue - Default value to be returned if shared preference isn't found.
     * @return value - String containing value of the shared preference if found.
     */
    fun getSharedPreferenceInt(key: String?, defValue: Int): Int {
        val settings = context.getSharedPreferences(PREF_FILE, Context.MODE_PRIVATE)
        return settings.getInt(key, defValue)
    }

    /**
     * Get a long shared preference
     * @param key - Key to look up in shared preferences.
     * @param defValue - Default value to be returned if shared preference isn't found.
     * @return value - String containing value of the shared preference if found.
     */
    fun getSharedPreferenceLong(key: String?, defValue: Long): Long {
        val settings = context.getSharedPreferences(PREF_FILE, Context.MODE_PRIVATE)
        return settings.getLong(key, defValue)
    }

    /**
     * Get a boolean shared preference
     * @param key - Key to look up in shared preferences.
     * @param defValue - Default value to be returned if shared preference isn't found.
     * @return value - String containing value of the shared preference if found.
     */
    fun getSharedPreferenceBoolean(key: String?, defValue: Boolean): Boolean {
        val settings = context.getSharedPreferences(PREF_FILE, Context.MODE_PRIVATE)
        return settings.getBoolean(key, defValue)
    }

    /**Clear all shared preference
     */
    fun clearSharedPreference() {
        val sharedPreferences = context.getSharedPreferences(PREF_FILE, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.clear()
        editor.apply()
    }


}