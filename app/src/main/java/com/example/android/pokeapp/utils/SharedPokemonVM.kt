package com.example.android.pokeapp.utils

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.android.data.models.domain.PokemonDetails

class SharedPokemonVM : ViewModel() {

    private var _pokemonDetails = MutableLiveData<PokemonDetails>()

    val pokemonDetails: LiveData<PokemonDetails>
        get() = _pokemonDetails

    fun setPokemonDetails(pokemonDetails: PokemonDetails){
        _pokemonDetails.value = pokemonDetails
    }
}