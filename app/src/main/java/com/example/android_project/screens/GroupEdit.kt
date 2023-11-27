package com.example.android_project.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.android_project.FirebaseManager
import com.example.android_project.FirebaseManager.database
import com.example.android_project.data.GroupPerson
import com.example.android_project.routes.Screen
import com.google.firebase.database.DatabaseReference
import kotlinx.coroutines.tasks.await
import java.util.UUID

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GroupEdit(navigation: NavHostController, groupId: String) {
    var groupName by remember { mutableStateOf("") }
    var groupDescription by remember { mutableStateOf("") }
    var newPersonName by remember { mutableStateOf("") }
    var newAmount by remember { mutableStateOf("") }

    // Use the passed groupId
    val groupRef: DatabaseReference = FirebaseManager.database.child("groups").child(groupId)

    // Fetch existing group name and description from the database
    LaunchedEffect(groupId) {
        val groupSnapshot = groupRef.get().await()
        groupName = groupSnapshot.child("name").getValue(String::class.java) ?: ""
        groupDescription = groupSnapshot.child("description").getValue(String::class.java) ?: ""
    }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
        ) {
            // Top Text Elements
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                text = "Edit Group",
                style = MaterialTheme.typography.headlineMedium
            )

            // Input Boxes for Group Name and Description with placeholders
            OutlinedTextField(
                value = groupName,
                onValueChange = { groupName = it },
                label = { Text("Group Name") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp),
                placeholder = { Text("Existing Group Name") }
            )

            OutlinedTextField(
                value = groupDescription,
                onValueChange = { groupDescription = it },
                label = { Text("Group Description") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                placeholder = { Text("Existing Group Description") }
            )

            // Input Boxes for Extra Person and Amount
            OutlinedTextField(
                value = newPersonName,
                onValueChange = { newPersonName = it },
                label = { Text("New Person Name") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp)
            )

            OutlinedTextField(
                value = newAmount,
                onValueChange = { newAmount = it },
                label = { Text("New Amount") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
            )

            // Button for Add Person
            Button(
                onClick = {
                    val amount = newAmount.toDoubleOrNull() ?: 0.0
                    val newPersonData = GroupPerson(newPersonName, amount)

                    // Add the new person to the Firebase database
                    groupRef.child("people").push().setValue(newPersonData)

                    // Clear the input fields
                    newPersonName = ""
                    newAmount = ""
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp)
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Add")
                Spacer(modifier = Modifier.width(4.dp))
                Text(text = "Add Person")
            }

            Button(onClick = { navigation.navigate("${Screen.GroupPage.route}/$groupId") }) {
                Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back")
                Spacer(modifier = Modifier.width(4.dp))
                Text(text = "confirm")
            }
        }
    }
}


@Preview
@Composable
fun GroupEditPreview() {
    MaterialTheme {
        //GroupEdit(rememberNavController(),)
    }
}