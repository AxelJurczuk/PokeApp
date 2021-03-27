package com.example.android.data.models.domain

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.android.data.commons.Constants
import com.squareup.moshi.Json

data class PokemonList(
    @Json(name = "results")
    val pokemonList: List<Pokemon>
)

data class Pokemon(val name: String, val url: String)

@Entity(tableName = Constants.TABLE_POKEMON)
data class PokemonDetails(
    @PrimaryKey val id:Int,
    val name: String,
    @Json(name = "sprites")
    val picture: Picture,
    val types: List<Types>,
    val species: Species,
    val weight: Int,
    @Json(name = "base_experience")
    val baseExperience: Int,
    val height:Int,
    val moves:List<Moves>
)

data class Picture(
    @Json(name = "front_default")
    val frontPicture: String
)

data class Species(val name: String)

data class Types (val type:Type)

data class Type (val name:String)

data class Moves (val move:Move)

data class Move (val name:String)


