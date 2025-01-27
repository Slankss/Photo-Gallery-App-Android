package com.okankkl.photogallery.presentation.screens.gallery.components

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.okankkl.photogallery.presentation.theme.InterFontFamily
import com.okankkl.photogallery.R

@Composable
fun FilterBox(
    modifier: Modifier = Modifier,
    text: String,
    isSelected: Boolean = false,
    colorCode: Long? = null
) {
    Box(
        modifier = modifier
            .clip(CircleShape)
            .background(
                color = if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.tertiary,
            )
            .defaultMinSize(minWidth = 80.dp)
            .animateContentSize()
    ){
        Row(
            modifier = modifier
                .padding(vertical = 5.dp, horizontal = 10.dp)
                .align(Alignment.Center),
            verticalAlignment = Alignment.CenterVertically
        ){
            if (colorCode != null){
                Box(
                    modifier = modifier
                        .size(20.dp)
                        .clip(CircleShape)
                        .background(
                            color = Color(colorCode),
                            shape = CircleShape
                        )
                )
            }
            Text(
                modifier = modifier
                    .padding(if (colorCode != null) 5.dp else 0.dp),
                text = text,
                color = MaterialTheme.colorScheme.onTertiary,
                fontFamily = InterFontFamily,
                fontWeight = FontWeight.Light,
                fontSize = 12.sp
            )
            if (isSelected){
                Icon(
                    modifier = modifier
                        .padding(start = 10.dp)
                        .size(24.dp),
                    painter = painterResource(R.drawable.ic_close),
                    contentDescription = null,
                    tint = Color.Black
                )
            }
        }

    }
}