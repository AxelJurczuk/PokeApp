package com.example.android.pokeapp.home_activity.list.vm

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android.data.models.PokemonDetails
import com.example.android.data.remote.ResultHandler
import com.example.android.data.repositories.PokemonRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ListViewModel(private val repository: PokemonRepository):ViewModel() {

    init {
        fetchPokemons()
    }

    private var _pokemonList= MutableLiveData<List<PokemonDetails>>()
    val pokemonList:LiveData<List<PokemonDetails>>
          get()=_pokemonList

    private fun fetchPokemons(){
        viewModelScope.launch(Dispatchers.IO) {
            when (val result = repository.getPokemonList()){
                is ResultHandler.Success-> _pokemonList.postValue(result.data)
                //ver todos los casos
            }
        }
    }

}