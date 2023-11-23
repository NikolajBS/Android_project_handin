package com.example.android_project.screens

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.android_project.FirebaseManager
import com.example.android_project.data.GroupPerson
import com.google.firebase.database.DatabaseReference
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await



@Composable
fun GroupPage(navController: NavController, groupId: String) {
    val groupRef = FirebaseManager.database.child("groups").child(groupId)

    var totalAmount by remember { mutableStateOf(0.0) }
    var owedAmount by remember { mutableStateOf(0.0) }
    var groupMembers by remember { mutableStateOf<List<GroupPerson>>(emptyList()) }

    LaunchedEffect(groupId) {
        // Use coroutine for database interaction
        val groupSnapshot = groupRef.get().await()

        // Calculate total and owed amounts based on the fetched data
        groupSnapshot.child("people").children.forEach { personSnapshot ->
            val person = personSnapshot.getValue(GroupPerson::class.java)
            totalAmount += person?.amount ?: 0.0
            groupMembers = groupMembers + listOf(person!!)
        }

        // Calculate owed amount (customize this logic based on your requirements)
        owedAmount = totalAmount / groupSnapshot.child("people").childrenCount
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxSize()
        ) {
            // Top Text Elements
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                text = "Full amount: $totalAmount kr",
                style = MaterialTheme.typography.headlineMedium
            )

            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                text = "You owe: $owedAmount kr",
                style = MaterialTheme.typography.headlineMedium
            )

            // Display group members
            for (member in groupMembers) {
                GroupMemberItem(member)
            }

            // Button to navigate to GroupEdit
            Button(
                onClick = {
                    navController.navigate(Routes.EDIT_SCREEN)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp)
            ) {
                Text(text = "Edit Group")
            }
        }
    }
}

@Composable
fun GroupMemberItem(member: GroupPerson?) {
    member?.let {
        // Customize the UI for displaying each group member
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp),
            text = "${it.name}: ${it.amount} kr",
            style = MaterialTheme.typography.headlineMedium
        )
    }
}



@Preview
@Composable
fun GroupPagePreview() {
    MaterialTheme {
        val groupId = "1"
        GroupPage(navController = rememberNavController(), groupId = groupId)
    }
}