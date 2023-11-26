package com.example.android_project.screens

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
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.android_project.data.AppSettings

@Composable
fun ProfileScreen(navigation: NavController, appSettings: MutableState<AppSettings>, onSettingsChanged: (AppSettings) -> Unit) {

    var isVisible: Boolean by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        NameBox(name = "Jens Hansen")
        EmailBox(email = "jens.hansen@gmail.com")
        PasswordBox(password = "P4ssw0rd", isVisible)
        BirthdateBox(date = "08/23-1989")
        CardDetailsBox(name = "Jens Hansen", type = "Visa", number = "4539 7109 0051 9030", date = "07/26", ccv = 676, billingAddress = "Emmagade 17, Odense S")

        Button(onClick = {
            isVisible = !isVisible
        }) {
            if (isVisible) Text(text = "Hide Password") else Text(text = "Show Password")
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