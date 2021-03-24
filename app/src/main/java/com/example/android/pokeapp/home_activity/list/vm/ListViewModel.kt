package com.example.android.pokeapp.home_activity.list.vm


import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.android.data.models.domain.PokemonDetails
import com.example.android.data.remote.ResultHandler
import com.example.android.data.repositories.PokemonRepository
import com.example.android.pokeapp.commons.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ListViewModel(private val repository: PokemonRepository) : BaseViewModel() {

    val pokemonList: LiveData<List<PokemonDetails>> = repository.mPokemonList

    fun fetchPokemons() {
        _isLoading.value = true
        viewModelScope.launch(Dispatchers.IO) {
            when (val result = repository.getPokemonListAndSave()) {
                is ResultHandler.Success -> {
                    showMessage("Uploaded")
                }
                else -> {
                    setShowError(result)
                }
            }

            _isLoading.postValue(false)
        }
    }

}