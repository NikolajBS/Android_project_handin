package com.example.android_project.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.android_project.ui.theme.Android_projectTheme

@OptIn(ExperimentalMaterial3Api::class)
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
        GroupEdit(rememberNavController())
    }
}