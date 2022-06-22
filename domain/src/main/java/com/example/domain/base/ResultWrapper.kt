package com.example.domain.base


sealed class ResultWrapper<out T> {
    data class Success<out T>(val value: T) : ResultWrapper<T>()
    data class Error(val description: String) : ResultWrapper<Nothing>()
    object NetworkError : ResultWrapper<Nothing>()
}