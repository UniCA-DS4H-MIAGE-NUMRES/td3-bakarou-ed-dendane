package fr.unica.miage.donati.pizzapp.screens

import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import fr.unica.miage.donati.pizzapp.model.OrderItem
import fr.unica.miage.donati.pizzapp.model.Pizza
import fr.unica.miage.donati.pizzapp.navigation.NavControllerWrapper
import kotlinx.coroutines.flow.StateFlow

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CaddyScreen(
    cartItems: StateFlow<List<OrderItem>>,
    onUpdateQuantity: (Int, Int) -> Unit,
    onRemoveItem: (Int) -> Unit,
    onAddDuplicate: (Pizza, Int) -> Unit,
    onCheckout: () -> Unit,
    navController: NavControllerWrapper
) {
    val cartItemsState by cartItems.collectAsState()
    val totalPrice by derivedStateOf {
        cartItemsState.sumOf { item -> (item.pizza.price * item.quantity) + (item.extraCheese * 0.02) }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Votre Commande", color = Color.White, fontSize = 22.sp) },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(0xFF6650A4)),
                navigationIcon = {
                    IconButton(onClick = { navController.navigate("MenuScreen") }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Retour", tint = Color.White)
                    }
                }
            )
        },
        bottomBar = {
            Footer(navController = navController)
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            LazyColumn(
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(cartItemsState) { item ->
                    CartItem(item, onUpdateQuantity, onRemoveItem, onAddDuplicate)
                }
            }

            // Section Total et bouton
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xFFD0BCFF)),
                shape = MaterialTheme.shapes.large
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    horizontalAlignment = Alignment.End
                ) {
                    Text(
                        text = "Total : €${totalPrice.toString()}",
                        fontSize = 20.sp,
                        color = Color(0xFF1E8560),
                        fontWeight = androidx.compose.ui.text.font.FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Button(
                        onClick = onCheckout,
                        modifier = Modifier.fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF6650A4))
                    ) {
                        Text("Passer la commande", color = Color.White, fontSize = 18.sp)
                    }
                }
            }
        }
    }
}

@Composable
fun CartItem(
    item: OrderItem,
    onUpdateQuantity: (Int, Int) -> Unit,
    onRemoveItem: (Int) -> Unit,
    onAddDuplicate: (Pizza, Int) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .shadow(8.dp, shape = MaterialTheme.shapes.medium)
            .clip(MaterialTheme.shapes.medium)
            .padding(8.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFE7DBFF))
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            // Nom et détails de la pizza
            Text(
                text = item.pizza.name,
                fontSize = 20.sp,
                color = Color(0xFF1E8560),
                fontWeight = androidx.compose.ui.text.font.FontWeight.Bold
            )
            Text(
                text = "Prix unitaire : €${item.pizza.price.toString()}",
                fontSize = 16.sp,
                color = Color.Gray
            )
            Text(
                text = if (item.extraCheese > 0) "Fromage supplémentaire : ${item.extraCheese}g" else "Pas de fromage supplémentaire",
                fontSize = 16.sp,
                color = Color(0xFFA0522D)
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Contrôle de la quantité et fromage
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    IconButton(
                        onClick = { if (item.extraCheese > 0) onUpdateQuantity(item.id, item.extraCheese - 10) }
                    ) {
                        Icon(Icons.Default.KeyboardArrowDown, contentDescription = "Diminuer", tint = Color(0xFF6650A4))
                    }
                    Text(
                        text = item.extraCheese.toString(),
                        fontSize = 18.sp,
                        fontWeight = androidx.compose.ui.text.font.FontWeight.Bold,
                        color = Color(0xFFA0522D)
                    )
                    IconButton(
                        onClick = { if (item.extraCheese + 10 <= 100) onUpdateQuantity(item.id, item.extraCheese + 10) }
                    ) {
                        Icon(Icons.Default.KeyboardArrowUp, contentDescription = "Augmenter", tint = Color(0xFF6650A4))
                    }
                }

                IconButton(onClick = { onAddDuplicate(item.pizza, item.extraCheese) }) {
                    Icon(Icons.Default.Add, contentDescription = "Ajouter identique", tint = Color(0xFF1E8560))
                }

                IconButton(onClick = { onRemoveItem(item.id) }) {
                    Icon(Icons.Default.Delete, contentDescription = "Supprimer", tint = Color(0xFFE63946))
                }
            }
        }
    }
}
