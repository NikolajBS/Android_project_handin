package com.example.android_project.data

// GroupInfo.kt
data class GroupInfo(
    val groupName: String,
    val description: String,
    val groupId: String,
    val members: List<GroupPerson> = emptyList()
)
