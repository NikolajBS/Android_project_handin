package com.example.android_project.screens

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.android_project.FirebaseManager
import com.example.android_project.data.GroupPerson
import com.example.android_project.routes.Screen
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await



@Composable
fun GroupPage(navigation: NavHostController, groupId: String) {
    Log.d("GroupPage", "GroupPage Composable executed with groupId: $groupId")
    val groupRef = FirebaseManager.database.child("groups").child(groupId)

    var totalAmount by remember { mutableStateOf(0.0) }
    var owedAmount by remember { mutableStateOf(0.0) }
    var groupMembers by remember { mutableStateOf<List<GroupPerson>>(emptyList()) }
    var groupName by remember { mutableStateOf("") }
    var groupDescription by remember { mutableStateOf("") }
    var id by remember { mutableStateOf("") }
    var split by remember { mutableStateOf(0.0) }

    LaunchedEffect(groupId) {
        // Use coroutine for database interaction
        val groupSnapshot = groupRef.get().await()

        // Get group name and description
        groupName = groupSnapshot.child("name").getValue(String::class.java) ?: ""
        groupDescription = groupSnapshot.child("description").getValue(String::class.java) ?: ""
        id = groupSnapshot.child("id").getValue(String::class.java) ?: ""

        // Calculate total and owed amounts based on the fetched data
        groupSnapshot.child("people").children.forEach { personSnapshot ->
            val person = personSnapshot.getValue(GroupPerson::class.java)
            totalAmount += person?.amount ?: 0.0
            groupMembers = groupMembers + listOf(person!!)
        }

        // Calculate total amount (sum of individual amounts)
        totalAmount = groupMembers.sumByDouble { it.amount }
        split=totalAmount/groupMembers.size



        // Calculate owed amount (customize this logic based on your requirements)
        owedAmount = if (groupMembers.isNotEmpty()) {
            totalAmount / groupMembers.size
        } else {
            0.0
        }
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(660.dp)
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
                text = "Group Details",
                style = MaterialTheme.typography.headlineMedium
            )

            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp),
                text = "Group Name: $groupName",
                style = MaterialTheme.typography.headlineMedium
            )

            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                text = "Group Description: $groupDescription",
                style = MaterialTheme.typography.headlineMedium
            )
            // Display total amount of group members
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp),
                text = "Total Amount: $totalAmount kr",
                style = MaterialTheme.typography.headlineMedium
            )
            // Display total amount of group members
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp),
                text="Every person owes: $split kr",
                style = MaterialTheme.typography.headlineMedium
            )


            // Display group members
            for (member in groupMembers) {
                GroupMemberItem(member, onRemoveMemberClick = {
                    removeMemberFromGroup(groupId, member)
                })
                Spacer(modifier = Modifier.height(8.dp))
            }

            // Button to navigate to GroupEdit
            Button(
                onClick = {
                    navigation.navigate("${Screen.GroupEdit.route}/$groupId")
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp)
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Add")
                Spacer(modifier = Modifier.width(4.dp))
                Text(text = "Add Person")
            }

        }
    }
}

@Composable
fun GroupMemberItem(member: GroupPerson?, onRemoveMemberClick: () -> Unit) {
    member?.let {
        // Rectangular Box with Text Elements for each group member
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp) // Adjusted height
                .background(Color.Gray, shape = RoundedCornerShape(16.dp)),
            contentAlignment = Alignment.Center
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Text for group member's name
                Text(
                    text = it.name,
                    color = Color.Black
                )

                // Box with white background for the amount owed
                Box(
                    modifier = Modifier
                        .background(Color.White, shape = RoundedCornerShape(16.dp))
                        .padding(8.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "${it.amount} kr",
                        color = Color.Black
                    )
                }

                // Button to remove the member
                Button(
                    onClick = { onRemoveMemberClick() },
                    modifier = Modifier
                        .height(60.dp)
                        .padding(8.dp),
                ) {
                    Icon(imageVector = Icons.Default.Delete, contentDescription = "Remove")
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(text = "Remove")
                }
            }
        }
    }
}

private fun removeMemberFromGroup(groupId: String, member: GroupPerson) {
    val groupRef = FirebaseManager.database.child("groups").child(groupId)

    // Remove the member from the "people" node in the database
    groupRef.child("people").orderByChild("name").equalTo(member.name).addListenerForSingleValueEvent(
        object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for (childSnapshot in snapshot.children) {
                    childSnapshot.ref.removeValue()
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e("GroupPage", "Error removing member: ${error.message}")
            }
        }
    )
}
@Preview
@Composable
fun GroupPagePreview() {
    MaterialTheme {
        val groupId = "1"
        GroupPage(navigation = rememberNavController(), groupId = groupId)
    }
}