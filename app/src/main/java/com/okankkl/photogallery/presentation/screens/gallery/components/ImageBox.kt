package com.okankkl.photogallery.presentation.screens.gallery.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.okankkl.photogallery.R
import com.okankkl.photogallery.data.remote.dto.PhotoDto
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import com.okankkl.photogallery.utils.enums.Orientation

@Composable
fun ImageBox(
    photo: PhotoDto,
    onClick: (PhotoDto) -> Unit = {}
) {
    var isImgLoaded by remember { mutableStateOf(false) }

    val orientation = if ((photo.imageHeight ?: 0) > (photo.imageWidth
            ?: 0)
    ) Orientation.Vertical else Orientation.Horizontal

    Box(
        modifier = Modifier
            .height(if (orientation == Orientation.Vertical) 500.dp else 250.dp)
            .fillMaxWidth()
    ) {
        AnimatedVisibility(
            visible = !isImgLoaded,
            enter = fadeIn(animationSpec = tween(1000)),
            exit = fadeOut(animationSpec = tween(1000))
        ) {

            Box(
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .background(
                        color = MaterialTheme.colorScheme.tertiary,
                        shape = RoundedCornerShape(8.dp)
                    )
                    .fillMaxSize()
            )
        }

        AsyncImage(
            model = photo.webFormatURL,
            contentDescription = null,
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .fillMaxSize()
                .clickable {
                    onClick(photo)
                },
            onSuccess = {
                isImgLoaded = true
            }
        )
    }
}