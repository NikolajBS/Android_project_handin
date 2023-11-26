package com.example.android_project.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.android_project.R
import com.example.android_project.data_classes.AppSettings
import com.example.android_project.routes.Screen

@Composable
fun TransactionActivity(navigation: NavHostController, appSettings: MutableState<AppSettings>, onSettingsChanged: (AppSettings) -> Unit){
    Column (modifier = Modifier.padding(15.dp)) {
        ListActivities()

        Spacer(modifier = Modifier.height(300.dp))

    }
}

@Composable
fun ListActivities() { //Make it retrive data from firebase and color the amount
    for(i in 1..8) {
        Box(
            modifier = Modifier
                .background(color = MaterialTheme.colorScheme.primaryContainer)
                .size(
                    width = dimensionResource(id = R.dimen.act_width),
                    height = dimensionResource(id = R.dimen.act_height)
                )
                .padding(10.dp),
            contentAlignment = Alignment.CenterStart

        ) {
            Text(text = "You paid 500kr", fontSize = 30.sp)
        }
        Spacer(modifier = Modifier.height(20.dp))
    }
}