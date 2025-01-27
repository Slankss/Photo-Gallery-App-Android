package com.okankkl.photogallery.presentation.screens.gallery

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.okankkl.photogallery.R
import com.okankkl.photogallery.presentation.screens.gallery.components.SearchBar
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.repeatOnLifecycle
import com.okankkl.photogallery.data.remote.dto.PhotoDto
import com.okankkl.photogallery.presentation.screens.gallery.components.ImageBox
import com.okankkl.photogallery.utils.PermissionHandler.checkWritePermission
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GalleryScreen(
    modifier: Modifier = Modifier,
    navigateToPhotoWebPageScreen: (photoWebPageUrl: String) -> Unit
){
    val lifecycle = LocalLifecycleOwner.current.lifecycle
    val galleryScreenViewModel: GalleryScreenViewModel = hiltViewModel()
    val viewState = galleryScreenViewModel.viewState.collectAsState()
    val modalState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    var bottomSheetVisible by remember { mutableStateOf(false) }
    val listState = rememberLazyListState()
    val visibleListItems by remember { derivedStateOf { listState.layoutInfo.visibleItemsInfo } }
    val scope = rememberCoroutineScope()
    var photoDialogVisible by remember { mutableStateOf(false) }
    var selectedPhoto by remember { mutableStateOf<PhotoDto?>(null) }
    val context = LocalContext.current

    LaunchedEffect(Lifecycle.State.CREATED) {
        lifecycle.repeatOnLifecycle(Lifecycle.State.CREATED){
            galleryScreenViewModel.fetchPhotos()
        }
    }

    if (bottomSheetVisible) {
        FilterModal(
            sheetState = modalState,
            filterSettings = viewState.value.filterSettings,
            onSetFilterSettings = { filterSettings ->
                galleryScreenViewModel.setFilterSettings(filterSettings)
            },
            onDismiss = {
                bottomSheetVisible = false
            },
            onSetFilter = {
                galleryScreenViewModel.fetchPhotos(1)
            }
        )
    }

    if (photoDialogVisible && selectedPhoto != null){
        PhotoDialog(
            onDismiss = {
                photoDialogVisible = false
            },
            photo = selectedPhoto!!,
            downloadImage = { photoUrl ->
                if (checkWritePermission(context)) {
                    galleryScreenViewModel.downloadPhoto(photoUrl)
                }
            },
            openWebPage = { photoWebPageUrl ->
                navigateToPhotoWebPageScreen(photoWebPageUrl)
            }
        )
    }

    Box(
        modifier = modifier.fillMaxSize()
    ){
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 10.dp, end = 10.dp,top = 20.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ){
                SearchBar(
                    modifier = Modifier
                        .weight(1f),
                    onSearch = { query ->
                        galleryScreenViewModel.searchPhotos(query)
                        scope.launch{
                            listState.scrollToItem(0)
                        }
                    }
                )
                Icon(
                    painter = painterResource(R.drawable.ic_filter),
                    contentDescription = null,
                    modifier = Modifier
                        .size(42.dp)
                        .padding(start = 5.dp)
                        .clickable(
                            interactionSource = null,
                            indication = null
                        ){
                            bottomSheetVisible = true
                        }
                )
            }
            LazyColumn(
                state = listState,
                modifier = modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(5.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                viewState.value.photos?.let { photos ->
                    itemsIndexed(photos) { index,photo ->
                        ImageBox(
                            photo = photo,
                            onClick = { photo ->
                                photoDialogVisible = true
                                selectedPhoto = photo
                            }
                        )
                        if (index == photos.lastIndex){
                            galleryScreenViewModel.fetchPhotos()
                        }
                    }
                }

                if (true){
                    item {
                        CircularProgressIndicator(
                            modifier = modifier
                                .size(40.dp)
                        )
                    }
                }
            }
        }

        visibleListItems.lastOrNull()?.index?.let { index ->
            AnimatedVisibility(
                modifier =  Modifier
                    .align(Alignment.BottomEnd)
                    .padding(end = 10.dp, bottom = 20.dp)
                    .clip(CircleShape),
                visible = index > 5
            ) {
                FloatingActionButton(
                    shape = CircleShape,
                    onClick = {
                        scope.launch{
                            listState.animateScrollToItem(
                                index = 0,

                            )
                        }
                    }
                ) {
                    Icon(
                        modifier = Modifier
                            .size(24.dp),
                        painter = painterResource(R.drawable.ic_long_up_arrow),
                        contentDescription = null
                    )
                }
            }
        }
    }
}