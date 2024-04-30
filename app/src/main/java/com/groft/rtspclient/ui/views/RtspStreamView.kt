package com.groft.rtspclient.ui.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavController
import com.groft.rtspclient.R
import com.pedro.library.rtsp.RtspCamera1
import com.pedro.library.rtsp.RtspCamera2
import com.pedro.library.view.OpenGlView

@Composable
fun RtspStreamView(
    client: RtspCamera2,
    openGlView: OpenGlView,
    url: String,
    navController: NavController
) {
    val onStopStream = {
        client.stopStream()
        openGlView.stop()
        navController.navigate("streamSettingsScreen")
    }
    LaunchedEffect(Unit) {
        client.startStream("rtsp://192.168.0.102:8554/stream/${url}")
    }
    DisposableEffect(Unit){
        onDispose {
            client.stopStream()
            openGlView.stop()
        }
    }
    AndroidView(
        factory = { openGlView },
        modifier = Modifier.fillMaxSize()
    ) {

    }
    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 20.dp),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Button(
            onClick = { onStopStream() },
        ) {
            IconButton(onClick = { onStopStream() }) {
                Icon(painter = painterResource(id = R.drawable.stop_button), contentDescription = "stop stream")
            }
        }
    }

}