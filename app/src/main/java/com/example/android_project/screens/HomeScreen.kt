package com.example.android_project.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.android_project.R


@Composable
fun HomeScreen(navController: NavController){
    Column(modifier = Modifier.padding(8.dp)) {
        Row {
            Button(onClick = { navController.navigate(Routes.EDIT_SCREEN) }) {
                Icon(Icons.Default.Add, contentDescription = "Add")
                Text(text = "Create group")
            }
            Spacer(modifier = Modifier.width(200.dp))
            Icon(Icons.Default.Person, contentDescription = "Profile", modifier = Modifier.size(50.dp))
        }

        Button(onClick = { navController.navigate(Routes.TRANSACTION_SCREEN) }) {
            Text(text = "navigate to transaction activity screen")            
        }

    }
}

@Composable
fun CreateGroup() {
    Box(
        modifier = Modifier
            .size(
                width = dimensionResource(R.dimen.group_width),
                height = dimensionResource(R.dimen.group_height)
            )
            .background(colorResource(id = R.color.gray), RoundedCornerShape(16.dp))


    ) {
        Column( modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally){
            Text(text = "You owe: 500kr", fontSize = 40.sp)
            Spacer(modifier = Modifier.height(80.dp))
            Box(modifier = Modifier
                .background(colorResource(id = R.color.green), RoundedCornerShape(16.dp))) {
                Text(text = "Pay now", fontSize = 40.sp)
            }
        }
    }
    Spacer(modifier = Modifier.height(30.dp))
}