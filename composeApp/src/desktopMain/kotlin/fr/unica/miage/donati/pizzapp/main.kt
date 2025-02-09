package fr.unica.miage.donati.pizzapp

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import fr.unica.miage.donati.pizzapp.data.DataSourceFactory
import fr.unica.miage.donati.pizzapp.data.OrderDaoDesktop

fun main() = application {
    DataSourceFactory.init(OrderDaoDesktop())

    Window(onCloseRequest = ::exitApplication) {
        App()
    }
}