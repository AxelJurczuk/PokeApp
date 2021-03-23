package com.example.android.data.remote

sealed class ResultHandler <out T: Any>{
    data class Success<out T: Any>(val data: T): ResultHandler<T>()
    data class HttpError(val code: Int?, val message: String?): ResultHandler<Nothing>()
    data class GenericError(val message: String?): ResultHandler<Nothing>()
    object NetworkError: ResultHandler<Nothing>()
}