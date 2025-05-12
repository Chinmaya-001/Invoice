package com.example.invoice.dataManager

import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity(tableName = "item_table")
data class ItemListData(
    @PrimaryKey(autoGenerate = true)
    var id : Int = 0,
    var itemName: String,
    var itemQuantity: String,
    var itemPrice: String,
    var itemAmount: String,
)
