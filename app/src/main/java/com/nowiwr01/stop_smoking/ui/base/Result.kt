package com.nowiwr01.stop_smoking.ui.base

/**
 * Result method with success or fail.
 */
sealed class Result<out V, out E>(val isSuccess: Boolean) {
    /**
     * Success result execution. Contains success value.
     */
    data class Success<V>(val value: V) : Result<V, Nothing>(true)

    /**
     * Result execution failed. Contain error value.
     */
    data class Fail<E>(val error: E) : Result<Nothing, E>(false)
}

/**
 * Conversion of [Result] and process possible variants.
 */
inline fun <V, E> Result<V, E>.mapBoth(success: (V) -> Unit, failure: (E) -> Unit) {
    return when (this) {
        is Result.Success -> success(value)
        is Result.Fail -> failure(error)
    }
}

/**
 * Conversion of [Result] and process failure variant. Skip success variation.
 */
inline fun <E> Result<Any?, E>.mapFailure(failure: (E) -> Unit) {
    return when (this) {
        is Result.Fail -> failure(error)
        else -> Unit
    }
}

/**
 * Conversion of [Result] and process success variant. Skip failure variation.
 */
inline fun <V> Result<V, Any?>.mapSuccess(success: (V) -> Unit) {
    return when (this) {
        is Result.Success -> success(value)
        else -> Unit
    }
}