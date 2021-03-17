package com.example.android.data.remote

import com.example.android.data.models.Pokemon
import retrofit2.Response
import retrofit2.http.GET

interface PokemonAPI {
    @GET
    suspend fun getPokemonList():Response<List<Pokemon>>

}