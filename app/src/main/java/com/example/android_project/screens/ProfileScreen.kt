package com.example.android_project.screens

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.android_project.data.AppSettings
import com.example.android_project.data.User
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.database


@Composable
fun ProfileScreen(navigation: NavController, appSettings: MutableState<AppSettings>, onSettingsChanged: (AppSettings) -> Unit) {
    var name by remember { mutableStateOf(TextFieldValue("")) }
    var email by remember { mutableStateOf(TextFieldValue("")) }
    var password by remember { mutableStateOf(TextFieldValue("")) }
    var birthdate by remember { mutableStateOf(TextFieldValue("")) }
    var carddetails by remember { mutableStateOf(TextFieldValue("")) }

    var isVisible: Boolean by remember { mutableStateOf(false) }

    val database = Firebase.database
    val userRef = FirebaseAuth.getInstance().currentUser?.uid?.let {
        database.getReference("users").child(
            it
        )
    }

    userRef?.addListenerForSingleValueEvent(object : ValueEventListener {
        override fun onDataChange(dataSnapshot: DataSnapshot) {
            val user = dataSnapshot.getValue(User::class.java)
            // Do something with the user data
            if (user != null) {
                name = TextFieldValue(user.name)

                email = TextFieldValue(user.email)
                password = TextFieldValue(user.password)
            }
        }

        override fun onCancelled(databaseError: DatabaseError) {
            Log.e("ProfileScreen", "Failed to read user", databaseError.toException())
        }
    })


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        NameBox(name = name.text)
        EmailBox(email = email.text)
        PasswordBox(password = password.text, isVisible)
        BirthdateBox(date = "08/23-1989")
        CardDetailsBox(name = "Jens Hansen", type = "Visa", number = "4539 7109 0051 9030", date = "07/26", ccv = 676, billingAddress = "Emmagade 17, Odense S")

        Button(onClick = {
            isVisible = !isVisible
        }) {
            if (isVisible) Text(text = "Hide Password") else Text(text = "Show Password")
        }
        Spacer(modifier = Modifier.height(20.dp))
        Button(onClick = {
            navigation.navigate("edit-profile-screen")
        }) {
            Text(text = "Edit Profile")
        }
    }
}

@Composable
fun InfoBox(modifier: Modifier = Modifier, content: @Composable () -> Unit) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp),
        contentAlignment = Alignment.CenterStart
    ) {
        content()
    }
    Spacer(modifier = Modifier.height(20.dp))
}

@Composable
fun NameBox(name: String) {
    Row {
        InfoBox(modifier = Modifier.weight(2f)) {
            Text(text = "Name:")
        }
        InfoBox(modifier = Modifier.weight(3f)) {
            Text(text = name)
        }
    }
}

@Composable
fun EmailBox(email: String) {
    Row {
        InfoBox(modifier = Modifier.weight(2f)) {
            Text(text = "Email:")
        }
        InfoBox(modifier = Modifier.weight(3f)) {
            Text(text = email)
        }
    }
}

@Composable
fun PasswordBox(password: String, isVisible: Boolean) {
    val displayedPassword = if (isVisible) password else password.map { '*' }.joinToString("")
    Row {
        InfoBox(modifier = Modifier.weight(2f)) {
            Text(text = "Password:")
        }
        InfoBox(modifier = Modifier.weight(3f)) {
            Text(text = displayedPassword)
        }
    }
}

@Composable
fun BirthdateBox(date: String) {
    Row {
        InfoBox(modifier = Modifier.weight(2f)) {
            Text(text = "Date of Birth:")
        }
        InfoBox(modifier = Modifier.weight(3f)) {
            Text(text = date)
        }
    }
}

@Composable
fun CardDetailsBox(
    name: String,
    type: String,
    number: String,
    date: String,
    ccv: Int,
    billingAddress: String,
) {
    Row {
        InfoBox(modifier = Modifier.weight(2f)) {
            Text(text = "Cardholder:")
        }
        InfoBox(modifier = Modifier.weight(3f)) {
            Text(text = name)
        }
    }
    Row {
        InfoBox(modifier = Modifier.weight(2f)) {
            Text(text = "Card type:")
        }
        InfoBox(modifier = Modifier.weight(3f)) {
            Text(text = type)
        }
    }
    Row {
        InfoBox(modifier = Modifier.weight(2f)) {
            Text(text = "Card number:")
        }
        InfoBox(modifier = Modifier.weight(3f)) {
            Text(text = number)
        }
    }
    Row {
        InfoBox(modifier = Modifier.weight(2f)) {
            Text(text = "Expiration date:")
        }
        InfoBox(modifier = Modifier.weight(3f)) {
            Text(text = date)
        }
    }
    Row {
        InfoBox(modifier = Modifier.weight(2f)) {
            Text(text = "CCV:")
        }
        InfoBox(modifier = Modifier.weight(3f)) {
            Text(text = ccv.toString())
        }
    }
    Row {
        InfoBox(modifier = Modifier.weight(2f)) {
            Text(text = "Billing address:")
        }
        InfoBox(modifier = Modifier.weight(3f)) {
            Text(text = billingAddress)
        }
    }
}