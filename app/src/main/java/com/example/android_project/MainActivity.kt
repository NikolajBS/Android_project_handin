package com.example.android_project

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.android_project.ui.theme.Android_projectTheme
import androidx.navigation.compose.rememberNavController
import com.example.android_project.routes.Screen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Android_projectTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    //Greeting("Android")
                    Button(onClick = { /*TODO*/ }) {
                        Text(text = "something")
                    }
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


    Column {
        NavHost(
            navController = navigation,
            startDestination = Screen.HomeScreen.route
        ){

            composable(Screen.HomeScreen.route) { Screen.HomeScreen(navigation = navigation) }
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