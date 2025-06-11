package com.example.si.api

import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// ✅ Petición
data class PromptRequest(val prompt: String)

data class RecomendacionItem(
    val artist: String,
    val song: String,
    val spotify_id: String
)


typealias RecomendacionResponse = List<RecomendacionItem>

// ✅ Interfaz de servicio
interface RecomendadorService {
    @POST("recomendar")
    suspend fun recomendar(@Body request: PromptRequest): RecomendacionResponse
}

// ✅ Cliente Retrofit
object ApiClient {
    val retrofit = Retrofit.Builder()
        .baseUrl("https://recomendador-de-canciones-rpq8.onrender.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val service: RecomendadorService = retrofit.create(RecomendadorService::class.java)
}

