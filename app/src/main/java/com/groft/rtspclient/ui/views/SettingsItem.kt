package com.groft.rtspclient.ui.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonColors
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.sp

@Composable
fun SettingsItem(
    modifier: Modifier = Modifier,
    param: MutableState<String>,
    settings: List<String>,
    paramName: String,
) {
    var expanded by remember { mutableStateOf(false) }
    var choose by remember {
        mutableStateOf("<Not Chosen>")
    }
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(Color.DarkGray),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            modifier = Modifier.weight(1f),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = paramName,
                fontFamily = FontFamily.SansSerif,
                fontSize = 24.sp,
                color = Color.White,
            )
        }
        Text(
            text = "Chosen: $choose",
            fontFamily = FontFamily.SansSerif,
            fontSize = 18.sp,
            color = Color.White,
        )
        IconButton(
            onClick = { expanded = true },
            colors = IconButtonDefaults.iconButtonColors(
                contentColor = Color.White,
            )
        ) {
            Icon(Icons.Default.MoreVert, contentDescription = "Expand")
        }
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier.fillMaxWidth()
        ) {
            Divider()
            settings.forEach { option ->
                DropdownMenuItem(
                    onClick = {
                        param.value = option
                        choose = option
                        expanded = false
                    },
                    text = {
                        Text(
                            text = option,
                            fontFamily = FontFamily.SansSerif,
                            fontSize = 24.sp,
                            color = Color.White,
                        )
                    }
                )
                Divider()
            }
        }
    }
}