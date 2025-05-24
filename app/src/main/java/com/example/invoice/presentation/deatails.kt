package com.example.invoice.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.invoice.data.InvoiceViewModel
import com.example.invoice.data.InvoiceWithItems
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InvoiceDetailScreen(invoiceId: Int, viewModel: InvoiceViewModel, onBack: () -> Unit,onEdit:()-> Unit) {
    var invoiceWithItems by remember { mutableStateOf<InvoiceWithItems?>(null) }
    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current


    LaunchedEffect(invoiceId) {
        invoiceWithItems = viewModel.getInvoiceById(invoiceId)
    }

    invoiceWithItems?.let { data ->
        Scaffold (
            topBar = {
                TopAppBar(
                    title = { Text("Invoice") }
                )
            },
        ){ paddingValues ->
            Column(modifier = Modifier.padding(horizontal = 16.dp).padding(paddingValues)) {
                Text(
                    "Customer: ${data.invoice.customerName}",
                    style = MaterialTheme.typography.titleLarge
                )
                Text("Total Amount: ₹${data.invoice.totalAmount}")
                Spacer(modifier = Modifier.height(16.dp))
                Text("Items:", style = MaterialTheme.typography.titleMedium)
                data.items.forEach {
                    Text("${it.itemName}: ${it.itemQuantity} x ₹${it.itemPrice} = ₹${it.itemAmount}")
                }
                Spacer(modifier = Modifier.height(16.dp))
                Row (
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth().padding(16.dp)
                ){
                    Button(onClick = onBack, modifier = Modifier.weight(0.4f)) {
                        Text("Back")
                    }
                    Spacer(
                        Modifier.width(8.dp)
                    )
                    Button(onClick = onEdit, modifier = Modifier.weight(0.4f)) {
                        Text("Edit")
                    }
                    Spacer(
                        Modifier.width(8.dp)
                    )
                    Button(onClick = {
                        coroutineScope.launch {
                            val invoice = viewModel.getInvoiceById(invoiceId)
                            createInvoicePdf(context, invoice)
                        }
                    }) {
                        Text("Export as PDF")
                    }

                }

            }
        }
    }

}
