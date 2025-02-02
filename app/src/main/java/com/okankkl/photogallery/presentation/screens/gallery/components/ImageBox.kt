package com.okankkl.photogallery.presentation.screens.gallery.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.okankkl.photogallery.data.remote.dto.PhotoDto
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import com.okankkl.photogallery.utils.enums.Orientation

@Composable
fun ImageBox(
    photo: PhotoDto,
    onClick: () -> Unit = {}
) {
    var isImgLoaded by remember { mutableStateOf(false) }

    val orientation = if ((photo.imageHeight ?: 0) > (photo.imageWidth
            ?: 0)
    ) Orientation.Vertical else Orientation.Horizontal

    Box(
        modifier = Modifier
            .height(if (orientation == Orientation.Vertical) 400.dp else 300.dp)
            .fillMaxWidth(0.8f)
            .padding(top = 10.dp)
            .clip(RoundedCornerShape(8))
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
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxSize()
                .clickable {
                    onClick()
                },
            onSuccess = {
                isImgLoaded = true
            }
        )
    }
}