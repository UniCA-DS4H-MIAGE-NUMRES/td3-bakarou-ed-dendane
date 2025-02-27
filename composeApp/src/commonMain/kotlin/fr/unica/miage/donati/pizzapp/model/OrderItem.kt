package fr.unica.miage.donati.pizzapp.model

import kotlinx.serialization.Serializable

@Serializable
data class OrderItem(
    val id: Int,
    var pizza: Pizza,
    val quantity: Int,
    val extraCheese: Int
)
