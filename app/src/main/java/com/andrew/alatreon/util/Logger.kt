package com.andrew.alatreon.util

import android.util.Log

interface Logger {
    val loggerTag: String
        get() = javaClass.simpleName

    fun logDebug(message: String) {
        Log.d(loggerTag, message)
    }

    fun logError(message: String) {
        Log.e(loggerTag, message)
    }

    fun logWarning(message: String) {
        Log.w(loggerTag, message)
    }

    fun logInfo(message: String) {
        Log.i(loggerTag, message)
    }
}