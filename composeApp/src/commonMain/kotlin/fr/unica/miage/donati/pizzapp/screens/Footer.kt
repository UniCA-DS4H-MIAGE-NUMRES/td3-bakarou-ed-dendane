package fr.unica.miage.donati.pizzapp.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import fr.unica.miage.donati.pizzapp.navigation.NavControllerWrapper

val Purple80 = Color(0xFFD0BCFF)
val Purple40 = Color(0xFF6650A4)
val BackgroundColor = Color(0xFFF5F3FF)  // Fond doux

@Composable
fun Footer(navController: NavControllerWrapper) {
    val selectedItem = remember { mutableStateOf(0) }

    NavigationBar(
        containerColor = BackgroundColor,
        tonalElevation = 4.dp
    ) {
        NavigationBarItem(
            icon = {
                Icon(
                    Icons.Filled.Home,
                    contentDescription = "Accueil",
                    tint = if (selectedItem.value == 0) Purple80 else Color.Gray
                )
            },
            label = { Text("Accueil", fontSize = 12.sp) },
            selected = selectedItem.value == 0,
            onClick = {
                selectedItem.value = 0
                navController.navigate("HomeScreen")
            },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = Purple80,
                selectedTextColor = Purple80,
                unselectedIconColor = Color.Gray,
                unselectedTextColor = Color.Gray
            )
        )

        NavigationBarItem(
            icon = {
                Icon(
                    Icons.Filled.Menu,
                    contentDescription = "Menu",
                    tint = if (selectedItem.value == 1) Purple80 else Color.Gray
                )
            },
            label = { Text("Menu", fontSize = 12.sp) },
            selected = selectedItem.value == 1,
            onClick = {
                selectedItem.value = 1
                navController.navigate("MenuScreen")
            },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = Purple80,
                selectedTextColor = Purple80,
                unselectedIconColor = Color.Gray,
                unselectedTextColor = Color.Gray
            )
        )

        NavigationBarItem(
            icon = {
                Icon(
                    Icons.Filled.ShoppingCart,
                    contentDescription = "Panier",
                    tint = if (selectedItem.value == 2) Purple80 else Color.Gray
                )
            },
            label = { Text("Panier", fontSize = 12.sp) },
            selected = selectedItem.value == 2,
            onClick = {
                selectedItem.value = 2
                navController.navigate("CaddyScreen")
            },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = Purple80,
                selectedTextColor = Purple80,
                unselectedIconColor = Color.Gray,
                unselectedTextColor = Color.Gray
            )
        )

        NavigationBarItem(
            icon = {
                Icon(
                    Icons.Filled.Face,
                    contentDescription = "Historique",
                    tint = if (selectedItem.value == 3) Purple80 else Color.Gray
                )
            },
            label = { Text("Historique", fontSize = 12.sp) },
            selected = selectedItem.value == 3,
            onClick = {
                selectedItem.value = 3
                navController.navigate("CommandeHistoryScreen")
            },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = Purple80,
                selectedTextColor = Purple80,
                unselectedIconColor = Color.Gray,
                unselectedTextColor = Color.Gray
            )
        )
    }
}
