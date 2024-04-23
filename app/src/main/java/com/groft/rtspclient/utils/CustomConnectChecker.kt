package com.groft.rtspclient.utils

import com.pedro.common.ConnectChecker

class CustomConnectChecker : ConnectChecker {
    override fun onAuthError() {
    }

    override fun onAuthSuccess() {
        println("AUTH SUCCESS!!")
    }

    override fun onConnectionFailed(reason: String) {
        println("FAILED CONNECTION: $reason")
    }

    override fun onConnectionStarted(url: String) {
        println("STARTED SUCCESS!!")
    }

    override fun onConnectionSuccess() {
        println("CONNECTION SUCCESS!!")
    }

    override fun onDisconnect() {
    }

    override fun onNewBitrate(bitrate: Long) {
    }


}