package com.okankkl.photogallery.utils

object UrlUtils{
    fun shortUrl(url: String): String {
        val regex = Regex("^(https?://[^?#]+)")
        val match = regex.find(url)
        return match?.value ?: url
    }
}
