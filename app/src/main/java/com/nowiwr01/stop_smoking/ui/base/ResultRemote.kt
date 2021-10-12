package com.nowiwr01.stop_smoking.ui.base

data class ResultRemote<out T>(
    val status: Status,
    val data: T?,
    val message: String = "unknown message",
    val code: Int? = null
) {

    enum class Status {
        SUCCESS,
        ERROR
    }

    companion object {
        fun <T> success(data: T): ResultRemote<T> {
            return ResultRemote(
                Status.SUCCESS,
                data
            )
        }

        fun <T> error(message: String, data: T? = null, code: Int? = null): ResultRemote<T> {
            return ResultRemote(
                Status.ERROR,
                data,
                message,
                code
            )
        }

        fun empty(message: String): ResultRemote<String> {
            return ResultRemote(
                Status.SUCCESS,
                "",
                message
            )
        }
    }
}