package com.example.android_project.screens

import android.util.Log
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
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseAuthWeakPasswordException
import com.example.android_project.screens.HomeScreen
import com.example.android_project.routes.Screen


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUp(navigation: NavHostController) {
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
                // Validate fields
                if (displayName.isNotEmpty() && email.isNotEmpty() && password.isNotEmpty() && confirmPassword.isNotEmpty()) {
                    // Check if passwords match
                    if (password == confirmPassword) {
                        // Create Firebase account
                        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
                            .addOnCompleteListener { task ->
                                if (task.isSuccessful) {
                                    // Account creation success
                                    Log.d("SignUp", "Account created successfully")
                                    navigation.navigate(Screen.HomeScreen.route)
                                } else {
                                    // If account creation fails, display a message to the user.
                                    try {
                                        throw task.exception!!
                                    } catch (e: FirebaseAuthWeakPasswordException) {
                                        // Weak password
                                        Log.e("SignUp", "Weak password", e)
                                    } catch (e: FirebaseAuthInvalidCredentialsException) {
                                        // Invalid email
                                        Log.e("SignUp", "Invalid email", e)
                                    } catch (e: FirebaseAuthUserCollisionException) {
                                        // Account already exists
                                        Log.e("SignUp", "Account already exists", e)
                                    } catch (e: Exception) {
                                        // General exception
                                        Log.e("SignUp", "Account creation failed", e)
                                    }
                                }
                            }
                    } else {
                        // Passwords do not match
                        Log.e("SignUp", "Passwords do not match")
                    }
                } else {
                    // Empty fields
                    Log.e("SignUp", "All fields must be filled")
                }
            },
            modifier = Modifier.fillMaxWidth().padding(top = 16.dp)
        ) {
            Text(text = "Create Account")
        }
    }
}