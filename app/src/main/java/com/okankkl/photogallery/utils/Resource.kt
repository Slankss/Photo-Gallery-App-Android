package com.okankkl.photogallery.utils

sealed class Resource<T>(var data: T? = null,var errorMessage: String? = null) {
    class Success<T>(data: T) : Resource<T>(data)
    class Failed<T>(errorMessage: String) : Resource<T>(errorMessage = errorMessage)
}