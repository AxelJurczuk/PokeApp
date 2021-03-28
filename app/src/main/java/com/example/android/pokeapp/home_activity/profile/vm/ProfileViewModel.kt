package com.example.android.pokeapp.home_activity.profile.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android.data.repositories.PokemonRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ProfileViewModel(private val repository: PokemonRepository) : ViewModel() {

    init {
        fetchProfile()
    }

    private var _profile = MutableLiveData<ProfileData>()
    val profile: LiveData<ProfileData>
        get() = _profile


    fun fetchProfile() {
        viewModelScope.launch(Dispatchers.IO) {
            val name = repository.readName().orEmpty()
            val lastName = repository.readLastName().orEmpty()
            val email = repository.readEmail().orEmpty()
            val favoritePokemon = repository.readFavoritePokemon().orEmpty()
            val profileData = ProfileData(name, lastName, email, favoritePokemon)
            _profile.postValue(profileData)
        }
    }


    fun saveName(name: String) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.saveName(name)
        }
    }

    fun saveLastName(lastName: String) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.saveLastName(lastName)
        }
    }

    fun saveEmail(email: String) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.saveEmail(email)
        }
    }

    fun saveFavoritePokemon(pokemon: String) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.saveFavoritePokemon(pokemon)
        }
    }

    data class ProfileData(
        val name: String,
        val lastName: String,
        val email: String,
        val favoritePokemon: String
    )
}
