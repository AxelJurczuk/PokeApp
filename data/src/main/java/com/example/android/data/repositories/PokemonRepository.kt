package com.example.android.data.repositories

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.android.data.commons.BaseRepository
import com.example.android.data.models.PokemonDetails
import com.example.android.data.remote.PokemonAPI
import com.example.android.data.remote.ResultHandler

class PokemonRepository (private val api:PokemonAPI):BaseRepository(){


    //API
    suspend fun getPokemonList(): ResultHandler<List<PokemonDetails>> {
        val pokemonList = mutableListOf<PokemonDetails>()
        val result = safeApiCall (call ={api.getPokemonList()})
        Log.i("pokeList", "error call $result")
        return when(result){
            is ResultHandler.Success->{
                try {
                    result.data.pokemonList.forEachIndexed { index, pokemon ->
                        //no logré encontrar el pokemon 27. Error 404. Pareciera ser error con la API
                        getPokemonDetails(index+1)?.let { pokemonList.add(it) }
                }
                    //aca deberia guardar en la DDBB

                    //y devuelvo algun mensaje de success
                ResultHandler.Success(pokemonList)
                } catch (throwable: Throwable){
                    parseError(throwable)
                }
            }
            is ResultHandler.GenericError -> result
            is ResultHandler.HttpError -> result
            is ResultHandler.NetworkError -> result
        }
    }
    private suspend fun getPokemonDetails(id:Int):PokemonDetails?=  api.getPokemonDetails(id).body()


}