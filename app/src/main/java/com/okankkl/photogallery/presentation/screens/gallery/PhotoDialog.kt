package com.okankkl.photogallery.presentation.screens.gallery

import android.content.Intent
import androidx.activity.compose.LocalActivity
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.okankkl.photogallery.R
import com.okankkl.photogallery.data.remote.dto.PhotoDto

@Composable
fun PhotoDialog(
    photo: PhotoDto,
    modifier: Modifier = Modifier,
    onDismiss: () -> Unit,
    downloadImage: (String) -> Unit,
    openWebPage: (String) -> Unit
) {
    val activity = LocalActivity.current

    Dialog(
        onDismissRequest = onDismiss
    ) {
        Card(
            modifier = modifier
                .fillMaxWidth()
                .padding(16.dp),
            shape = RoundedCornerShape(16.dp)
        ) {
            Row(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                photo.pageURL?.let { pageUrl ->
                    Column(
                        modifier = modifier,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {

                        IconButton(
                            interactionSource = null,
                            onClick = {
                                openWebPage(pageUrl)
                            },
                        ) {
                            Icon(
                                modifier = modifier.size(36.dp),
                                painter = painterResource(R.drawable.ic_web),
                                contentDescription = null
                            )
                        }
                        Text(
                            modifier = modifier
                                .padding(top = 5.dp),
                            text = "Open Web Page",
                            fontSize = 10.sp
                        )
                    }

                    Column(
                        modifier = modifier,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        IconButton(
                            interactionSource = null,
                            onClick = {
                                activity?.let { activity ->
                                    val sendIntent = Intent()
                                    sendIntent.apply {
                                        action = Intent.ACTION_SEND
                                        putExtra(Intent.EXTRA_TEXT, pageUrl)
                                        type = "text/plain"
                                    }

                                    activity.startActivity(sendIntent)
                                }
                            },
                        ) {
                            Icon(
                                modifier = modifier.size(36.dp),
                                painter = painterResource(R.drawable.ic_share),
                                contentDescription = null
                            )
                        }
                        Text(
                            modifier = modifier
                                .padding(top = 5.dp),
                            text = "Share",
                            fontSize = 10.sp
                        )
                    }
                }

                photo.webFormatURL?.let { webFormatUrl ->
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        IconButton(
                            onClick = {
                                downloadImage(photo.webFormatURL)
                            },
                        ) {
                            Icon(
                                modifier = modifier.size(36.dp),
                                painter = painterResource(R.drawable.ic_download),
                                contentDescription = null
                            )
                        }
                        Text(
                            modifier = modifier
                                .padding(top = 5.dp),
                            text = "Download",
                            fontSize = 10.sp
                        )
                    }
                }
            }
        }
    }
}