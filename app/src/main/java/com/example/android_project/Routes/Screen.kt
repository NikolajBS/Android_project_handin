package com.example.android_project.routes

sealed class Screen(val route: String) {
    object HomeScreen : Screen("home-screen")
    object TransactionActivity : Screen("tranAct-screen")
    object GroupPage: Screen("Group-screen")
    object GroupEdit: Screen("Edit-screen")
    object SettingsScreen : Screen("settings-screen")
    object ProfileScreen : Screen("profile-screen")
    object Login : Screen("login-screen")
    object EditProfile : Screen("edit-profile-screen")
    object SignUp : Screen("signUp-screen")
    object GroupMaking : Screen("groupMakin-screen")

}