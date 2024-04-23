package com.groft.rtspclient.ui.viewmodels

import androidx.lifecycle.ViewModel
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import java.io.IOException
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class ApiViewModel() : ViewModel() {
    private val client = OkHttpClient()
    private var responseData: String = ""

    suspend fun sendStreamRequest(): String? {
        return suspendCoroutine { continuation ->
            val request = Request.Builder()
                .url("http://192.168.0.102:8080/stream_request") // Замените на ваш URL
                .build()

            client.newCall(request).enqueue(object : Callback {
                override fun onResponse(call: Call, response: Response) {
                    if (response.isSuccessful) {
                        val responseBody = response.peekBody(Long.MAX_VALUE).string()
                        val moshi = Moshi.Builder()
                            .add(KotlinJsonAdapterFactory())
                            .build()

                        val adapter = moshi.adapter(com.groft.rtspclient.data.models.Response::class.java)
                        val responseGson = adapter.fromJson(responseBody)
                        val url = responseGson?.streamID
                        continuation.resume(url)
                    } else {
                        continuation.resume(null)
                    }
                }

                override fun onFailure(call: Call, e: IOException) {
                    continuation.resume(null)
                }
            })
        }
    }



    fun setResponseData(data: String) {
        responseData = data
    }

    fun getResponseData(): String {
        return responseData
    }
}