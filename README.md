# RTSP Client

This is a simple Android client written in Kotlin that allows real-time streaming of RTSP packets over TCP to a server.

Currently, there is only a stream settings selection screen, which includes:
1. Width selection
2. Height selection
3. FPS selection
4. Bitrate selection
5. Video codec selection

Upon pressing the `Stream` button, the client sends a request to the server, receives a `stream_url`, and starts streaming at that address.

**Used:**
1. Jetpack Compose for layout.
2. RootEncoder library for RTSP handling.
3. okHttp for HTTP requests.

Tested on Realme 9 PRO

Work in progress.
