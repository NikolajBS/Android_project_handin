package com.example.android_project.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.android_project.routes.Screen

@Composable
fun Home(navigation: NavController){
    Column {
        Button(onClick = { navigation.navigate(Screen.NiceScreen.route) }) {
            Text(text = "Create group")
        }
    }
}

fun CreateGroup(){

}
