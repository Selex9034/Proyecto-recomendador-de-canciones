package com.example.si.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.compose.foundation.Image
import androidx.compose.ui.res.painterResource
import com.example.si.R

@Composable
fun WelcomeScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(16.dp))
        Image(
            painter = painterResource(id = R.drawable.snoopy),
            contentDescription = "Snoopy escuchando música",
            modifier = Modifier
                .height(150.dp)
                .padding(8.dp)
        )
        Text("Bienvenido al Recomendador de Canciones", style = MaterialTheme.typography.headlineSmall)

        Spacer(modifier = Modifier.height(32.dp))
        Button(onClick = { navController.navigate("main") }) {
            Text("Comenzar")
        }
    }
}
