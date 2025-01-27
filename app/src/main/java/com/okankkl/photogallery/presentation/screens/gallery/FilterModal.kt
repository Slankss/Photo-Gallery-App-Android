package com.okankkl.photogallery.presentation.screens.gallery

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.okankkl.photogallery.presentation.screens.gallery.components.FilterColumn
import com.okankkl.photogallery.utils.enums.Category
import com.okankkl.photogallery.utils.enums.Color
import com.okankkl.photogallery.utils.enums.Order
import com.okankkl.photogallery.utils.enums.Orientation
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun FilterModal(
    modifier: Modifier = Modifier,
    sheetState: SheetState,
    onDismiss: () -> Unit,
    filterSettings: FilterSettings,
    onSetFilterSettings: (FilterSettings) -> Unit,
    onSetFilter: () -> Unit
) {
    val scope = rememberCoroutineScope()

    ModalBottomSheet(
        sheetState = sheetState,
        onDismissRequest = onDismiss
    ) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(horizontal = 10.dp, vertical = 20.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ){
            LazyColumn(
                modifier = Modifier.weight(1f)
            ){
                item {
                    Row(
                        modifier = modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ){
                        Text(
                            text = "Filter Settings",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = modifier
                        )
                        Button(
                            modifier = modifier,
                            colors = ButtonDefaults.buttonColors(
                                containerColor = MaterialTheme.colorScheme.tertiary,
                                contentColor = MaterialTheme.colorScheme.onTertiary
                            ),
                            onClick = {
                                onSetFilterSettings(
                                    filterSettings.copy(
                                        orientation = null,
                                        order = null,
                                        color = null,
                                        category = null
                                    )
                                )
                            }
                        ){
                            Text(
                                text = "Reset",
                                style = MaterialTheme.typography.bodyMedium
                            )
                        }
                    }

                }

                item {
                    FilterColumn(
                        modifier = modifier.padding(top = 20.dp),
                        header = "Orientation",
                        selectedContent = filterSettings.orientation,
                        contents = Orientation.entries,
                        onSelected = { filterType ->
                            onSetFilterSettings(
                                filterSettings.copy(
                                    orientation = filterType
                                )
                            )
                        }
                    )
                }

                item {
                    FilterColumn(
                        modifier = modifier.padding(top = 20.dp),
                        header = "Order",
                        selectedContent = filterSettings.order,
                        contents = Order.entries,
                        onSelected = { filterType ->
                            onSetFilterSettings(
                                filterSettings.copy(
                                    order = filterType
                                )
                            )
                        }
                    )
                }

                item {
                    FilterColumn(
                        modifier = modifier.padding(top = 20.dp),
                        header = "Category",
                        selectedContent = filterSettings.category,
                        contents = Category.entries,
                        onSelected = { filterType ->
                            onSetFilterSettings(
                                filterSettings.copy(
                                    category = filterType
                                )
                            )
                        }
                    )
                }

                item {
                    FilterColumn(
                        modifier = modifier.padding(top = 20.dp, bottom = 20.dp),
                        header = "Color",
                        selectedContent = filterSettings.color,
                        contents = Color.entries,
                        onSelected = { filterType ->
                            onSetFilterSettings(
                                filterSettings.copy(
                                    color = filterType
                                )
                            )
                        }
                    )
                }

            }

            Button(
                colors = ButtonDefaults.buttonColors().copy(
                    containerColor = MaterialTheme.colorScheme.secondary,
                    contentColor = MaterialTheme.colorScheme.onSecondary
                ),
                onClick = {
                    scope.launch { sheetState.hide() }.invokeOnCompletion {
                        if (!sheetState.isVisible) {
                            onDismiss()
                            onSetFilter()
                        }
                    }
                },
                modifier = modifier
                    .fillMaxWidth()
            ) {
                Text(
                    text = "Show Results",
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }


    }
}