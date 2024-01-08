package com.example.android_project.screens


import android.Manifest
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.android_project.FirebaseManager
import com.example.android_project.data.GroupPerson
import com.example.android_project.routes.Screen
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import java.util.UUID

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class, ExperimentalPermissionsApi::class)
@Composable
fun GroupMaking(navigation: NavHostController) {
    var groupIdCounter by remember { mutableStateOf(0) }
    var groupName by remember { mutableStateOf("") }
    var groupDescription by remember { mutableStateOf("") }
    var newPersonName by remember { mutableStateOf("") }
    var newAmount by remember { mutableStateOf("") }

    // Generate a unique ID for the group
    val groupId = UUID.randomUUID().toString()
    val groupRef: DatabaseReference = FirebaseManager.database.child("groups")

    val postNotificationPermission=
        rememberPermissionState(permission = Manifest.permission.POST_NOTIFICATIONS)
    val notificationService= NotificationService(context = LocalContext.current)
    LaunchedEffect(key1 = true ){
        if(!postNotificationPermission.status.isGranted){
            postNotificationPermission.launchPermissionRequest()
        }
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

                    // Retrieve the current maximum groupId
                    groupRef.addListenerForSingleValueEvent(object : ValueEventListener {
                        override fun onDataChange(snapshot: DataSnapshot) {
                            val maxGroupId = snapshot.children.mapNotNull {
                                it.child("id").getValue(String::class.java)?.toIntOrNull()
                            }.maxOrNull() ?: 0

                            groupIdCounter = maxGroupId + 1

                            // Generate a unique ID for the group
                            val newGroupId = groupIdCounter.toString()
                            val newGroupRef: DatabaseReference = FirebaseManager.database.child("groups").child(newGroupId)

                            val newPersonData = GroupPerson(newPersonName, amount)

                            // Add the new person to the Firebase database
                            newGroupRef.child("people").push().setValue(newPersonData)

                            // Clear the input fields
                            newPersonName = ""
                            newAmount = ""
                        }

                        override fun onCancelled(error: DatabaseError) {
                            // Handle error
                        }
                    })
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
                    notificationService.showBasicNotification()
                    if (groupName.isNotEmpty() && groupDescription.isNotEmpty()) {
                        // Use groupId in your Firebase operations
                        val newGroupId = groupIdCounter.toString()
                        groupIdCounter++

                        val newGroupRef: DatabaseReference = FirebaseManager.database.child("groups").child(newGroupId)

                        // Save group details (name and description) to the Firebase database
                        newGroupRef.child("id").setValue(newGroupId)
                        newGroupRef.child("name").setValue(groupName)
                        newGroupRef.child("description").setValue(groupDescription)

                        // Navigate to the GroupPage with the generated groupId
                        navigation.navigate(Screen.GroupPage.route + "/$newGroupId")
                    } else {
                        // Show an error message or UI feedback about empty name or description
                    }
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

@RequiresApi(Build.VERSION_CODES.O)
@Preview
@Composable
fun GroupMakingScreenPreview() {
    GroupMaking(navigation = rememberNavController())
}
