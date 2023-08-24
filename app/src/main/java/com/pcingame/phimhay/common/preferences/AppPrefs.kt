package com.pcingame.phimhay.common.preferences

import android.content.Context
import com.google.gson.Gson
import com.pcingame.phimhay.common.DebugLog
import com.pcingame.phimhay.common.utils.CryptUtil
import com.pcingame.phimhay.di.App
import org.koin.android.BuildConfig

object AppPrefs {
    private const val PREF_NAME = "moviedb"

    private val sharedPrefs by lazy {
        App.context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
    }

    private val gson = Gson()

    /**
     * Remove object
     * @param key is the key for saving
     */
    fun remove(key: String) {
        sharedPrefs.edit().remove(key).apply()
    }

    /**
     * Remove all object
     */
    fun removeAll() {
        sharedPrefs.edit().clear().apply()
    }

    /**
     * Save string value
     * @param key is the key for saving
     * @param value is the value to save
     */
    fun saveString(key: String, value: String) {
        val encValue = try {
            if (BuildConfig.DEBUG) {
                value
            } else {
                CryptUtil.encrypt(value, key) ?: ""
            }
        } catch (e: Exception) {
            DebugLog().d(e.message.toString())
            ""
        }
        sharedPrefs.edit()
            .putString(key, encValue)
            .apply()
    }

    /**
     * Get string value from pfs
     * @param key is the key for getting
     */
    fun getString(key: String): String? {
        return try {
            // Get value
            val value =
                sharedPrefs.getString(key, null)
                    ?: return null
            // // Decrypt value
            if (BuildConfig.DEBUG) {
                value
            } else {
                CryptUtil.decrypt(value, key)
            }
        } catch (e: Exception) {
            DebugLog().d(e.message.toString())
            return null
        }
    }

    /**
     * Save boolean value
     * @param key is the key for saving
     * @param value is the value to save
     */
    fun saveLong(key: String, value: Long?) {
        sharedPrefs.edit()
            .putLong(key, value ?: 0)
            .apply()
    }

    /**
     * Get long value from pfs
     * @param key is the key for getting
     */
    fun getLong(key: String): Long {
        return sharedPrefs.getLong(key, 0L)
    }

    /**
     * Save boolean value
     * @param key is the key for saving
     * @param value is the value to save
     */
    fun saveBoolean(key: String, value: Boolean?) {
        sharedPrefs.edit()
            .putBoolean(key, value ?: false)
            .apply()
    }

    /**
     * Get boolean value from pfs
     * @param key is the key for getting
     */
    fun getBoolean(key: String): Boolean {
        return sharedPrefs.getBoolean(key, false)
    }

    /**
     * This is function clear data reference
     */
    fun clear() {
        sharedPrefs.edit().clear().apply()
    }

    /**
     * Save int value
     * @param key is the key for saving
     * @param value is the value to save
     */
    fun saveInt(key: String, value: Int?) {
        sharedPrefs.edit()
            .putInt(key, value ?: 0)
            .apply()
    }

    /**
     * Get int value from pfs
     * @param key is the key for getting
     */
    fun getInt(key: String): Int {
        return sharedPrefs.getInt(key, 0)
    }

    @Suppress("UNCHECKED_CAST")
    operator fun <T> get(key: String, anonymousClass: Class<T>, defaultValue: T): T {
        return kotlin.runCatching {
            when (anonymousClass) {
                String::class.java -> sharedPrefs.getString(key, defaultValue as? String)
                Boolean::class.java -> {
                    sharedPrefs.getBoolean(key, defaultValue as? Boolean ?: false)
                }
                Float::class.java -> sharedPrefs.getFloat(key, defaultValue as? Float ?: 0f)
                Int::class.java -> sharedPrefs.getInt(key, defaultValue as? Int ?: 0)
                Long::class.java -> sharedPrefs.getLong(key, defaultValue as? Long ?: 0L)
                else -> {
                    gson.fromJson(sharedPrefs.getString(key, ""), anonymousClass) ?: defaultValue
                }
            }
        }.getOrNull() as? T ?: defaultValue
    }

    @Suppress("UNCHECKED_CAST")
    fun <T> getOrNull(key: String, anonymousClass: Class<T>): T? {
        return kotlin.runCatching {
            when (anonymousClass) {
                String::class.java -> getString(key)
                Boolean::class.java -> getBoolean(key)
                Float::class.java -> sharedPrefs.getFloat(key, 0f)
                Int::class.java -> getInt(key)
                Long::class.java -> getLong(key)
                else -> gson.fromJson(getString(key), anonymousClass)
            }
        }.getOrNull() as? T
    }

    fun <T> save(key: String, data: T?) {
        kotlin.runCatching {
            val editor = sharedPrefs.edit()
            when (data) {
                is String -> saveString(key, data)
                is Boolean -> saveBoolean(key, data)
                is Float -> editor.putFloat(key, data)
                is Int -> saveInt(key, data)
                is Long -> saveLong(key, data)
                else -> saveString(key, gson.toJson(data))
            }
            editor.apply()
        }
    }
}
