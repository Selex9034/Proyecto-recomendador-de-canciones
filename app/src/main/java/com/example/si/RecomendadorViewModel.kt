package com.example.si

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.example.si.api.RecomendacionItem

class RecomendadorViewModel : ViewModel() {
    // Lista observable de historial
    val historial = mutableStateListOf<RecomendacionItem>()

    // Función para agregar resultados
    fun agregarAlHistorial(nuevas: List<RecomendacionItem>) {
        historial.addAll(nuevas)
    }
}
