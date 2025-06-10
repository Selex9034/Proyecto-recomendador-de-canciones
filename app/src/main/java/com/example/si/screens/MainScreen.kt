package com.example.si.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun MainScreen() {
    var sentimiento by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("¿Cómo te sientes hoy?", style = MaterialTheme.typography.titleLarge)
        Spacer(modifier = Modifier.height(16.dp))
        TextField(
            value = sentimiento,
            onValueChange = { sentimiento = it },
            label = { Text("Escribe aquí...") }
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
            // Aquí más adelante iría el modelo de IA
        }) {
            Text("Ok")
        }
    }
}
