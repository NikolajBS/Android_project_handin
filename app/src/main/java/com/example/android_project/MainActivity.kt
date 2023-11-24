package com.example.android_project

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.android_project.ui.theme.Android_projectTheme
import com.example.android_project.routes.Screen
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
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    Navigation()
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
fun Navigation() {
    val navigation = rememberNavController()

    Column {
        NavHost(
            navController = navigation,
            startDestination = Screen.HomeScreen.route
        ) {
            composable(Screen.HomeScreen.route) { HomeScreen(navigation = navigation) }
            composable(Screen.TransactionActivity.route) { TransactionActivity(navigation = navigation) }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Android_projectTheme {
        Greeting("Android")
    }
}
