package com.example.android_project.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.android_project.FirebaseManager
import com.example.android_project.data.GroupPerson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExpenseEntryScreen(navController: NavController, groupId: String) {
    var payer by remember { mutableStateOf("") }
    var amount by remember { mutableStateOf("") }

    // You can add more fields or features as needed

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(text = "Expense Entry", textAlign = TextAlign.Center)

        OutlinedTextField(
            value = payer,
            onValueChange = { payer = it },
            label = { Text("Payer") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)
        )

        OutlinedTextField(
            value = amount,
            onValueChange = { amount = it },
            label = { Text("Amount") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
        )

        Button(
            onClick = {
                // Implement logic to add expense to the group
                // You might want to store the expense details in the Firebase database
                val expenseAmount = amount.toDoubleOrNull() ?: 0.0
                val expensePayer = GroupPerson(payer, expenseAmount)

                GlobalScope.launch(Dispatchers.IO) {
                    FirebaseManager.database.child("groups").child(groupId)
                        .child("expenses").push().setValue(expensePayer)
                }

                // Clear input fields
                payer = ""
                amount = ""
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)
        ) {
            Text(text = "Add Expense")
        }

        // Add more UI components or features as needed

        Button(
            onClick = {
                // Navigate back to the GroupPage
                navController.popBackStack()
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Back to Group")
        }
    }
}

@Preview
@Composable
fun ExpenseEntryScreenPreview() {
    ExpenseEntryScreen(rememberNavController(), "groupId")
}