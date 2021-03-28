package com.example.android.pokeapp.di

import com.example.android.pokeapp.home_activity.HomeActivity
import com.example.android.pokeapp.home_activity.list.vm.ListViewModel
import com.example.android.pokeapp.home_activity.profile.vm.ProfileViewModel
import com.example.android.pokeapp.utils.SharedPokemonVM
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val uiModule = module {

    viewModel { ListViewModel(get()) }
    viewModel { SharedPokemonVM() }
    viewModel { ProfileViewModel(get()) }
}