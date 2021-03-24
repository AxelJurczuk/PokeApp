package com.example.android.data.remote


import com.example.android.data.models.domain.PokemonDetails
import com.example.android.data.models.domain.PokemonList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface PokemonAPI {
    @GET("pokemon?limit=40")
    suspend fun getPokemonList():Response<PokemonList>

    @GET("pokemon/{id}")
    suspend fun getPokemonDetails(@Path("id") id:Int):Response<PokemonDetails>

}