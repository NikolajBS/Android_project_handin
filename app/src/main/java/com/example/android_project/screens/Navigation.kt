package com.example.android_project.screens

import androidx.navigation.NavHostController

// app/src/main/java/com/example/ui/navigation/Navigation.kt



object Screens {
    const val GroupPage = "groupPage"
    const val GroupEdit = "groupEdit"
}

fun NavHostController.setupNavigation() {
    composable(route = Screens.GroupPage) { GroupPage(navController = this) }
    composable(route = Screens.GroupEdit) { GroupEdit(navController = this) }
}