package com.example.shoppingtestingapp.data

sealed class ResponseResult<out T: Any> {
    data class Success<out T : Any>(val data: T) : ResponseResult<T>()
    data class Error(val exception: String) : ResponseResult<Nothing>()
    data class NetworkError(val code:String): ResponseResult<Nothing>()
    object isLoading: ResponseResult<Nothing>()
}