package com.example.android_project.screens

import android.graphics.Color
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.android_project.FirebaseManager
import com.example.android_project.R
import kotlinx.coroutines.tasks.await


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

suspend fun GetData(): MutableList<Double> {
    val list = mutableListOf<Double>()
    val data = FirebaseManager.database.child("activities").get().await()
    data.children.forEach{ item ->
        val value = item.value
        if (value is Long) {
            list.add(value.toDouble())
        } else if (value is Double) {
            list.add(value)
        }
    }
    return list
}

@Composable
fun ListActivities() {
    var list by remember { mutableStateOf(emptyList<Double>()) }
    LaunchedEffect(Unit) {
        list = GetData()

    }
    for(i in list) {
        Box(
            modifier = Modifier
                .background(colorResource(id = R.color.gray), RoundedCornerShape(16.dp))
                .size(
                    width = dimensionResource(id = R.dimen.act_width),
                    height = dimensionResource(id = R.dimen.act_height)
                )
                .padding(10.dp),
            contentAlignment = Alignment.CenterStart

        ) {
            if (i < 0) {
                Text(buildAnnotatedString {
                    append("You paid ")
                    withStyle(style = SpanStyle(colorResource(id = R.color.red))){
                        append("$i ")
                    }
                    append("kr")}, fontSize = 30.sp)
            } else {
                Text(buildAnnotatedString {
                    append("You received ")
                    withStyle(style = SpanStyle(colorResource(id = R.color.dgreen))){
                        append("$i ")
                    }
                    append("kr")}, fontSize = 30.sp)
            }
        }
        Spacer(modifier = Modifier.height(20.dp))
    }
}