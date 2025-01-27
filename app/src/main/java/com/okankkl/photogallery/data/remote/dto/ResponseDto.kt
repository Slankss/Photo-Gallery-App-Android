package com.okankkl.photogallery.data.remote.dto

data class ResponseDto(
    val total:Int? = null,
    val totalHits:Int? = null,
    val hits:List<PhotoDto>? = null
)
