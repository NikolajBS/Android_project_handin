package com.example.android_project.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.compose.ui.Alignment


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUpScreen(navController: NavHostController) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var displayName by remember { mutableStateOf("") }
    var passwordVisibility by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Sign Up", style = MaterialTheme.typography.headlineMedium)

        OutlinedTextField(
            value = displayName,
            onValueChange = { displayName = it },
            label = { Text("Display Name") },
            leadingIcon = { Icon(imageVector = Icons.Default.Person, contentDescription = "User Icon") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth().padding(top = 16.dp)
        )

        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth().padding(top = 16.dp)
        )

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            singleLine = true,
            visualTransformation = if (passwordVisibility) VisualTransformation.None else PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth().padding(top = 16.dp)
        )

        OutlinedTextField(
            value = confirmPassword,
            onValueChange = { confirmPassword = it },
            label = { Text("Confirm Password") },
            singleLine = true,
            visualTransformation = if (passwordVisibility) VisualTransformation.None else PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth().padding(top = 16.dp)
        )

        Button(
            onClick = {
               // data base logikken kan blive implementeret here
            },
            modifier = Modifier.fillMaxWidth().padding(top = 16.dp)
        ) {
            Text(text = "Create Account")
        }
    }
}
