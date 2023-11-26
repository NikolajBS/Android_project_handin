package com.example.android_project

// FirebaseManager.kt

import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

object FirebaseManager {
    // Get a reference to the root of your database
    val database: DatabaseReference = FirebaseDatabase.getInstance().reference

    // Add more functions for interacting with the database if needed
    // For example:
    // fun addUser(name: String, age: Int) {
    //     val user = mapOf("name" to name, "age" to age)
    //     database.child("users").push().setValue(user)
    // }
}
