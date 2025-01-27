package com.okankkl.photogallery.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.okankkl.photogallery.data.remote.dto.PhotoDto
import com.okankkl.photogallery.presentation.theme.PhotoGalleryTheme
import com.okankkl.photogallery.presentation.screens.gallery.GalleryScreen
import com.okankkl.photogallery.presentation.screens.photo_web_page.PhotoWebPageScreen
import com.okankkl.photogallery.utils.PermissionHandler
import com.okankkl.photogallery.utils.enums.Screen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        PermissionHandler.checkNotificationPermission(this)

        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()

            PhotoGalleryTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Surface(
                        color = MaterialTheme.colorScheme.background
                    ){
                        NavHost(
                            navController = navController,
                            startDestination = Screen.Gallery.route
                        ){
                            composable(Screen.Gallery.route){
                                GalleryScreen(
                                    modifier = Modifier.padding(innerPadding),
                                    navigateToPhotoWebPageScreen = { photoWebPageUrl ->
                                        navController.apply {
                                            currentBackStackEntry?.savedStateHandle?.set("photo_web_page_url",photoWebPageUrl)
                                            navigate(Screen.PhotoDetail.route)
                                        }
                                    }
                                )
                            }
                            composable(Screen.PhotoDetail.route){
                                val photoWebPageUrl = navController.previousBackStackEntry?.savedStateHandle?.get<String>("photo_web_page_url")
                                PhotoWebPageScreen(
                                    modifier = Modifier.padding(innerPadding),
                                    photoWebPageUrl = photoWebPageUrl,
                                    prevBack = {
                                        navController.popBackStack()
                                    }
                                )
                            }
                        }
                    }

                }
            }
        }
    }
}