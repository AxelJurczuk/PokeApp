package com.example.android.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.android.data.commons.Constants
import com.example.android.data.models.domain.PokemonDetails

@Dao
interface PokemonDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(transactions: List<PokemonDetails>)
    @Query("SELECT * FROM `${Constants.TABLE_POKEMON}`")
    fun load(): LiveData<List<PokemonDetails>>
    @Query("DELETE FROM `${Constants.TABLE_POKEMON}`")
    fun deleteAll()
}