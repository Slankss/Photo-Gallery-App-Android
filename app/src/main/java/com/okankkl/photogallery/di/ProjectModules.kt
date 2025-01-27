package com.okankkl.photogallery.di

import android.app.DownloadManager
import android.content.Context
import android.content.Context.DOWNLOAD_SERVICE
import com.okankkl.photogallery.data.remote.PhotoApi
import com.okankkl.photogallery.data.repository.PhotoRepository
import com.okankkl.photogallery.utils.Constants.BASE_URL
import com.okankkl.photogallery.data.download_manager.DownloadHandler
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class ProjectModules {

    @Singleton
    @Provides
    fun providePhotoApi(): PhotoApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(PhotoApi::class.java)
    }

    @Singleton
    @Provides
    fun providePhotoRepository(photoApi: PhotoApi,downloadHandler: DownloadHandler): PhotoRepository {
        return PhotoRepository(photoApi,downloadHandler)
    }

    @Singleton
    @Provides
    fun provideDownloadManager(@ApplicationContext appContext: Context): DownloadManager{
        return appContext.getSystemService(DOWNLOAD_SERVICE) as DownloadManager
    }

    @Singleton
    @Provides
    fun provideDownloadHandler(downloadManager: DownloadManager): DownloadHandler {
        return DownloadHandler(downloadManager)
    }

}