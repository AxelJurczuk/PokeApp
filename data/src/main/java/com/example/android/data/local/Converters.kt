package com.example.android.data.local

import androidx.room.TypeConverter
import com.example.android.data.models.domain.Picture
import com.example.android.data.models.domain.Species
import com.example.android.data.models.domain.Type
import com.example.android.data.models.domain.Types


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

}