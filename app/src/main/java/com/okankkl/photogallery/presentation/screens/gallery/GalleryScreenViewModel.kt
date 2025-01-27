package com.okankkl.photogallery.presentation.screens.gallery

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.okankkl.photogallery.data.remote.dto.PhotoDto
import com.okankkl.photogallery.data.repository.PhotoRepository
import com.okankkl.photogallery.utils.enums.FilterType
import com.okankkl.photogallery.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GalleryScreenViewModel @Inject constructor(
    private val photoRepository: PhotoRepository
): ViewModel(){

    private val _viewState = MutableStateFlow(GalleryScreenViewState())
    val viewState = _viewState.asStateFlow()

    fun fetchPhotos(page:Int? = null){
        viewModelScope.launch(Dispatchers.IO){
            _viewState.update { it.copy(isLoading = true) }
            val filterSettings = viewState.value.filterSettings

            photoRepository.fetchPhotos(
                page = if (page != null ) page else viewState.value.currentPage,
                orientation = filterSettings.orientation?.query,
                order = filterSettings.order?.query,
                color = filterSettings.color?.query,
                category = filterSettings.category?.query,
                query = viewState.value.query
            ).let { result ->
                if (result is Resource.Success){
                    _viewState.update {
                        it.copy(
                            isLoading = false,
                            photos = if (it.photos == null || page != null) result.data else it.photos.plus(result.data!!),
                            currentPage = if (page != null) page+1 else it.currentPage + 1,
                            errorMessage = null
                        )
                    }
                } else if(viewState.value.photos == null) {
                    _viewState.update {
                        it.copy(
                            isLoading = false,
                            errorMessage = result.errorMessage
                        )
                    }
                }
            }
        }
    }

    fun searchPhotos(query: String){
        viewModelScope.launch(Dispatchers.IO){
            _viewState.update { it.copy(isLoading = true) }
            val filterSettings = viewState.value.filterSettings

            photoRepository.fetchPhotos(
                page = 1,
                orientation = filterSettings.orientation?.query,
                order = filterSettings.order?.query,
                color = filterSettings.color?.query,
                category = filterSettings.category?.query,
                query = query
            ).let { result ->
                if (result is Resource.Success && result.data != null){
                    _viewState.update {
                        it.copy(
                            isLoading = false,
                            photos = result.data,
                            currentPage = 2,
                            errorMessage = null,
                            query = query
                        )
                    }
                } else {
                    _viewState.update {
                        it.copy(
                            isLoading = false,
                            errorMessage = result.errorMessage,
                            currentPage = 1
                        )
                    }
                }
            }
        }
    }

    fun setFilterSettings(
        filterSettings: FilterSettings
    ){
        _viewState.update {
            it.copy(
                filterSettings = filterSettings
            )
        }
    }

    fun downloadPhoto(photoUrl: String){
        viewModelScope.launch(Dispatchers.IO){
            photoRepository.downloadFile(photoUrl)
        }
    }
}

data class GalleryScreenViewState(
    val isLoading: Boolean = false,
    val photos: List<PhotoDto>? = null,
    val currentPage: Int = 1,
    val errorMessage: String? = null,
    val filterSettings: FilterSettings = FilterSettings(),
    val query: String? = null,
)

data class FilterSettings(
    val orientation: FilterType? = null,
    val order: FilterType? = null,
    val color: FilterType? = null,
    val category: FilterType? = null
)