package com.example.invoice.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.invoice.data.Invoice
import com.example.invoice.data.InvoiceViewModel
import com.example.invoice.data.InvoiceWithItems

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InvoiceListScreen(viewModel: InvoiceViewModel, onCreate: () -> Unit, onView: (Int) -> Unit) {
    val invoices by viewModel.allInvoices.collectAsState(initial = emptyList())
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Invoice") }
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = onCreate) {
                Icon(Icons.Default.Add, contentDescription = "Add Invoice")
            }
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .padding(padding)
                .padding(horizontal = 16.dp)
        ) {
            items(invoices) { invoiceWithItems ->
                InvoiceCard(
                    invoiceWithItems, onDelete = { invoice ->
                        viewModel.deleteInvoice(invoice)
                    },
                    onView = { onView(invoiceWithItems.invoice.invoiceId) })
            }
        }
    }
}

@Composable
fun InvoiceCard(
    invoiceWithItems: InvoiceWithItems,
    onDelete: (Invoice) -> Unit,
    onView: (Int) -> Unit
) {
    Card(
        modifier = Modifier
            .padding(vertical = 8.dp)
            .fillMaxWidth()
            .clickable(onClick = {
                onView(invoiceWithItems.invoice.invoiceId)
            }),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(modifier = Modifier) {
                Text(invoiceWithItems.invoice.customerName)
                Text("Total: ₹${invoiceWithItems.invoice.totalAmount}")
            }
            Spacer(Modifier.width(80.dp))
            Icon(
                Icons.Default.Delete,
                contentDescription = "",
                Modifier.clickable(onClick = { onDelete(invoiceWithItems.invoice) })
            )
        }
    }
}
