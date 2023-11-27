package com.company.login.ui.theme.screens

import User
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.input.TextFieldValue
import androidx.navigation.NavHostController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest
import com.google.firebase.database.FirebaseDatabase

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditProfileScreen(navigation: NavHostController) {
    var name by remember { mutableStateOf(TextFieldValue("")) }
    var email by remember { mutableStateOf(TextFieldValue("")) }

    val user = FirebaseAuth.getInstance().currentUser
    val database = FirebaseDatabase.getInstance().getReference("users")

    fun saveProfileData(name: String, email: String) {
        // Update Firebase Auth User Email
        user?.updateEmail(email)?.addOnCompleteListener { task ->
            if (task.isSuccessful) {
                println("User email updated.")
            }
        }

        // Update Firebase Auth User Profile
        val profileUpdates = UserProfileChangeRequest.Builder()
            .setDisplayName(name)
            .build()

        user?.updateProfile(profileUpdates)?.addOnCompleteListener { task ->
            if (task.isSuccessful) {
                println("User profile updated.")
            }
        }

        // Update User Data in Firebase Database
        user?.uid?.let { userId ->
            database.child(userId).setValue(User(name, email)).addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    println("User data updated in database.")
                }
            }
        }
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Text("Change your Profile", style = MaterialTheme.typography.headlineMedium)

        Spacer(modifier = Modifier.height(16.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("Name", style = MaterialTheme.typography.bodyLarge)
            Spacer(modifier = Modifier.width(8.dp))

            OutlinedTextField(
                value = name,
                onValueChange = { name = it },
                label = { Text("Name") }
            )
        }


        Spacer(modifier = Modifier.height(8.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("Email", style = MaterialTheme.typography.bodyLarge)
            Spacer(modifier = Modifier.width(8.dp))

            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Email") }
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = { saveProfileData(name.text, email.text) }) {
            Text("Confirm")
        }
    }
}
/*
@Preview
@Composable
fun EditProfileScreenPreview() {
    EditProfileScreen()
}
*/
