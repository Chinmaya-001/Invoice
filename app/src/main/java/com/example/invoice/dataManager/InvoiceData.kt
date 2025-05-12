package com.example.invoice.dataManager

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "invoice_table")
data class InvoiceData(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    var comberName: String,
    var itemListData: ItemListData
)
