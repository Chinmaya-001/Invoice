package com.example.invoice.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

// --- DATABASE ---
@Database(entities = [Invoice::class, InvoiceItem::class], version = 1)
abstract class InvoiceDatabase : RoomDatabase() {
    abstract fun invoiceDao(): InvoiceDao

    companion object {
        @Volatile private var INSTANCE: InvoiceDatabase? = null
        fun getDatabase(context: Context): InvoiceDatabase {
            return INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(
                    context.applicationContext,
                    InvoiceDatabase::class.java,
                    "invoice_db"
                ).build().also { INSTANCE = it }
            }
        }
    }
}