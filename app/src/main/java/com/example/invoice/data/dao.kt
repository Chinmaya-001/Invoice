package com.example.invoice.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import kotlinx.coroutines.flow.Flow

@Dao
interface InvoiceDao {
    @Insert
    suspend fun insertInvoice(invoice: Invoice): Long

    @Insert suspend fun insertItems(items: List<InvoiceItem>)

    @Transaction
    @Query("SELECT * FROM invoice")
    fun getAllInvoices(): Flow<List<InvoiceWithItems>>

    @Transaction
    @Query("SELECT * FROM invoice WHERE invoiceId = :id")
    suspend fun getInvoiceWithItems(id: Int): InvoiceWithItems
}
