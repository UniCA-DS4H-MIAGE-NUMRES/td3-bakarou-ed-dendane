package fr.unica.miage.donati.pizzapp.navigation

import androidx.compose.runtime.Composable

expect class NavControllerWrapper {
    fun navigate(route: String)
    var onNavigate: ((String) -> Unit)?
}

@Composable
expect fun rememberNavControllerWrapper(): NavControllerWrapper
