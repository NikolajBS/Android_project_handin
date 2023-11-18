package com.example.android_project.screens

import androidx.annotation.Dimension
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.navigation.NavController
import com.example.android_project.R
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
fun CreateGroup() {
    Box(
        modifier = Modifier
            .size(width = dimensionResource(R.dimen.group_width),height = dimensionResource(R.dimen.group_height))
            .background(color = Color(R.color.gray))
    ) {
        Column( modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally){
            Text(text = "You owe: 500kr")
            Text(text = "Pay now")
        }
    }
}