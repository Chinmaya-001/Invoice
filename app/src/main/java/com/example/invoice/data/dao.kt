package com.example.invoice.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface InvoiceDao {
    @Insert
    suspend fun insertInvoice(invoice: Invoice): Long

    @Insert suspend fun insertItems(items: List<InvoiceItem>)

    @Delete
    suspend fun deleteInvoice(invoice: Invoice)

    @Query("DELETE FROM invoice_item WHERE invoiceOwnerId = :invoiceId")
    suspend fun deleteItemsByInvoiceId(invoiceId: Int)

    @Transaction
    @Query("SELECT * FROM invoice")
    fun getAllInvoices(): Flow<List<InvoiceWithItems>>

    @Transaction
    @Query("SELECT * FROM invoice WHERE invoiceId = :id")
    suspend fun getInvoiceWithItems(id: Int): InvoiceWithItems

    @Update
    suspend fun updateInvoice(invoice: Invoice)

    @Update
    suspend fun updateItems(items: List<InvoiceItem>)
}
