package fr.unica.miage.donati.pizzapp.utils

expect object PlatformConfig {
    val buttonWidth: Float
    val buttonHeight: Float
    val textSize: Float
    val isAndroid: Boolean
    val isWeb: Boolean
}
