package com.example.android_project.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.android_project.routes.Screen

@Composable
fun SettingsScreen(navigation: NavController) {
    Column(modifier = Modifier.padding(12.dp)) {
        Button(onClick = {navigation.navigate(Screen.HomeScreen.route) }) {
            Text(text = "Home Button")
        }
    }
}