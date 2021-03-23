package com.example.android.data.di

import com.example.android.data.di.providers.*
import org.koin.dsl.module

val dataModule = module {
    single { provideMockInterceptor(get()) }
    single { provideOkHttpClient(get())}
    single { provideMoshi() }
    single { provideRetrofit(get(),get())}
    single { providePokemonAPI(get()) }
    single { providePokemonRepository(get()) }
}