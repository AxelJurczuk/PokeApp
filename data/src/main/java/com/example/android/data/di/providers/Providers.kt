package com.example.android.data.di.providers

import android.app.Application
import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.example.android.data.BuildConfig
import com.example.android.data.extensions.dataStore
import com.example.android.data.local.PokemonDatabase
import com.example.android.data.remote.PokemonAPI
import com.example.android.data.remote.interceptors.MockInterceptor
import com.example.android.data.repositories.PokemonRepository
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

fun provideMockInterceptor(application: Application): Interceptor {
    return MockInterceptor(application)
}

fun provideOkHttpClient(mockInterceptor: Interceptor?): OkHttpClient {
    val client = OkHttpClient().newBuilder()
        .connectTimeout(60, TimeUnit.SECONDS)
        .readTimeout(60, TimeUnit.SECONDS)
        .writeTimeout(60, TimeUnit.SECONDS)
    if (BuildConfig.mock) {
        mockInterceptor?.let {
            client.addInterceptor(mockInterceptor)
        }
    }
    return client.build()
}

fun provideMoshi(): Moshi {
    return Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()
}

fun provideRetrofit(httpClient: OkHttpClient, moshi: Moshi): Retrofit = Retrofit.Builder()
    .client(httpClient)
    .baseUrl(com.example.android.data.BuildConfig.BaseURL)
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .build()

fun providePokemonAPI(retrofit: Retrofit): PokemonAPI {
    return retrofit.create(PokemonAPI::class.java)
}

fun providePokemonDatabase(application: Application): PokemonDatabase {
    return PokemonDatabase.getInstance(application)
}

fun provideProfileDataStore(context: Context): DataStore<Preferences> {

    return context.dataStore
}

fun providePokemonRepository(
    pokemonAPI: PokemonAPI,
    pokemonDatabase: PokemonDatabase,
    profileDataStore: DataStore<Preferences>
): PokemonRepository {
    return PokemonRepository(pokemonAPI, pokemonDatabase, profileDataStore)
}