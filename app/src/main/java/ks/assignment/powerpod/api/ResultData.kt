package ks.assignment.powerpod.api

sealed class ResultData<out T> {

    data class Success<out T>(val data: T? = null): ResultData<T>()

    object Loading: ResultData<Nothing>()

    data class Failed(val message: String? = null): ResultData<Nothing>()

}