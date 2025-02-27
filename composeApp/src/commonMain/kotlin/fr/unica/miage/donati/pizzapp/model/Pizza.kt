package fr.unica.miage.donati.pizzapp.model

import kotlinx.serialization.Serializable

@Serializable
data class Pizza(
    val id: Int,
    val name: String,
    val price: Double,
    val image: String,
    val ingredients: List<String>
)