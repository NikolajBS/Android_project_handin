package com.example.android_project.screens


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.android_project.FirebaseManager
import com.example.android_project.R
import com.example.android_project.screens.Routes
import com.example.android_project.screens.GroupEdit
import com.example.android_project.screens.GroupPage
import kotlinx.coroutines.tasks.await

@Composable
fun GroupNav() {
    val navController = rememberNavController()

    // Fetch the list of group IDs from the database
    var groupIds by remember { mutableStateOf<List<String>>(emptyList()) }

    // Use a coroutine to fetch data from the database
    LaunchedEffect(Unit) {
        val groupsSnapshot = FirebaseManager.database.child("groups").get().await()
        groupIds = groupsSnapshot.children.mapNotNull { it.key }
    }

    // Content of the screen
    Column {
        // NavHost should be part of the Column
        NavHost(navController = navController, startDestination = Routes.HOME_SCREEN) {
            composable(Routes.GROUP_SCREEN) {
                // Use the first group ID for demonstration, you can adapt this logic based on your requirements
                val groupId = groupIds.firstOrNull() ?: ""
                GroupPage(navController, groupId)
            }
            composable(Routes.HOME_SCREEN) {
                HomeScreen(navController)
            }
            composable(Routes.TRANSACTION_SCREEN) {
                TransactionActivity(navigation = navController)
            }
            composable(Routes.EDIT_SCREEN) {
                GroupEdit(navController)
            }
        }
        // BottomAppBar inside the Column
        BottomAppBar(
            modifier = Modifier
                .fillMaxWidth()
                .background(colorResource(id = R.color.gray))
                .height(56.dp),
            content = {
                NavigationBarItem(
                    selected = false,
                    onClick = { navController.navigate(Routes.TRANSACTION_SCREEN) },
                    icon = {
                        Icon(
                            Icons.Default.Star,
                            contentDescription = "Profile",
                            modifier = Modifier.size(24.dp)
                        )
                    },
                    label = { Text("Profile") }
                )
                NavigationBarItem(
                    selected = false,
                    onClick = { navController.navigate(Routes.HOME_SCREEN) },
                    icon = {
                        Icon(
                            Icons.Default.Home,
                            contentDescription = "Home",
                            modifier = Modifier.size(24.dp)
                        )
                    },
                    label = { Text("Home") }
                )
                NavigationBarItem(
                    selected = false,
                    onClick = { navController.navigate(Routes.GROUP_SCREEN) },
                    icon = {
                        Icon(
                            Icons.Default.AccountCircle,
                            contentDescription = "Group",
                            modifier = Modifier.size(24.dp)
                        )
                    },
                    label = { Text("Group") }
                )
                NavigationBarItem(
                    selected = false,
                    onClick = { navController.navigate(Routes.EDIT_SCREEN) },
                    icon = {
                        Icon(
                            Icons.Default.Edit,
                            contentDescription = "Edit",
                            modifier = Modifier.size(24.dp)
                        )
                    },
                    label = { Text("Edit") }
                )
            }
        )
    }
}
