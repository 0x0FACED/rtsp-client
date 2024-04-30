package com.groft.rtspclient

import StreamSettingsScreen
import android.Manifest.*
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.groft.rtspclient.data.models.StreamSettings
import com.groft.rtspclient.ui.screens.DualRtspStreamScreen
import com.groft.rtspclient.ui.screens.RtspStreamScreen
import com.groft.rtspclient.ui.theme.RTSPClientTheme
import com.groft.rtspclient.ui.viewmodels.ApiViewModel
import com.groft.rtspclient.utils.CustomConnectChecker
import com.pedro.common.VideoCodec
import com.pedro.library.rtsp.RtspCamera2
import com.pedro.library.view.OpenGlView


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RTSPClientTheme {
                if (ContextCompat.checkSelfPermission(
                        this,
                        permission.CAMERA
                    ) != PackageManager.PERMISSION_GRANTED
                ) {
                    ActivityCompat.requestPermissions(this, arrayOf(permission.CAMERA), 1)
                }
                if (ContextCompat.checkSelfPermission(
                        this,
                        permission.RECORD_AUDIO
                    ) != PackageManager.PERMISSION_GRANTED
                ) {
                    ActivityCompat.requestPermissions(this, arrayOf(permission.RECORD_AUDIO), 1)
                }
                val navController = rememberNavController()
                val viewModel = remember { ApiViewModel() }
                NavHost(
                    navController = navController,
                    startDestination = "streamSettingsScreen",
                ) {
                    composable("streamSettingsScreen") {
                        // создаем экземпляр ViewModel
                        // отображаем экран настроек стрима
                        StreamSettingsScreen(viewModel, navController)
                    }
                    composable(
                        route = "rtspStreamScreen/{settingsString}",
                        arguments = listOf(navArgument("settingsString") { type = NavType.StringType })
                    ) { navBackStackEntry ->
                        val settingsString = navBackStackEntry.arguments?.getString("settingsString") ?: ""
                        val settings = StreamSettings.fromUrlString(settingsString)
                        RtspStreamScreen(settings, navController)
                    }
                    composable(
                        route = "dualRtspStreamScreen/{settingsString}",
                        arguments = listOf(navArgument("settingsString") { type = NavType.StringType })
                    ) { navBackStackEntry ->
                        val settingsString = navBackStackEntry.arguments?.getString("settingsString") ?: ""
                        val settings = StreamSettings.fromUrlString(settingsString)
                        DualRtspStreamScreen(settings, navController)
                    }

                }
                //RtspStreamView()
            }

        }
    }

    @Deprecated("Deprecated in Java")
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            1 -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    return
                } else {
                    Toast.makeText(
                        applicationContext,
                        "${permissions[0]} разрешение не предоставлено",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                return
            }
        }
    }
}



