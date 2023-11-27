package com.example.android_project.data

// Model class for Group
data class Group(
    val groupId: String,
    val name: String,
    val description: String,
    val people: List<String>
)
