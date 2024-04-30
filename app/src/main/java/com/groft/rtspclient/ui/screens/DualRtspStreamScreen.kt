package com.groft.rtspclient.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.groft.rtspclient.data.models.StreamSettings
import com.groft.rtspclient.ui.views.RtspStreamView
import com.groft.rtspclient.utils.prepareStream
import com.pedro.library.view.OpenGlView

@Composable
fun DualRtspStreamScreen(settings: StreamSettings, navController: NavController) {
    val context = LocalContext.current
    val openGlView1 = remember { OpenGlView(context) }
    val openGlView2 = remember { OpenGlView(context) }
    val client1 = prepareStream(
        settings = settings,
        openGlView = openGlView1,
    )
    val client2 = prepareStream(
        settings = settings,
        openGlView = openGlView2,
    )
    RtspStreamView(
        openGlView = openGlView1,
        url = settings.url,
        client = client1,
        navController = navController
    )
    RtspStreamView(
        openGlView = openGlView2,
        url = "test_second",
        client = client2,
        navController = navController
    )
}