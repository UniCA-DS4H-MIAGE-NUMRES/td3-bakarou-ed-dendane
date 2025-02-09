package fr.unica.miage.donati.pizzapp.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import fr.unica.miage.donati.pizzapp.navigation.NavControllerWrapper
import fr.unica.miage.donati.pizzapp.utils.PlatformConfig
import org.jetbrains.compose.resources.painterResource
import pizzapp.composeapp.generated.resources.Res
import pizzapp.composeapp.generated.resources.logo

@Composable
fun HomeScreen(navController: NavControllerWrapper) {
    Scaffold(
        containerColor = Color.White,
        content = { innerPadding ->
            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxSize()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = "Bienvenue chez Da Leo",
                        fontSize = 28.sp,
                        color = Color(0xFF333333),
                        style = MaterialTheme.typography.headlineLarge
                    )

                    Image(
                        painter = painterResource(Res.drawable.logo),
                        contentDescription = "Chef Pizza",
                        modifier = Modifier
                            .size(200.dp)
                            .padding(vertical = 16.dp)
                    )

                    Text(
                        text = "Découvrez les meilleures pizzas 🍕",
                        fontSize = 18.sp,
                        color = Color(0xFF555555)
                    )
                }

                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    SimpleButton(
                        text = "Entrer",
                        onClick = { navController.navigate("MenuScreen") }
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    SimpleButton(
                        text = "Voir la commande",
                        onClick = { navController.navigate("CaddyScreen") }
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    SimpleButton(
                        text = "Payer la commande",
                        onClick = { navController.navigate("PaymentScreen") }
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    SimpleButton(
                        text = "Historique des commandes",
                        onClick = { navController.navigate("CommandeHistoryScreen") }
                    )
                }

                Text(
                    text = "© 2025 Da Leo PizzApp - Tous droits réservés",
                    fontSize = 12.sp,
                    color = Color(0xFF888888),
                    modifier = Modifier.padding(top = 16.dp)
                )
            }
        }
    )
}

@Composable
fun SimpleButton(
    text: String,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth(0.8f)
            .height(48.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFF6650a4),
            contentColor = Color.White
        )
    ) {
        Text(text = text, fontSize = 18.sp)
    }
}
