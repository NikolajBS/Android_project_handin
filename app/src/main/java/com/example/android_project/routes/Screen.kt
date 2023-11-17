package com.example.android_project.routes

sealed class Screen(val route: String) {
    object HomeScreen : Screen("home-screen")
}