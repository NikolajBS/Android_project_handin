package com.example.android_project.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavController
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.android_project.data.AppSettings

@Composable
fun SettingsScreen(navigation: NavController, appSettings: MutableState<AppSettings>, onSettingsChanged: (AppSettings) -> Unit) {
    var notificationsButtonText by remember { mutableStateOf(getNotificationsButtonText(appSettings)) }
    var isDarkTheme by remember { mutableStateOf(appSettings.value.isDarkTheme) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Settings Screen", fontSize = 24.sp, fontWeight = FontWeight.Bold)

        Spacer(modifier = Modifier.height(16.dp))

        SettingsButton(icon = Icons.Outlined.Notifications, label = notificationsButtonText) {
            appSettings.value.notificationEnabled = !appSettings.value.notificationEnabled
            notificationsButtonText = getNotificationsButtonText(appSettings)
            onSettingsChanged(appSettings.value)
        }

        SettingsButton(icon = Icons.Outlined.Lock, label = "Privacy & Security") {
            // Handle Privacy & Security button click
        }

        SettingsButton(icon = Icons.Outlined.Info, label = "Help & Support") {
            // Handle Help & Support button click
        }

        SettingsButton(icon = Icons.Outlined.Info, label = getThemeButtonText(isDarkTheme)) {
            appSettings.value.isDarkTheme = !appSettings.value.isDarkTheme
            isDarkTheme = appSettings.value.isDarkTheme
            onSettingsChanged(appSettings.value)
        }
    }
}

@Composable
fun SettingsButton(icon: ImageVector, label: String, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable { onClick.invoke() },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon,
            contentDescription = label,
            modifier = Modifier
                .size(48.dp)
                .clip(CircleShape)
                .background(MaterialTheme.colorScheme.inversePrimary)
                .padding(4.dp),
            tint = Color.Black
        )

        Spacer(modifier = Modifier.width(16.dp))

        Text(text = label, fontSize = 16.sp, fontWeight = FontWeight.Medium)
    }
}

fun getNotificationsButtonText(appSettings: MutableState<AppSettings>): String {
    return if (appSettings.value.notificationEnabled) {
        "Notifications On"
    } else {
        "Notifications Off"
    }
}

fun getThemeButtonText(isDarkTheme: Boolean): String {
    return if (isDarkTheme) {
        "Dark Mode"
    } else {
        "Light Mode"
    }
}