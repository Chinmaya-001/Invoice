package com.example.invoice.dataManager

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [ItemListData::class, InvoiceData::class], version = 1, exportSchema = false)
@TypeConverters(ItemListDataConverter::class)
abstract class Database: RoomDatabase(){

    companion object{
        const val DATABASE_NAME = "invoice_database"
    }
    abstract fun invoiceDao(): InvoiceDao
    abstract fun itemListDao(): ItemListDao

}