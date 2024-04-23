package com.groft.rtspclient.data.models

import com.squareup.moshi.Json

data class Response(
    @Json(name = "stream_url")
    val streamID: String
)
