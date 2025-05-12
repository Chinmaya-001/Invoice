package com.example.invoice.dataManager

import androidx.lifecycle.LiveData
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

class Repository(private val db: Database) {


    suspend fun getAllInvoice(): Flow<List<InvoiceData>> {
        return db.invoiceDao().getAllInvoice()
    }


    suspend fun addInvoice(invoiceData: InvoiceData){
        db.invoiceDao().addInvoice(invoiceData)
    }


    suspend fun updateInvoice(invoiceData: InvoiceData){
        db.invoiceDao().updateInvoice(invoiceData)
    }


    suspend fun deleteInvoice(id : Int){
        db.invoiceDao().deleteInvoice(id)
    }



    suspend fun getAllItemList(): Flow<List<ItemListData>>{
        return db.itemListDao().getAllItemList()
    }


    suspend fun addItemList(itemListData: ItemListData){
        db.itemListDao().addItemList(itemListData)

    }
}