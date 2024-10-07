package com.android.encora.weather.api.data
/*
Data is the API class is the primary response from the API that holds status code, api message and results data
*/
data class Data<out T>(val status: Status, val data: T?, val message: String?) {
    companion object {
        fun <T> success(data: T?): Data<T> { return Data(Status.SUCCESS, data, null) }

        fun <T> error(msg: String, data: T?): Data<T> { return Data(Status.ERROR, data, msg) }

        fun <T> loading(data: T?): Data<T> { return Data(Status.LOADING, data, null) }
    }

}

enum class Status { SUCCESS, ERROR, LOADING }