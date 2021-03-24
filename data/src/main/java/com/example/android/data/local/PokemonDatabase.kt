package com.example.android.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.android.data.commons.Constants
import com.example.android.data.models.domain.PokemonDetails

@Database(entities = [PokemonDetails::class], version = 1)
@TypeConverters(Converters::class)
abstract class PokemonDatabase: RoomDatabase() {
    abstract fun pokemonDao(): PokemonDao
    companion object{
        @Volatile
        private var INSTANCE: PokemonDatabase? = null
        fun getInstance(context: Context): PokemonDatabase =
            INSTANCE ?: synchronized(this){
                INSTANCE ?: buildDatabase(context.applicationContext).also {
                    INSTANCE = it
                }
            }
        private fun buildDatabase(appContext: Context): PokemonDatabase{
            return Room.databaseBuilder(appContext, PokemonDatabase::class.java,
                Constants.DATABASE_NAME
            )
                .fallbackToDestructiveMigration()
                .build()
        }
    }
}