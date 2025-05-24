package com.example.invoice.data

import kotlinx.coroutines.flow.Flow

// --- REPOSITORY ---
class InvoiceRepository(val dao: InvoiceDao) {
    val allInvoices: Flow<List<InvoiceWithItems>> = dao.getAllInvoices()

    suspend fun insertInvoiceWithItems(invoice: Invoice, items: List<InvoiceItem>) {
        val invoiceId = dao.insertInvoice(invoice).toInt()
        dao.insertItems(items.map { it.copy(invoiceOwnerId = invoiceId) })
    }
    suspend fun deleteInvoiceWithItems(invoice: Invoice) {
        dao.deleteItemsByInvoiceId(invoice.invoiceId)
        dao.deleteInvoice(invoice)
    }

    suspend fun updateInvoiceWithItems(invoice: Invoice, items: List<InvoiceItem>) {
        dao.updateInvoice(invoice)
        dao.updateItems(items)
    }
}