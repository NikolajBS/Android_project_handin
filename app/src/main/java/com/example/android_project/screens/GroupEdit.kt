package com.example.android_project.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.android_project.ui.theme.Android_projectTheme

@Composable
fun GroupEdit(navController: NavHostController) {
    var newPerson by remember { mutableStateOf("") }
    var newAmount by remember { mutableStateOf("") }

    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
                .background(Color.White)
        ) {
            // Top Text Element
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                text = "Edit Group",
                textAlign = TextAlign.Center
            )

            // Input Boxes for Extra Person and Amount
            OutlinedTextField(
                value = newPerson,
                onValueChange = { newPerson = it },
                label = { Text("New Person") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp)
            )

            OutlinedTextField(
                value = newAmount,
                onValueChange = { newAmount = it },
                label = { Text("New Amount") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
            )

            // Buttons for Delete, Add Person, Confirm
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Button(
                    onClick = {
                        // Handle delete button click
                        // For now, you can add your logic here
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 8.dp)
                ) {
                    Text(text = "Delete")
                }

                Button(
                    onClick = {
                        // Handle add person button click
                        // For now, you can add your logic here
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 8.dp)
                ) {
                    Text(text = "Add Person")
                }

                Button(
                    onClick = {
                        // Handle confirm button click
                        // For now, navigate back to the GroupPage
                        navController.popBackStack()
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(text = "Confirm")
                }
            }
        }
    }
}


@Preview
@Composable
fun GroupEditPreview() {
    MaterialTheme {
        GroupEdit()
    }
}