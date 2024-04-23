package com.groft.rtspclient.data.models

import com.pedro.common.VideoCodec

data class StreamSettings(
    val url: String,
    val width: String,
    val height: String,
    val bitrate: String,
    val fps: String,
    val codec: VideoCodec,
) {
    companion object {
        fun fromUrlString(urlString: String): StreamSettings {
            val parts = urlString.split("&").associate {
                val (key, value) = it.split("=")
                key to value
            }
            return StreamSettings(
                url = parts["url"] ?: "",
                width = parts["width"] ?: "",
                height = parts["height"] ?: "",
                bitrate = parts["bitrate"] ?: "",
                fps = parts["fps"] ?: "",
                codec = VideoCodec.valueOf(parts["codec"] ?: "H264")
            )
        }

        fun StreamSettings.toUrlString(): String {
            return "url=${url}&width=${width}&height=${height}&bitrate=${bitrate}&fps=${fps}&codec=${codec.name}"
        }
    }
}
