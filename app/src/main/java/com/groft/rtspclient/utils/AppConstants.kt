package com.groft.rtspclient.utils

import android.content.Context
import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.ViewModel
import com.groft.rtspclient.data.models.StreamSettings
import com.pedro.common.AudioCodec
import com.pedro.common.VideoCodec
import com.pedro.encoder.input.video.CameraHelper
import com.pedro.library.base.StreamBase
import com.pedro.library.rtsp.RtspCamera1
import com.pedro.library.rtsp.RtspCamera2
import com.pedro.library.util.streamclient.StreamBaseClient
import com.pedro.library.view.OpenGlView

fun getCodec(codec: String): VideoCodec {
    return when (codec) {
        "H264" -> VideoCodec.H264
        "H265" -> VideoCodec.H265
        else -> VideoCodec.AV1
    }
}

fun prepareStream(
    settings: StreamSettings,
    openGlView: OpenGlView,
): RtspCamera2 {
    val customConnectChecker = CustomConnectChecker()
    val client = RtspCamera2(openGlView, customConnectChecker)
    client.setVideoCodec(settings.codec)
    client.enableVideoStabilization()
    client.enableOpticalVideoStabilization()
    client.enableAutoFocus()
    client.prepareVideo(
        settings.width.toInt(),
        settings.height.toInt(),
        settings.bitrate.toInt() * settings.fps.toInt(),
    )
    client.streamClient.setOnlyVideo(true)
    return client
}