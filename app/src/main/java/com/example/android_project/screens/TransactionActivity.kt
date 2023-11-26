package com.example.android_project.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.android_project.R


@Composable
fun TransactionActivity(navigation: NavHostController){
    val scroll = rememberScrollState()
    Column (modifier = Modifier
        .padding(15.dp)
        .height(660.dp)
        .verticalScroll(scroll)
       )
    {
        ListActivities()
    }
}

@Composable
fun ListActivities() { //Make it retrive data from firebase and color the amount
    for(i in 1..20) {
        Box(
            modifier = Modifier
                .background(colorResource(id = R.color.gray))
                .size(
                    width = dimensionResource(id = R.dimen.act_width),
                    height = dimensionResource(id = R.dimen.act_height)
                )
                .padding(10.dp),
            contentAlignment = Alignment.CenterStart

        ) {
            Text(text = "You paid $i kr", fontSize = 30.sp)
        }
        Spacer(modifier = Modifier.height(20.dp))
    }
}