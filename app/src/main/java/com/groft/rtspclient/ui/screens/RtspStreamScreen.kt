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
fun RtspStreamScreen(settings: StreamSettings, navController: NavController) {
    val context = LocalContext.current
    val openGlView = remember { OpenGlView(context) }
    val client = prepareStream(
        settings = settings,
        openGlView = openGlView
    )
    RtspStreamView(
        openGlView = openGlView,
        url = settings.url,
        client = client,
        navController = navController
    )
}