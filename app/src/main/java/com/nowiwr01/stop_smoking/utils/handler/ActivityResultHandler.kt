package com.nowiwr01.stop_smoking.utils.handler

import android.content.Intent

/**
 * Activity result handler interface. Uses for catch activity result in chain.
 * */
interface ActivityResultHandler {
    /**
     * On activity result callback method. Returns true, when result handled.
     * */
    fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?): Boolean
}

/**
 * Try to find first [ActivityResultHandler] in order and process result.
 * */
class ActivityResultChain(
    private val handlerList: List<ActivityResultHandler>
) {
    fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?): Boolean {
        handlerList.forEach {
            if (it.onActivityResult(requestCode, resultCode, data)) {
                return true
            }
        }
        return false
    }
}