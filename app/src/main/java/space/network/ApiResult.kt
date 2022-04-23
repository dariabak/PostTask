package space.network

sealed class ApiResult<out T> {
    data class Success<out T>(val value: T): ApiResult<T>()
    data class Failure(val error: ApiError): ApiResult<Nothing>()
}