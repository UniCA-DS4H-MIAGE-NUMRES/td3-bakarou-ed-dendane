package fr.unica.miage.donati.pizzapp.data

import fr.unica.miage.donati.pizzapp.model.Order
import kotlinx.coroutines.flow.Flow

expect class OrderRepository(dao: OrderDao) {
    fun getOrders(): Flow<List<Order>>
    suspend fun addOrder(order: Order)
    suspend fun deleteAllOrders()
}
