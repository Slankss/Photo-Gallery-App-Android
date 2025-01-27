package com.okankkl.photogallery.presentation.screens.gallery.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.okankkl.photogallery.utils.enums.FilterType
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.painterResource
import com.okankkl.photogallery.R
import com.okankkl.photogallery.utils.enums.Color

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun FilterColumn(
    modifier: Modifier = Modifier,
    header: String,
    selectedContent: FilterType? = null,
    contents: List<FilterType>,
    onSelected: (FilterType?) -> Unit
) {
    var contentVisibility by remember { mutableStateOf(false) }
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ){
        Card{
            Box(
                modifier = Modifier
                    .clickable{
                        contentVisibility = !contentVisibility
                    }
            ){
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ){
                    Text(
                        text = header,
                        style = MaterialTheme.typography.labelMedium.copy(fontSize = 15.sp)
                    )
                    Icon(
                        painter = painterResource(
                            id = if (contentVisibility) R.drawable.ic_up_arrow else R.drawable.ic_down_arrow
                        ),
                        contentDescription = null
                    )
                }
            }

            AnimatedVisibility(contentVisibility) {
                FlowRow(
                    modifier = Modifier.padding(10.dp),
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    contents.forEach{ content ->
                        val isSelected = selectedContent == content
                        FilterBox(
                            modifier = Modifier
                                .clickable(
                                    indication = null,
                                    interactionSource = null
                                ){
                                    onSelected(if(isSelected) null else content)
                                },
                            colorCode = if (content is Color) content.colorCode else null,
                            text = content.label,
                            isSelected = isSelected
                        )
                    }
                }
            }

        }



    }
}