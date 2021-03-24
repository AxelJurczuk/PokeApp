package com.example.android.data.local

import androidx.room.TypeConverter
import com.example.android.data.models.domain.Picture
import com.example.android.data.models.domain.Species
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

    /*
    @TypeConverter
    fun fromTypes (types: Types): Type {
        return types.type
    }

    @TypeConverter
    fun toTypes (type:Type):Types{
        return Types(type)
    }

    @TypeConverter
    fun fromType (type: Type):String{
        return type.name
    }

    @TypeConverter
    fun toType (name:String):Type{
        return Type(name)
    }

     */


}