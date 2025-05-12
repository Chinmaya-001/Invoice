package com.example.invoice.dataManager

import androidx.compose.ui.input.key.type
import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

@ProvidedTypeConverter
class ItemListDataConverter {

    @TypeConverter
    fun fromItemListData(itemListData: ItemListData): String {
        // Convert ItemListData to a JSON string
        return Gson().toJson(itemListData)
    }

    @TypeConverter
    fun toItemListData(itemListDataString: String): ItemListData {
        // Convert the JSON string back to ItemListData
        val type = object : TypeToken<ItemListData>() {}.type
        return Gson().fromJson(itemListDataString, type)
    }
}