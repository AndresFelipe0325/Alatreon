package com.andrew.alatreon.util

import android.util.Log

interface Logger {
    val tag: String
        get() = javaClass.simpleName

    fun LogDebug(message: String) {
        Log.d(tag, message)
    }

    fun LogError(message: String) {
        Log.e(tag, message)
    }

    fun logWarning(message: String) {
        Log.w(tag, message)
    }

    fun logInfo(message: String) {
        Log.i(tag, message)
    }
}