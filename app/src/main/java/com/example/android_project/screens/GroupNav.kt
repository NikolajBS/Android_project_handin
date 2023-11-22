package com.example.android_project.screens


import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.android_project.screens.Routes
import com.example.android_project.screens.GroupEdit
import com.example.android_project.screens.GroupPage

@Composable
fun GroupNav(){
    val navController = rememberNavController()

    NavHost(navController=navController , startDestination= Routes.GROUP_SCREEN){
        composable(Routes.GROUP_SCREEN){
            GroupPage(navController)
        }

        composable(Routes.EDIT_SCREEN){
            GroupEdit(navController)
        }
    }
}