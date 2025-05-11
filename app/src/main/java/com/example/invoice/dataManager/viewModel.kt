package com.example.invoice.dataManager

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class viewModel: ViewModel() {
    val InvoiceListDao: LiveData<List<InvoiceData>> = InvoiceDao.getAllInvoice()

}