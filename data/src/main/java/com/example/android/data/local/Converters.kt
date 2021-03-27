package com.example.android.data.local

import androidx.room.TypeConverter
import com.example.android.data.models.domain.*


class Converters {

    @TypeConverter
    fun fromPicture (picture: Picture):String{
        return picture.frontPicture
    }

    @TypeConverter
    fun toPicture (frontPicture:String):Picture{
        return Picture(frontPicture)
    }

    @TypeConverter
    fun fromSpecies (species: Species):String{
        return species.name
    }

    @TypeConverter
    fun toSpecies (name:String):Species{
        return Species(name)
    }

    @TypeConverter
    fun fromTypes (typesList: List<Types>): String {
        return typesList.joinToString { it.type.name }
    }

    @TypeConverter
    fun toTypes (type:String):List<Types>{
        return type.split(",").map { Types(Type(it.trim())) }
    }

    @TypeConverter
    fun fromMoves (movesList: List<Moves>):String{
        return movesList.joinToString { it.move.name }
    }

    @TypeConverter
    fun toMoves (move:String):List<Moves>{
        return move.split(",").map{ Moves(Move(it.trim()))}
    }

}