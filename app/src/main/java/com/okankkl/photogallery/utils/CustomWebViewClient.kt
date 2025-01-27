package com.okankkl.photogallery.utils

import android.graphics.Bitmap
import android.webkit.WebView
import android.webkit.WebViewClient

class CustomWebViewClient(
    private val onPageStarted: (String) -> Unit
): WebViewClient(){
    override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
        super.onPageStarted(view, url, favicon)

        url?.let { onPageStarted(it) }
    }
}