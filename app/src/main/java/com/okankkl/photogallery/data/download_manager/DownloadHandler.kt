package com.okankkl.photogallery.data.download_manager

import android.app.DownloadManager
import android.os.Environment
import androidx.core.net.toUri

class DownloadHandler constructor(
    private val downloadManager: DownloadManager
){
    fun downloadFile(photoUrl: String){
        val request = DownloadManager.Request(photoUrl.toUri())
            .setMimeType("image/jpeg")
            //.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI)
            .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
            .setTitle("image.jpg")
            //.addRequestHeader("Authorization", "Bearer <token>")
            .setDestinationInExternalPublicDir(Environment.DIRECTORY_PICTURES,"image.jpg")

        downloadManager.enqueue(request)
    }
}