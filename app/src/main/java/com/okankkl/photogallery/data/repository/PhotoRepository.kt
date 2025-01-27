package com.okankkl.photogallery.data.repository

import com.okankkl.photogallery.data.remote.PhotoApi
import com.okankkl.photogallery.data.remote.dto.PhotoDto
import com.okankkl.photogallery.data.download_manager.DownloadHandler
import com.okankkl.photogallery.utils.Resource
import javax.inject.Inject

class PhotoRepository @Inject constructor(
    private val photoApi: PhotoApi,
    private val downloadHandler: DownloadHandler
) {
    suspend fun fetchPhotos(
        query: String? = null,
        page: Int?,
        orientation: String?,
        order: String?,
        color: String?,
        category: String?
        ): Resource<List<PhotoDto>>{
        try {
            val response = photoApi.fetchPhotos(
                query= query,
                page = page,
                orientation = orientation,
                order = order,
                color = color,
                category = category
            )
            if (response.hits.isNullOrEmpty()){
                return Resource.Failed(errorMessage = "No Photos Found")
            }
            return Resource.Success(response.hits)
        } catch (e: Exception){
            return Resource.Failed(errorMessage = e.localizedMessage ?: "Unexpected Error")
        }
    }

    fun downloadFile(photoUrl: String){
        try {
            downloadHandler.downloadFile(photoUrl)
        } catch (_: Exception){

        }
    }
}