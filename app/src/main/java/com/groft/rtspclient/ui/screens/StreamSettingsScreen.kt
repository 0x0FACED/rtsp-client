import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text

import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily

import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.groft.rtspclient.data.models.StreamSettings
import com.groft.rtspclient.data.models.StreamSettings.Companion.toUrlString
import com.groft.rtspclient.ui.viewmodels.ApiViewModel
import com.groft.rtspclient.ui.views.SettingsItem
import com.groft.rtspclient.utils.getCodec
import com.pedro.common.VideoCodec
import kotlinx.coroutines.launch

@Composable
fun StreamSettingsScreen(
    viewModel: ApiViewModel,
    navController: NavController
) {
    val width = remember { mutableStateOf("1280") }
    val height = remember { mutableStateOf("720") }
    val codec = remember { mutableStateOf("H264") }
    val bitrate = remember { mutableStateOf("1000") }
    val fps = remember { mutableStateOf("24") }

    var url by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxSize()
            .background(Color.DarkGray)
            .padding(12.dp)
    ) {
        Text(
            "Stream Settings",
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(8.dp),
            fontFamily = FontFamily.SansSerif,
            fontSize = 36.sp,
            color = Color.White,
        )

        Spacer(modifier = Modifier.height(12.dp))

        SettingsItem(
            param = width,
            settings = listOf("426","640","854","1280","1920"),
            paramName = "Width",
        )

        Spacer(modifier = Modifier.height(12.dp))

        SettingsItem(
            param = height,
            settings = listOf("240","360","480","720","1080"),
            paramName = "Height",
        )

        Spacer(modifier = Modifier.height(12.dp))

        SettingsItem(
            param = fps,
            settings = listOf("15","30","60","120"),
            paramName = "FPS",
        )

        Spacer(modifier = Modifier.height(12.dp))

        SettingsItem(
            param = bitrate,
            settings = listOf("1000","2000","5000","10000"),
            paramName = "Bitrate",
        )

        Spacer(modifier = Modifier.height(12.dp))

        SettingsItem(
            param = codec,
            settings = listOf("H264","H265","AV1"),
            paramName = "Video Codec",
        )

        Spacer(modifier = Modifier.height(12.dp))

        Button(
            onClick = {
                viewModel.viewModelScope.launch {
                    url = viewModel.sendStreamRequest().toString()
                    println("URL: $url")
                    val settings = StreamSettings(
                        url = url,
                        width = width.value,
                        height = height.value,
                        bitrate = bitrate.value,
                        fps = fps.value,
                        codec = getCodec(codec.value)
                    )
                    navController.navigate("rtspStreamScreen/${settings.toUrlString()}")
                }

            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Stream")
        }
    }
}
