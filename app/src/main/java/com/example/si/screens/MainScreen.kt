package com.example.si.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.si.api.ApiClient
import com.example.si.api.PromptRequest
import kotlinx.coroutines.launch
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.si.RecomendadorViewModel



@Composable
fun MainScreen(viewModel: RecomendadorViewModel = viewModel()) {
    val pagerState = rememberPagerState(pageCount = { 2 })

    HorizontalPager(state = pagerState) { page ->
        when (page) {
            0 -> EntradaSentimientoScreen(viewModel)
            1 -> HistorialScreen(viewModel)
        }
    }
}


@Composable
fun EntradaSentimientoScreen(viewModel: RecomendadorViewModel) {
    var sentimiento by remember { mutableStateOf("") }
    var recomendacion by remember { mutableStateOf("") }
    var isLoading by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()

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

        Button(
            onClick = {
                isLoading = true
                recomendacion = ""
                scope.launch {
                    try {
                        val response = ApiClient.service.recomendar(PromptRequest(sentimiento))
                        recomendacion = response.joinToString("\n") { "${it.song} - ${it.artist}" }

                        viewModel.agregarAlHistorial(response)


                    } catch (e: Exception) {
                        recomendacion = "Error: ${e.localizedMessage ?: "desconocido"}"
                        e.printStackTrace()
                    } finally {
                        isLoading = false
                    }
                }
            }
        ) {
            Text("Ok")
        }

        Spacer(modifier = Modifier.height(16.dp))
        if (isLoading) {
            CircularProgressIndicator()
        } else {
            Text(recomendacion)
        }
    }
}


@Composable
fun HistorialScreen(viewModel: RecomendadorViewModel) {
    val historial = viewModel.historial

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
    ) {
        Text("Historial de Recomendaciones", style = MaterialTheme.typography.titleLarge)
        Spacer(modifier = Modifier.height(16.dp))

        historial.forEach {
            Text("🎵 $it", style = MaterialTheme.typography.bodyLarge)
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}
