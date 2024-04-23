package com.groft.rtspclient.utils

import android.content.Context
import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.ViewModel
import com.groft.rtspclient.data.models.StreamSettings
import com.pedro.common.VideoCodec
import com.pedro.library.rtsp.RtspCamera2
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
    openGlView: OpenGlView
): RtspCamera2 {
    val customConnectChecker = CustomConnectChecker()
    val client = RtspCamera2(openGlView, customConnectChecker)
    if (client.prepareAudio() && client.prepareVideo(
            settings.width.toInt(),
            settings.height.toInt(),
            settings.fps.toInt() * settings.bitrate.toInt(),
        )
    ) {
        client.setVideoCodec(settings.codec)
    } else {
        /**This device cant init encoders, this could be for 2 reasons: The encoder selected doesnt support any configuration setted or your device hasnt a H264 or AAC encoder (in this case you can see log error valid encoder not found)*/
    }

    return client
}