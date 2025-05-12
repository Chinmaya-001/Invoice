package com.example.invoice.dataManager

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class viewModel(private val repository: Repository) : ViewModel() {
    fun getInvoice() =
        repository.getAllInvoice().asLiveData(viewModelScope.coroutineContext)

    fun addInvoice(invoiceData: InvoiceData) {
        viewModelScope.launch {
            repository.addInvoice(invoiceData)
        }

    }

    fun updateInvoice(invoiceData: InvoiceData) {
        viewModelScope.launch {
            repository.updateInvoice(invoiceData)
        }

    }

    fun deleteInvoice(invoiceData: InvoiceData) {
        viewModelScope.launch {
            repository.deleteInvoice(invoiceData.id)
        }
    }

    suspend fun getAllItemList() =
        repository.getAllItemList().asLiveData(viewModelScope.coroutineContext)

    fun addItemList(itemListData: ItemListData) {
        viewModelScope.launch {
            repository.addItem(itemListData)
        }
    }

    fun addQty(itemListData: ItemListData) {
        viewModelScope.launch {
            repository.addQty(itemListData)
        }
    }

    fun addPrice(itemListData: ItemListData) {
        viewModelScope.launch {
            repository.addPrice(itemListData)
        }
    }


    fun addAmount(itemListData: ItemListData) {
        viewModelScope.launch {
            repository.addAmount(itemListData)
        }
    }

}