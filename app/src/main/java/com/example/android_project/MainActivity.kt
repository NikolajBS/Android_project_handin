package com.example.android_project

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
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
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.android_project.routes.Screen
import com.example.android_project.screens.GroupEdit
import com.example.android_project.screens.GroupPage
import com.example.android_project.ui.theme.Android_projectTheme
//import com.example.android_project.routes.Screen
//import com.example.android_project.screens.GroupNav
import com.example.android_project.screens.HomeScreen
import com.example.android_project.screens.TransactionActivity
import com.google.firebase.FirebaseApp
import com.google.firebase.database.FirebaseDatabase

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initializeFirebase(applicationContext)
        setContent {
            Android_projectTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Navigation("name")
                }
            }
        }
    }
}

private fun initializeFirebase(context: Context) {
    FirebaseApp.initializeApp(context)
    FirebaseDatabase.getInstance().setPersistenceEnabled(true)
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Composable
fun Navigation(name: String, modifier: Modifier = Modifier) {

    val navigation = rememberNavController()
    Box(modifier = Modifier.fillMaxSize()) {
        // Other content can go here

        // BottomAppBar should enclose its children as direct content
        BottomAppBar(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .background(colorResource(id = R.color.gray))
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
                    navigation.navigate(Screen.HomeScreen.route)
                },
                icon = {
                    Column(
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
        }

        Column {
            NavHost(
                navController = navigation,
                startDestination = Screen.HomeScreen.route
            ) {
                composable(Screen.HomeScreen.route) { HomeScreen(navigation = navigation) }
                composable(Screen.TransactionActivity.route) { TransactionActivity(navigation = navigation) }
                composable(Screen.GroupPage.route + "/{groupId}") { backStackEntry ->
                    val groupId = backStackEntry.arguments?.getString("groupId") ?: ""
                    GroupPage(navigation = navigation, groupId = groupId)
                }
                composable(Screen.GroupEdit.route + "/{groupId}") { backStackEntry ->
                    val groupId = backStackEntry.arguments?.getString("groupId") ?: ""
                    GroupEdit(navigation = navigation, groupId = groupId)
                }
            }

        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Android_projectTheme {
        // Greeting("Android")
    }
}
