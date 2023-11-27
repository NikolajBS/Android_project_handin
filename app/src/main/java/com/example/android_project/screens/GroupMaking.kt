package com.example.android_project.screens


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.android_project.FirebaseManager
import com.example.android_project.data.GroupPerson

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GroupMaking(navigation: NavHostController) {
    var groupName by remember { mutableStateOf("") }
    val groupMembers = remember { mutableStateListOf<GroupPerson>() }
    var newMemberName by remember { mutableStateOf("") }
    var newMemberAmount by remember { mutableStateOf("") }

    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = "Create New Group", style = MaterialTheme.typography.headlineMedium)

        OutlinedTextField(
            value = groupName,
            onValueChange = { groupName = it },
            label = { Text("Group Name") },
            modifier = Modifier.fillMaxWidth().padding(top = 16.dp)
        )

        groupMembers.forEach { member ->
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(text = member.name, modifier = Modifier.weight(1f))
                Text(text = "${member.amount}", modifier = Modifier.weight(1f))
                IconButton(onClick = { groupMembers.remove(member) }) {
                    Icon(imageVector = Icons.Default.Delete, contentDescription = "Remove member")
                }
            }
        }

        OutlinedTextField(
            value = newMemberName,
            onValueChange = { newMemberName = it },
            label = { Text("Member Name") },
            modifier = Modifier.fillMaxWidth().padding(top = 16.dp)
        )

        OutlinedTextField(
            value = newMemberAmount,
            onValueChange = { newMemberAmount = it },
            label = { Text("Amount") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth().padding(top = 16.dp)
        )

        Button(
            onClick = {
                if (newMemberName.isNotBlank() && newMemberAmount.toDoubleOrNull() != null) {
                    groupMembers.add(GroupPerson(name = newMemberName, amount = newMemberAmount.toDouble()))
                    newMemberName = ""
                    newMemberAmount = ""
                }
            },
            modifier = Modifier.fillMaxWidth().padding(top = 16.dp)
        ) {
            Text(text = "Add Member")
        }

        Button(
            onClick = {
                // Implement logic to create a group with the entered details
                // Navigate to the group details screen if needed
            },
            modifier = Modifier.fillMaxWidth().padding(top = 16.dp)
        ) {
            Text(text = "Create Group")
        }
    }
}

@Preview
@Composable
fun GroupMakingScreenPreview() {
    GroupMaking(navigation = rememberNavController())
}
