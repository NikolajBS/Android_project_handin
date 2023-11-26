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
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import androidx.navigation.compose.rememberNavController
import com.example.android_project.FirebaseManager
import com.example.android_project.FirebaseManager.database
import com.example.android_project.data.GroupPerson
import com.google.firebase.database.DatabaseReference
import java.util.UUID

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GroupEdit(navController: NavController) {
    var groupName by remember { mutableStateOf("") }
    var groupDescription by remember { mutableStateOf("") }
    var newPersonName by remember { mutableStateOf("") }
    var newAmount by remember { mutableStateOf("") }

    // Generate a unique ID for the group
    val groupId = UUID.randomUUID().toString()
    val groupRef: DatabaseReference = FirebaseManager.database.child("groups").child(groupId)

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

            // Input Boxes for Group Name and Description
            OutlinedTextField(
                value = groupName,
                onValueChange = { groupName = it },
                label = { Text("Group Name") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp)
            )

            OutlinedTextField(
                value = groupDescription,
                onValueChange = { groupDescription = it },
                label = { Text("Group Description") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
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

            // Button for Confirm
            Button(
                onClick = {
                    // Save group details (name and description) to the Firebase database
                    groupRef.child("id").setValue(groupId)
                    groupRef.child("name").setValue(groupName)
                    groupRef.child("description").setValue(groupDescription)

                    // Navigate to the GroupPage with the generated groupId
                    navController.navigate("${Routes.GROUP_SCREEN}/$groupId")
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Icon(imageVector = Icons.Default.Send, contentDescription = "Send")
                Spacer(modifier = Modifier.width(4.dp))
                Text(text = "Create Group")
            }
        }
    }
}

@Preview
@Composable
fun GroupEditPreview() {
    MaterialTheme {
        GroupEdit(rememberNavController(),)
    }
}