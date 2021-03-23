package com.example.android.pokeapp.di

import com.example.android.pokeapp.home_activity.list.vm.ListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val uiModule = module {
    viewModel { ListViewModel(get()) }
}