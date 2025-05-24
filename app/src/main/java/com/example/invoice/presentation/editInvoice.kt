package com.example.invoice.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.invoice.data.InvoiceViewModel
import com.example.invoice.data.InvoiceWithItems

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InvoiceEditScreen(invoiceId: Int, viewModel: InvoiceViewModel, onBack: () -> Unit) {
    var invoiceWithItems by remember { mutableStateOf<InvoiceWithItems?>(null) }

    LaunchedEffect(invoiceId) {
        invoiceWithItems = viewModel.getInvoiceById(invoiceId)
    }

    invoiceWithItems?.let { data ->
        var customerName by remember { mutableStateOf(data.invoice.customerName) }
        val itemsState = remember { data.items.map { it.copy() }.toMutableStateList() }

        Scaffold (
            topBar = {
                TopAppBar(
                    title = { Text("Edit Invoice") }
                )
            }
        ){ paddingValues ->
            Column(
                Modifier
                    .padding(horizontal = 16.dp)
                    .padding(paddingValues),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                OutlinedTextField(
                    value = customerName,
                    onValueChange = { customerName = it },
                    label = { Text("Customer Name") }
                )

                itemsState.forEachIndexed { index, item ->
                    OutlinedTextField(
                        value = item.itemName,
                        onValueChange = { itemsState[index] = item.copy(itemName = it) },
                        label = { Text("Item Name") }
                    )
                    OutlinedTextField(
                        value = item.itemQuantity.toString(),
                        onValueChange = {
                            itemsState[index] = item.copy(itemQuantity = it.toIntOrNull() ?: 0)
                        },
                        label = { Text("Quantity") }
                    )
                    OutlinedTextField(
                        value = item.itemPrice.toString(),
                        onValueChange = {
                            itemsState[index] = item.copy(itemPrice = it.toDouble())
                        },
                        label = { Text("Price") }
                    )
                }

                Spacer(Modifier.height(16.dp))

                Button(
                    onClick = {
                        val updatedItems = itemsState.map {
                            it.copy(itemAmount = it.itemQuantity * it.itemPrice.toDouble())
                        }
                        val updatedInvoice = data.invoice.copy(
                            customerName = customerName,
                            totalAmount = updatedItems.sumOf { it.itemAmount }
                        )
                        viewModel.updateInvoice(updatedInvoice, updatedItems)
                        onBack()
                    },
                    Modifier.fillMaxWidth()
                ) {
                    Text("Save")
                }
            }
        }
    }
}