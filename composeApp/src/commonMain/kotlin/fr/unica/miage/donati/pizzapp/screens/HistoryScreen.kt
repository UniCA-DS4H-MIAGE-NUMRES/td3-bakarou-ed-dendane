package fr.unica.miage.donati.pizzapp.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import fr.unica.miage.donati.pizzapp.data.OrderRepository
import fr.unica.miage.donati.pizzapp.model.Order
import fr.unica.miage.donati.pizzapp.model.OrderItem
import fr.unica.miage.donati.pizzapp.navigation.NavControllerWrapper
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CommandeHistoryScreen(
    navController: NavControllerWrapper,
    orderRepository: OrderRepository
) {
    val orderHistory = remember { mutableStateOf<List<Order>>(emptyList()) }
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        coroutineScope.launch {
            orderRepository.getOrders().collect { orders ->
                orderHistory.value = orders
            }
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("🧾 Historique des commandes", color = Color.White, fontSize = 22.sp) },
                navigationIcon = {
                    IconButton(onClick = { navController.navigate("HomeScreen") }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Retour", tint = Color.White)
                    }
                },
                actions = {
                    IconButton(onClick = {
                        coroutineScope.launch {
                            orderRepository.deleteAllOrders()
                            orderHistory.value = emptyList()
                        }
                    }) {
                        Icon(Icons.Default.Delete, contentDescription = "Supprimer", tint = Color.White)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(0xFF6650A4))
            )
        },
        bottomBar = { Footer(navController = navController) }
    ) { innerPadding ->
        if (orderHistory.value.isEmpty()) {
            Box(
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    "Aucune commande enregistrée.",
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFFA0522D)
                )
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(orderHistory.value) { order ->
                    OrderCard(order)
                }
            }
        }
    }
}

@Composable
fun OrderCard(order: Order) {
    var isExpanded by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .shadow(8.dp, shape = MaterialTheme.shapes.medium),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFE7DBFF))
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text("📅 ${order.date}", fontWeight = FontWeight.Bold, color = Color(0xFF1E8560), fontSize = 18.sp)
                    Text("Total : €${order.totalPrice.toString()}", color = Color.Black)
                    Text("Paiement : ${order.paymentMethod}", color = Color(0xFFA0522D))
                }
                IconButton(onClick = { isExpanded = !isExpanded }) {
                    Icon(
                        imageVector = if (isExpanded) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
                        contentDescription = "Afficher les détails",
                        tint = Color(0xFF6650A4)
                    )
                }
            }

            if (isExpanded) {
                Spacer(modifier = Modifier.height(8.dp))
                order.items.forEach { item ->
                    OrderItemView(item)
                }
            }
        }
    }
}

@Composable
fun OrderItemView(item: OrderItem) {
    val cheesePrice = item.extraCheese * 0.02
    val totalPrice = item.pizza.price + cheesePrice

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .shadow(4.dp, shape = MaterialTheme.shapes.medium),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFE7F0FF))
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                item.pizza.name,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF1E8560),
                fontSize = 18.sp
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text("Quantité : ${item.quantity}", color = Color.Black)
            Text(
                if (item.extraCheese > 0)
                    "🧀 Fromage supplémentaire : ${item.extraCheese}g (€${cheesePrice.toString()}})"
                else
                    "Pas de fromage supplémentaire",
                color = Color(0xFFA0522D)
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text("Prix total : €${totalPrice.toString()}", fontWeight = FontWeight.Bold, color = Color(0xFF1E8560))
        }
    }
}
