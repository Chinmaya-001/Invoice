package com.example.invoice.dataManager

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface InvoiceDao {

    @Query("SELECT * FROM invoice_table")
    fun getAllInvoice(): Flow<List<InvoiceData>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
   suspend fun addInvoice(invoiceData: InvoiceData)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateInvoice(invoiceData: InvoiceData)

    @Query("DELETE FROM invoice_table where id = :id")
    suspend fun deleteInvoice(id : Int)
}