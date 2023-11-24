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

    NavHost(navController=navController , startDestination= Routes.HOME_SCREEN){
        composable(Routes.GROUP_SCREEN){
            GroupPage(navController, "groupId")  // Pass the actual groupId
        }
        composable(Routes.HOME_SCREEN){
            HomeScreen( navController)
        }
        composable(Routes.TRANSACTION_SCREEN){
            TransactionActivity(navigation = navController)
        }

        composable(Routes.EDIT_SCREEN){
            GroupEdit(navController, "groupId")  // Pass the actual groupId
        }
    }
}