package com.example.shopify.services.typeconverters

import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.example.shopify.model.response.Category
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converter {
    private val gson = Gson()

    @TypeConverter
    fun fromStringList(list: List<String>): String{
        return gson.toJson(list)
    }
    @TypeConverter
    fun toStringList(json: String): List<String>{
        val type = object: TypeToken<List<String>>(){}.type
        return gson.fromJson(json, type)?: emptyList()
    }
    @TypeConverter
    fun fromCategory(category: Category): String{
        return gson.toJson(category)
    }
    @TypeConverter
    fun toCategory(json: String): Category{
        return gson.fromJson(json, Category::class.java)
    }
}