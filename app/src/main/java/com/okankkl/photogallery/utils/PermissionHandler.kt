package com.okankkl.photogallery.utils

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

object PermissionHandler {

    fun checkWritePermission(context: Context): Boolean {
        var isGranted = false
        Build.VERSION.SDK_INT.let { version ->
            if (version >= Build.VERSION_CODES.TIRAMISU) {
                isGranted = ContextCompat.checkSelfPermission(
                    context,
                    Manifest.permission.READ_MEDIA_IMAGES
                ) == PackageManager.PERMISSION_GRANTED
                if (!isGranted) {
                    requestPermission(context, Manifest.permission.READ_MEDIA_IMAGES, 1)
                }
            } else {
                isGranted = ContextCompat.checkSelfPermission(
                    context,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
                ) == PackageManager.PERMISSION_GRANTED
                if (!isGranted) {
                    requestPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE, 2)
                }
            }
        }
        return isGranted
    }


    fun checkNotificationPermission(context: Context) {
        Build.VERSION.SDK_INT.let { version ->
            if (version >= Build.VERSION_CODES.TIRAMISU) {
                val isGranted = ContextCompat.checkSelfPermission(
                    context,
                    Manifest.permission.POST_NOTIFICATIONS
                ) == PackageManager.PERMISSION_GRANTED
                if (!isGranted) {
                    requestPermission(context, Manifest.permission.POST_NOTIFICATIONS, 3)
                }
            }
        }
    }


    private fun requestPermission(context: Context, permission: String, code: Int) {
        ActivityCompat.requestPermissions(
            context as Activity,
            arrayOf(permission),
            1
        )
    }
}