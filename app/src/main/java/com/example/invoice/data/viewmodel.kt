package com.example.invoice.data

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

// --- VIEWMODEL ---
class InvoiceViewModel(application: Application) : AndroidViewModel(application) {
    private val repo: InvoiceRepository
    val allInvoices: Flow<List<InvoiceWithItems>>

    init {
        val db = InvoiceDatabase.getDatabase(application)
        repo = InvoiceRepository(db.invoiceDao())
        allInvoices = repo.allInvoices
    }

    fun addInvoice(customerName: String, items: List<InvoiceItem>) {
        viewModelScope.launch {
            val total = items.sumOf { it.itemAmount }
            val invoice = Invoice(customerName = customerName, totalAmount = total)
            repo.insertInvoiceWithItems(invoice, items)
        }
    }

    suspend fun getInvoiceById(id: Int): InvoiceWithItems {
        return repo.dao.getInvoiceWithItems(id)
    }
}
