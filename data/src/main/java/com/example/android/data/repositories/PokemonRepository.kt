package com.example.android.data.repositories


import android.util.Log
import androidx.lifecycle.LiveData
import com.example.android.data.commons.BaseRepository
import com.example.android.data.local.PokemonDatabase
import com.example.android.data.models.domain.PokemonDetails
import com.example.android.data.remote.PokemonAPI
import com.example.android.data.remote.ResultHandler

class PokemonRepository(
    private val api: PokemonAPI,
    private val pokemonDB: PokemonDatabase
) : BaseRepository() {

    val mPokemonList: LiveData<List<PokemonDetails>> by lazy {
        pokemonDB.pokemonDao().load()
    }

    //API
    suspend fun getPokemonListAndSave(): ResultHandler<List<PokemonDetails>> {
        val pokemonList = mutableListOf<PokemonDetails>()

        return when (val result = safeApiCall(call = { api.getPokemonList() })) {
            is ResultHandler.Success -> {
                try {
                    result.data.pokemonList.forEachIndexed { index, pokemon ->
                        //no logré encontrar el pokemon 27. Error 404. Pareciera ser error con la API
                        getPokemonDetails(index + 1)?.let {
                            pokemonList.add(it)
                        }
                    }
                    //guardo la lista en la DDBB
                    pokemonDB.pokemonDao().save(pokemonList)
                    //devuelvo un mensaje de success
                    ResultHandler.Success(pokemonList)
                } catch (throwable: Throwable) {
                    parseError(throwable)
                }
            }
            is ResultHandler.GenericError -> result
            is ResultHandler.HttpError -> result
            is ResultHandler.NetworkError -> result
        }
    }

    private suspend fun getPokemonDetails(id: Int): PokemonDetails? =
        api.getPokemonDetails(id).body()

    //Database
    suspend fun deletePokemons() {
        pokemonDB.pokemonDao().deleteAll()
    }
}