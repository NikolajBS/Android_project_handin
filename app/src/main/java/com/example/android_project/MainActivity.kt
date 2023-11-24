package com.example.android_project

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.android_project.ui.theme.Android_projectTheme
import androidx.navigation.compose.rememberNavController
import com.example.android_project.routes.Screen
import com.example.android_project.screens.HomeScreen
import com.example.android_project.screens.TransactionActivity

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Android_projectTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    //Greeting("Android")
                    Navigation(name = "testing")
                    //Button(onClick = { /*TODO*/ }) {
                    //    Text(text = "something")
                    //}
                }
            }
        }
    }
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

    val navigation = rememberNavController();
    Box(modifier = Modifier.fillMaxSize()) {
        BottomAppBar(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .background(colorResource(id = R.color.gray))
                .height(56.dp)
        )
        {
            NavigationBarItem(
                selected = true,
                onClick = { navigation.navigate(Screen.TransactionActivity.route) },
                icon = {
                    Icon(
                        Icons.Default.Star,
                        contentDescription = "Profile",
                        modifier = Modifier.size(50.dp)
                    )
                })
            NavigationBarItem(
                selected = true,
                onClick = { navigation.navigate(Screen.HomeScreen.route) },
                icon = {
                    Icon(
                        Icons.Default.Home,
                        contentDescription = "Profile",
                        modifier = Modifier.size(50.dp)
                    )
                })
            }
        }
    Column {
        NavHost(
            navController = navigation,
            startDestination = Screen.HomeScreen.route
        ){

            composable(Screen.HomeScreen.route) { HomeScreen(navigation = navigation) }
            composable(Screen.TransactionActivity.route) { TransactionActivity(navigation = navigation) }

            /*
            composable(Screen.AnotherScreen.route) { AnotherScreen(navigation = navigation)}
            composable(Screen.SwScreen.route) { SwScreen(navigation = navigation)}
            */
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