package com.example.android_project

// GroupApp.kt

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.android_project.screens.Routes
import com.example.android_project.screens.GroupEdit
import com.example.android_project.screens.GroupPage

@Composable
fun GroupApp() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Routes.GROUP_SCREEN) {
        composable(Routes.GROUP_SCREEN) {
            GroupPage(navController, "groupId")  // Pass the actual groupId
        }

        composable(Routes.EDIT_SCREEN) {
            GroupEdit(navController, "groupId")  // Pass the actual groupId
        }
    }
}
