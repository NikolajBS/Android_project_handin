package com.example.android_project.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.android_project.FirebaseManager
import com.example.android_project.R
import com.example.android_project.routes.Screen
import kotlinx.coroutines.tasks.await


@Composable
fun HomeScreen(navigation: NavHostController) {

    var groups by remember { mutableStateOf<List<GroupItem>>(emptyList()) }

    // Fetch groups from Firebase
    LaunchedEffect(true) {
        groups = fetchGroups()
    }

    Column(modifier = Modifier.padding(8.dp)) {
        Row {
            Button(onClick = { navigation.navigate(Screen.GroupEdit.route) }) {
                Icon(Icons.Default.Add, contentDescription = "Add")
                Text(text = "Create group")
            }
            Spacer(modifier = Modifier.width(200.dp))
            Icon(Icons.Default.Person, contentDescription = "Profile", modifier = Modifier.size(50.dp))
        }

        // Display groups
        LazyColumn {
            items(groups) { group ->
                GroupItem(group, onItemClick = {
                    navigation.navigate(Screen.GrouPage.route + "/${group.id}")
                })

            }
        }
    }
}

@Composable
fun GroupItem(group: GroupItem, onItemClick: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(colorResource(id = R.color.gray), RoundedCornerShape(16.dp))
            .clickable { onItemClick.invoke() } // Make the item clickable
            .padding(16.dp)
    ) {
        Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = group.name, fontSize = 20.sp)
            Text(text = group.description, fontSize = 16.sp)
        }
    }
    Spacer(modifier = Modifier.height(8.dp))
}

data class GroupItem(val id: String, val name: String, val description: String)

// Function to fetch groups from Firebase
suspend fun fetchGroups(): List<GroupItem> {
    val groups = mutableListOf<GroupItem>()

    // Replace this with your logic to fetch groups from Firebase
    val groupsSnapshot = FirebaseManager.database.child("groups").get().await()
    groupsSnapshot.children.forEach { groupSnapshot ->
        val groupId = groupSnapshot.key ?: ""
        val groupName = groupSnapshot.child("name").getValue(String::class.java) ?: ""
        val groupDescription = groupSnapshot.child("description").getValue(String::class.java) ?: ""
        groups.add(GroupItem(groupId, groupName, groupDescription))
    }

    return groups
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