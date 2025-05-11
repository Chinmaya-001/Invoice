package com.example.invoice.dataManager

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface InvoiceDao {

    @Query("SELECT * FROM invoice_table")
    fun getAllInvoice(): LiveData<List<InvoiceData>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addInvoice(invoiceDao: InvoiceDao)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateInvoice(invoiceDao: InvoiceDao)

    @Query("DELETE FROM invoice_table where id = :id")
    fun deleteInvoice(id : Int)
}