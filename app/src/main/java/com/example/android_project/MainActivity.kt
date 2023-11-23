package com.example.android_project

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.android_project.screens.GroupEdit
import com.example.android_project.screens.Routes
import com.example.android_project.screens.GroupEdit
import com.example.android_project.screens.GroupPage
import com.example.android_project.screens.GroupNav
import com.example.android_project.ui.theme.Android_projectTheme
import com.google.firebase.FirebaseApp
import com.google.firebase.database.FirebaseDatabase


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initializeFirebase(applicationContext)
        setContent {
            Android_projectTheme {
                GroupApp()
            }
        }
    }
}


private fun initializeFirebase(context: Context) {
    // Initialize Firebase
    FirebaseApp.initializeApp(context)
    // Optionally, set the database persistence (offline mode)
    FirebaseDatabase.getInstance().setPersistenceEnabled(true)
}


@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Android_projectTheme {
        Greeting("Android")
    }
}