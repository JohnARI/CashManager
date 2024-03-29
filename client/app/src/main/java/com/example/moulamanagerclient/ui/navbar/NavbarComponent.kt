package com.example.moulamanagerclient.ui.navbar

import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavbarComponent(
    navController: NavController,
    navViewModel: NavbarViewModel = viewModel()
) {
    NavigationBar {
        navViewModel.items.forEachIndexed() { index, item ->
            NavigationBarItem(
                selected = navViewModel.selectedItemIndex.intValue == index,
                onClick = {
                    navViewModel.selectedItemIndex.intValue = index
                    navController.navigate(item.route)
                },
                label = {
                    Text(text = item.title)
                },
                icon = {
                    BadgedBox(
                        badge = {
                            if (item.badgeCount != null) {
                                Badge {
                                    Text(text = item.badgeCount.toString())
                                }
                            }

                            else if (item.hasNews) {
                                Badge {

                                }
                            }
                        }
                    ) {
                        Icon(
                            imageVector = if (navViewModel.selectedItemIndex.intValue == index) item.selectedIcon else item.unselectedIcon,
                            contentDescription = item.title,
                        )
                    }
                }
            )
        }
    }
}