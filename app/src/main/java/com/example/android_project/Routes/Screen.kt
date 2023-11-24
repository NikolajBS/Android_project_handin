package com.company.login.Routes

sealed class Screen(val route: String) {
    object Login : Screen("login")
    object Home : Screen("home")
    object EditProfile : Screen("editProfile")
}
