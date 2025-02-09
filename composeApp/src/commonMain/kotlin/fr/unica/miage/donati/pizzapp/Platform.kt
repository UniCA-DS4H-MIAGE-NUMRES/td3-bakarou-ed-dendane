package fr.unica.miage.donati.pizzapp

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform