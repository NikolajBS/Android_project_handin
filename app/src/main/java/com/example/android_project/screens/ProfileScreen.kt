package com.example.android_project.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.navigation.NavController
import com.example.android_project.data_classes.AppSettings

@Composable
fun ProfileScreen(navigation: NavController, appSettings: MutableState<AppSettings>, onSettingsChanged: (AppSettings) -> Unit) {

    Column {
        Text(text = "Name: ")
        Text(text = "Email: ")
        Text(text = "Password: ")
        Text(text = "Date of birth: ")
        Text(text = "Card details: ")
    }
}