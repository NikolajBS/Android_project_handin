package com.example.android_project.data

// GroupDetails.kt

data class GroupDetails(
    val groupId: String = "",
    val name: String = "",
    val description: String = "",
    val members: List<GroupPerson> = emptyList()
)
