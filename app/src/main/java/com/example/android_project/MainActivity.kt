
package com.example.android_project

import LoginScreen
import android.content.Context

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.company.login.ui.theme.screens.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.android_project.data.AppSettings
import com.example.android_project.routes.Screen
import com.example.android_project.screens.GroupEdit
import com.example.android_project.screens.GroupPage
import com.example.android_project.ui.theme.Android_projectTheme
import com.example.android_project.screens.HomeScreen
import com.example.android_project.screens.ProfileScreen
import com.example.android_project.screens.SettingsScreen
import com.example.android_project.screens.TransactionActivity
import com.google.firebase.FirebaseApp
import com.google.firebase.database.FirebaseDatabase


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initializeFirebase(applicationContext)

        setContent {

            //AppNavigation()

            val appSettings = remember { mutableStateOf(AppSettings(isDarkTheme = false, notificationEnabled = true)) }

            Android_projectTheme(appSettings = appSettings) {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Navigation(appSettings = appSettings)
                }
            }

        }
    }
}

private fun initializeFirebase(context: Context) {
    FirebaseApp.initializeApp(context)
    FirebaseDatabase.getInstance().setPersistenceEnabled(true)
}
/*
@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "login") {
        composable("login") {
            LoginScreen(onLoginSuccess = { navController.navigate("home") })
        }
        composable("home") {
            HomeScreen()
        }
        composable("editProfile") {
            EditProfileScreen()
        }
    }
}

 */
@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}


@Composable
fun Navigation(modifier: Modifier = Modifier, appSettings: MutableState<AppSettings>) {
    val navigation = rememberNavController();

    Box(modifier = Modifier.fillMaxSize()) {
        // Other content can go here

        // BottomAppBar should enclose its children as direct content
        BottomAppBar(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .background(color = MaterialTheme.colorScheme.secondary)
                .height(70.dp)
        ) {
            // NavigationBarItem items should be here, directly inside BottomAppBar
            NavigationBarItem(
                selected = false,
                onClick = { navigation.navigate(Screen.TransactionActivity.route) },
                icon = {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Icon(
                            Icons.Default.Star,
                            contentDescription = "Activity",
                            modifier = Modifier.size(45.dp)
                        )
                        Text(text = "Activity")
                    }
                }
            )
            NavigationBarItem(
                selected = false,
                onClick = {
                    Log.d("Navigation", "Navigating to HomeScreen")
                    navigation.navigate(Screen.HomeScreen.route) },
                icon = {
                    Column (
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Icon(
                            Icons.Default.Home,
                            contentDescription = "Home",
                            modifier = Modifier.size(45.dp)
                        )
                        Text(text = "Home")
                    }
                }
            )
            NavigationBarItem(
                selected = false,
                onClick = { navigation.navigate(Screen.SettingsScreen.route) },
                icon = {
                    Column (
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Icon(
                            Icons.Default.Settings,
                            contentDescription = "Settings",
                            modifier = Modifier.size(45.dp)
                        )
                        Text(text = "Settings")
                    }
                }
            )
        }
    }

    Column {
        NavHost(
            navController = navigation,
            startDestination = Screen.Login.route
        ){

            composable(Screen.HomeScreen.route) {
                HomeScreen(navigation = navigation, appSettings = appSettings) {
                    // Update appSettings when needed
                    appSettings.value = it
                }
            }
            composable(Screen.TransactionActivity.route) {
                TransactionActivity(navigation = navigation, appSettings = appSettings) {
                    // Update appSettings when needed
                    appSettings.value = it
                }
            }
            composable(Screen.GroupPage.route + "/{groupId}") { backStackEntry ->
                val groupId = backStackEntry.arguments?.getString("groupId") ?: ""
                GroupPage(navigation = navigation, groupId = groupId)
            }
            composable(Screen.GroupEdit.route) { GroupEdit(navigation = navigation) }
            composable(Screen.SettingsScreen.route) {
                SettingsScreen(navigation = navigation, appSettings = appSettings) {
                    // Update appSettings when needed
                    appSettings.value = it
                }
            }
            composable(Screen.ProfileScreen.route) {
                ProfileScreen(navigation = navigation, appSettings = appSettings) {
                    // Update appSettings when needed
                    appSettings.value = it
                }
            }
            composable(Screen.Login.route){
                LoginScreen(navigation = navigation)
            }


        }

    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    //AppNavigation()
}
@Composable
fun GreetingPreview() {
    val appSettings = remember { mutableStateOf(AppSettings(isDarkTheme = false, notificationEnabled = true)) }
    Android_projectTheme(appSettings = appSettings) {
        Greeting("Android")
    }
}
