package fr.unica.miage.donati.pizzapp.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import fr.unica.miage.donati.pizzapp.data.DataSourceFactory
import fr.unica.miage.donati.pizzapp.model.Pizza
import fr.unica.miage.donati.pizzapp.navigation.NavControllerWrapper
import org.jetbrains.compose.resources.painterResource
import pizzapp.composeapp.generated.resources.Res
import pizzapp.composeapp.generated.resources.logo
import pizzapp.composeapp.generated.resources.pizza1
import pizzapp.composeapp.generated.resources.pizza2
import pizzapp.composeapp.generated.resources.pizza3
import pizzapp.composeapp.generated.resources.pizza4
import pizzapp.composeapp.generated.resources.pizza5
import pizzapp.composeapp.generated.resources.pizza6
import pizzapp.composeapp.generated.resources.pizza7
import pizzapp.composeapp.generated.resources.pizza8
import pizzapp.composeapp.generated.resources.pizza9
import kotlin.math.round

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MenuScreen(navController: NavControllerWrapper) {
    val dataSource = remember { DataSourceFactory.getInstance() }
    val pizzas = dataSource.getPizzas()

    Scaffold(
        containerColor = Color.White,
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Notre Menu",
                        style = MaterialTheme.typography.titleLarge,
                        color = Color(0xFF333366)  // Texte violet foncé
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { navController.navigate("HomeScreen") }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Retour",
                            tint = Color(0xFF333366)  // Icône violette
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(0xFFD0BCFF))  // Fond violet
            )
        },
        bottomBar = {
            Footer(navController = navController)
        },
        content = { innerPadding ->
            // Défilement vertical dans le menu
            LazyColumn(
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(pizzas) { pizza ->
                    PizzaCard(
                        pizza = pizza,
                        onClickPizza = {
                            navController.navigate("PizzaScreen/${pizza.id}")
                        }
                    )
                }
            }
        }
    )
}

@Composable
fun PizzaCard(
    pizza: Pizza,
    onClickPizza: () -> Unit
) {
    val imageRes = when (pizza.image) {
        "pizza1" -> Res.drawable.pizza1
        "pizza2" -> Res.drawable.pizza2
        "pizza3" -> Res.drawable.pizza3
        "pizza4" -> Res.drawable.pizza4
        "pizza5" -> Res.drawable.pizza5
        "pizza6" -> Res.drawable.pizza6
        "pizza7" -> Res.drawable.pizza7
        "pizza8" -> Res.drawable.pizza8
        "pizza9" -> Res.drawable.pizza9
        else -> Res.drawable.logo
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp)
            .clickable(onClick = { onClickPizza() })
            .shadow(elevation = 8.dp, shape = MaterialTheme.shapes.medium)
            .clip(MaterialTheme.shapes.medium),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFF5F3FF))  // Fond doux violet clair
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(imageRes),
                contentDescription = pizza.name,
                modifier = Modifier.size(80.dp)
            )

            Spacer(modifier = Modifier.width(16.dp))

            Column {
                Text(
                    text = pizza.name,
                    style = MaterialTheme.typography.titleMedium,
                    color = Color(0xFF333366)  // Texte violet foncé
                )
                Text(
                    text = "Prix : ${round(pizza.price * 100) / 100} €",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color(0xFF444444)
                )
            }
        }
    }
}
