package fr.unica.miage.donati.pizzapp.model

interface OrderInterface { // A SUPPRIMER ?
    val date: String
    val totalPrice: Double
    val paymentMethod: String
    val items: List<OrderItem>
}
