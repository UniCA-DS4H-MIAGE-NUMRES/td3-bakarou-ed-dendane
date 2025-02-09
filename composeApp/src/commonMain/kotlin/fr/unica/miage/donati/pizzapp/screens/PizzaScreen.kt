package fr.unica.miage.donati.pizzapp.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import fr.unica.miage.donati.pizzapp.model.Pizza
import fr.unica.miage.donati.pizzapp.navigation.NavControllerWrapper
import org.jetbrains.compose.resources.painterResource
import pizzapp.composeapp.generated.resources.Res
import pizzapp.composeapp.generated.resources.logo
import pizzapp.composeapp.generated.resources.pizza1
import pizzapp.composeapp.generated.resources.pizza2

fun formatPrice(price: Double): String {
    return price.toString()
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PizzaScreen(
    pizza: Pizza,
    navController: NavControllerWrapper,
    onAddToCart: (Pizza, Int) -> Unit
) {
    var extraCheese by remember { mutableStateOf(0) }
    var quantity by remember { mutableStateOf(1) }
    val basePrice = pizza.price
    val cheesePrice = 0.1
    val totalPrice = basePrice * quantity + extraCheese * cheesePrice * quantity

    val imageRes = when (pizza.image) {
        "pizza1" -> Res.drawable.pizza1
        "pizza2" -> Res.drawable.pizza2
        else -> Res.drawable.logo
    }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = { onAddToCart(pizza, quantity) },
                containerColor = Color(0xFF6650A4)
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Ajouter au panier")
            }
        },
        content = { innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(horizontal = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceEvenly
            ) {
                // Image de la pizza
                Image(
                    painter = painterResource(imageRes),
                    contentDescription = pizza.name,
                    modifier = Modifier
                        .size(200.dp)
                        .shadow(4.dp, RoundedCornerShape(16.dp))
                )

                Spacer(modifier = Modifier.height(8.dp))

                // Nom et prix de base
                Text(
                    text = pizza.name,
                    fontSize = 28.sp,
                    color = Color(0xFF1E8560)
                )
                Text(
                    text = "Base Price: €${formatPrice(basePrice)}",
                    fontSize = 18.sp,
                    color = Color.Gray
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Sélection de la quantité
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = "Quantité :",
                        fontSize = 18.sp
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    IconButton(onClick = { if (quantity > 1) quantity-- }) {
                        Icon(Icons.Default.KeyboardArrowDown, contentDescription = "Diminuer")
                    }
                    Text(text = quantity.toString(), fontSize = 18.sp)
                    IconButton(onClick = { quantity++ }) {
                        Icon(Icons.Default.KeyboardArrowUp, contentDescription = "Augmenter")
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Fromage supplémentaire (slider)
                Text(
                    text = "Fromage supplémentaire : $extraCheese g",
                    fontSize = 16.sp,
                    color = Color(0xFF6650A4)
                )
                Slider(
                    value = extraCheese.toFloat(),
                    onValueChange = { extraCheese = it.toInt() },
                    valueRange = 0f..100f,
                    steps = 4,
                    modifier = Modifier.fillMaxWidth(0.8f),
                    colors = SliderDefaults.colors(
                        thumbColor = Color(0xFF6650A4),
                        activeTrackColor = Color(0xFFBB86FC)
                    )
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Affichage du prix total
                Card(
                    modifier = Modifier
                        .fillMaxWidth(0.8f),
                    shape = RoundedCornerShape(12.dp),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFFD0BCFF))
                ) {
                    Box(
                        modifier = Modifier.padding(16.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "Total Price: €${formatPrice(totalPrice)}",
                            fontSize = 22.sp,
                            color = Color.White
                        )
                    }
                }
            }
        }
    )
}
