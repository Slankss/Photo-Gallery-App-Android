package com.okankkl.photogallery.data.remote

import com.okankkl.photogallery.data.remote.dto.ResponseDto
import com.okankkl.photogallery.utils.Constants.API_KEY
import retrofit2.http.GET
import retrofit2.http.Query

interface PhotoApi {

    @GET("api/")
    suspend fun fetchPhotos(
        @Query("key") apiKey: String = API_KEY,
        @Query("q") query: String? = null,
        @Query("page") page: Int? = null,
        @Query("orientation") orientation: String? = null,
        @Query("order") order: String? = null,
        @Query("colors") color: String? = null,
        @Query("category") category: String? = null,
        @Query("per_page") perPage: Int = 25,
        @Query("lang") lang: String = "en",
        @Query("pretty") pretty: Boolean = true
    ): ResponseDto

}