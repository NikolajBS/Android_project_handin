package com.example.android_project.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.android_project.routes.Screen

@Composable
fun HomeScreen(navigation: NavController){
    Column {
        Button(onClick = {  }) {
            Text(text = "Create group")
        }
        CreateGroup()
    }
}
@Composable
fun CreateGroup(){
    Text(text = "something")
}
