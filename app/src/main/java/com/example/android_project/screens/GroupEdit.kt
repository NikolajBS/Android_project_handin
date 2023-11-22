package com.example.android_project.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.android_project.ui.theme.Android_projectTheme

@Composable
fun GroupEdit(navController: NavController) {
    Column {
        Text(text = "Group Edit")
        Button(onClick = { navController.navigate("groupPage") }) {
            Text(text = "Go to Group Page")
        }
    }
}

@Preview
@Composable
fun GroupEditPreview() {
    MaterialTheme {
        GroupEdit(navController = PreviewNavController())
    }
}