import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.android_project.routes.Screen
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(navigation: NavHostController) {//,onLoginSuccess: () -> Unit
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var showError by remember { mutableStateOf(false) }

    val auth = FirebaseAuth.getInstance()

    fun loginUser(email: String, password: String) {
        try {
            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        //onLoginSuccess()
                        saveUserData(email, password) // Call function to save data
                        navigation.navigate(Screen.HomeScreen.route)
                    } else {
                        // Log the specific error message from Firebase
                        Log.e("LoginError", "Error during login: ${task.exception?.message}")
                        showError = true
                    }
                }
        } catch (e: Exception) {
            // Log the exception
            Log.e("LoginError", "Error during login", e)
            showError = true
        }
    }



    Box(
        contentAlignment = Alignment.TopCenter,
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 50.dp)
    ) {
        Card(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .fillMaxWidth()
                .wrapContentHeight(),
            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),

            ) {
            Column(
                modifier = Modifier
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {
                Text("Login", style = MaterialTheme.typography.headlineMedium)

                Spacer(modifier = Modifier.height(16.dp))

                // Email
                Text("Email", style = MaterialTheme.typography.bodyLarge)
                OutlinedTextField(
                    value = email,
                    onValueChange = { email = it },
                    label = { Text("Enter your email") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(8.dp))

                // Password
                Text("Password", style = MaterialTheme.typography.bodyLarge)
                OutlinedTextField(
                    value = password,
                    onValueChange = { password = it },
                    label = { Text("Enter your password") },
                    visualTransformation = PasswordVisualTransformation(),
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(24.dp))

                if (showError) {
                    Text("Invalid email or password", color = Color.Red)
                    Spacer(modifier = Modifier.height(8.dp))
                }

                // Login Button
                Button(
                    onClick = {
                        loginUser(email, password)
                    },

                    modifier = Modifier
                        .padding(horizontal = 50.dp)
                        .height(48.dp)
                )
                {
                    Text("Login")
                }
                // Signup button
                Button(
                    onClick = {
                        // Navigate to the signup screen
                        navigation.navigate(Screen.SignUp.route)
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                ) {
                    Text("Signup")
                }
            }
        }
    }
}

@Preview
@Composable
fun LoginScreenPreview() {
    //LoginScreen(onLoginSuccess = { /* Navigate to home page */ })
}
fun saveUserData(name: String, email: String) {
    val database = FirebaseDatabase.getInstance()
    val ref = database.getReference("users")

    val userId = FirebaseAuth.getInstance().currentUser?.uid
    userId?.let {
        ref.child(it).setValue(User(name, email)).addOnFailureListener { e ->
            // Handle any errors here
            Log.e("FirebaseError", "Error saving user data", e)
        }
    }
}

// com.company.login.ui.theme.screens.User data model
data class User(val name: String, val email: String)