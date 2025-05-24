package com.example.invoice.data

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation

@Entity(tableName = "invoice")
data class Invoice(
    @PrimaryKey(autoGenerate = true) val invoiceId: Int = 0,
    val customerName: String,
    val totalAmount: Double
)

@Entity(tableName = "invoice_item")
data class InvoiceItem(
    @PrimaryKey(autoGenerate = true) val itemId: Int = 0,
    val invoiceOwnerId: Int,
    val itemName: String,
    val itemQuantity: Int,
    val itemPrice: Double,
    val itemAmount: Double
)

// --- RELATIONSHIP ---
data class InvoiceWithItems(
    @Embedded val invoice: Invoice,
    @Relation(
        parentColumn = "invoiceId",
        entityColumn = "invoiceOwnerId"
    ) val items: List<InvoiceItem>
)
