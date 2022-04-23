package space.network

sealed class ApiError: Throwable() {
    object Generic: ApiError()
    object NoInternetConnection: ApiError()
}