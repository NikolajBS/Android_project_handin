package com.example.android_project.screens


/*
@Composable
fun GroupNav() {
    val navController = rememberNavController()

    // Fetch the list of group IDs from the database
    var groupIds by remember { mutableStateOf<List<String>>(emptyList()) }

    // Use a coroutine to fetch data from the database
    LaunchedEffect(Unit) {
        val groupsSnapshot = FirebaseManager.database.child("groups").get().await()
        groupIds = groupsSnapshot.children.mapNotNull { it.key }
    }

    // Content of the screen
    Column {
        // NavHost should be part of the Column
        NavHost(navController = navController, startDestination = Routes.HOME_SCREEN) {
            composable(Routes.GROUP_SCREEN) {
                // Use the first group ID for demonstration, you can adapt this logic based on your requirements
                val groupId = groupIds.firstOrNull() ?: ""
                GroupPage(navController, groupId)
            }
            composable(Routes.HOME_SCREEN) {
                HomeScreen(navController)
            }
            composable(Routes.TRANSACTION_SCREEN) {
                TransactionActivity(navigation = navController)
            }
            composable(Routes.EDIT_SCREEN) {
                GroupEdit(navController)
            }
        }
        // BottomAppBar inside the Column
        BottomAppBar(
            modifier = Modifier
                .fillMaxWidth()
                .background(colorResource(id = R.color.gray))
                .height(56.dp),
            content = {
                NavigationBarItem(
                    selected = false,
                    onClick = { navController.navigate(Routes.TRANSACTION_SCREEN) },
                    icon = {
                        Icon(
                            Icons.Default.Star,
                            contentDescription = "Profile",
                            modifier = Modifier.size(24.dp)
                        )
                    },
                    label = { Text("Profile") }
                )
                NavigationBarItem(
                    selected = false,
                    onClick = { navController.navigate(Routes.HOME_SCREEN) },
                    icon = {
                        Icon(
                            Icons.Default.Home,
                            contentDescription = "Home",
                            modifier = Modifier.size(24.dp)
                        )
                    },
                    label = { Text("Home") }
                )
                NavigationBarItem(
                    selected = false,
                    onClick = { navController.navigate(Routes.GROUP_SCREEN) },
                    icon = {
                        Icon(
                            Icons.Default.AccountCircle,
                            contentDescription = "Group",
                            modifier = Modifier.size(24.dp)
                        )
                    },
                    label = { Text("Group") }
                )
                NavigationBarItem(
                    selected = false,
                    onClick = { navController.navigate(Routes.EDIT_SCREEN) },
                    icon = {
                        Icon(
                            Icons.Default.Edit,
                            contentDescription = "Edit",
                            modifier = Modifier.size(24.dp)
                        )
                    },
                    label = { Text("Edit") }
                )
            }
        )
    }
}

 */
