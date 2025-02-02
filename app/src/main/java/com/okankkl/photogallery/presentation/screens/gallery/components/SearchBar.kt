package com.okankkl.photogallery.presentation.screens.gallery.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import com.okankkl.photogallery.R

@Composable
fun SearchBar(
    modifier: Modifier = Modifier,
    onSearch: (String) -> Unit,
){
    val keyboardController = LocalSoftwareKeyboardController.current
    var searchText by remember { mutableStateOf("") }
    
    Row(
      modifier = modifier
          .height(50.dp)
          .clip(CircleShape)
          .background(Color.Gray.copy(alpha = 0.2f)),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ){
        TextField(
            modifier = Modifier.weight(1f),
            value = searchText,
            onValueChange = {
                searchText = it
            },
            placeholder = {
                Text("Searched text")
            },
            colors = TextFieldDefaults.colors().copy(
                unfocusedContainerColor = Color.Transparent,
                focusedContainerColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent
            ),
            keyboardActions = KeyboardActions(
                onSearch = {
                    if (searchText.isNotBlank()){
                        onSearch(searchText)
                        keyboardController?.hide()
                    }
                }
            ),
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Search
            )

        )
        IconButton(
            onClick = {
                if (searchText.isNotBlank()){
                    onSearch(searchText)
                    keyboardController?.hide()
                }
            },
            colors = IconButtonDefaults.iconButtonColors().copy(
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.onPrimary,
            ),
            modifier = Modifier.size(50.dp)
        ) {
            Icon(
               painter = painterResource(R.drawable.ic_search),
               contentDescription = null
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SearchBarPreview(){
    SearchBar {

    }
}