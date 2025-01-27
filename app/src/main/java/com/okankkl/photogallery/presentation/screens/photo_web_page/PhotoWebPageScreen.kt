package com.okankkl.photogallery.presentation.screens.photo_web_page

import android.content.ClipData
import android.content.Context
import android.view.ViewGroup
import android.webkit.WebView
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.sp
import com.okankkl.photogallery.utils.UrlUtils
import com.okankkl.photogallery.R
import android.content.ClipboardManager
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.ui.draw.clip
import com.okankkl.photogallery.utils.CustomWebViewClient

@Composable
fun PhotoWebPageScreen(
    modifier: Modifier = Modifier,
    photoWebPageUrl: String?,
    prevBack: () -> Unit = {}
) {
    val context = LocalContext.current
    var webView by remember { mutableStateOf<WebView?>(null)}
    val brushColors = listOf(
        MaterialTheme.colorScheme.primary,
        MaterialTheme.colorScheme.secondary
    )
    var currentUrl by remember { mutableStateOf(photoWebPageUrl ?: "") }
    val customWebClient = CustomWebViewClient(
        onPageStarted = {
            currentUrl = it
        }
    )

    BackHandler {
        if (webView?.canGoBack() == true){
            webView?.goBack()
        } else {
            prevBack()
        }
    }

    Column(
        modifier = modifier
            .fillMaxSize()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    brush = Brush.horizontalGradient(
                        colors = brushColors
                    )
                ),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .padding(top = 5.dp, bottom = 5.dp, start = 10.dp, end = 10.dp)
                    .clip(CircleShape)
                    .clickable{
                        prevBack()
                    }
            ){
                Icon(
                    imageVector = Icons.Default.Close,
                    contentDescription = null,
                    tint = Color.Gray,
                    modifier = Modifier
                        .padding(2.dp)
                        .size(32.dp)
                )
            }

            Text(
                modifier = Modifier.weight(1f),
                text = UrlUtils.shortUrl(currentUrl),
                style = MaterialTheme.typography.displayMedium.copy(
                    fontSize = 12.sp
                ),
                fontStyle = FontStyle.Italic
            )

            Box(
                modifier = Modifier
                    .padding(top = 5.dp, bottom = 5.dp, start = 10.dp, end = 10.dp)
                    .clip(CircleShape)
                    .clickable{
                        val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
                        val clip = ClipData.newPlainText("label", currentUrl)
                        clipboard.setPrimaryClip(clip)
                    }
            ){
                Icon(
                    painter = painterResource(R.drawable.ic_copy),
                    contentDescription = null,
                    tint = Color.Gray,
                    modifier = Modifier
                        .size(42.dp)
                        .padding(8.dp)
                )
            }

        }
        photoWebPageUrl?.let { photoWebPageUrl ->
            AndroidView(
                modifier = Modifier.fillMaxHeight(),
                factory = {
                WebView(it).apply {
                    webViewClient = customWebClient
                    layoutParams = ViewGroup.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT
                    )
                    settings.supportMultipleWindows()
                    loadUrl(photoWebPageUrl)
                    webView = this
                }
            }, update = {
                webView = it
                webView?.webViewClient = customWebClient
            }
            )
        }
    }
}