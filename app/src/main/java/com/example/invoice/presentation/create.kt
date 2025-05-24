package com.example.invoice.presentation


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.invoice.data.InvoiceItem
import com.example.invoice.data.InvoiceViewModel


@Composable
fun CreateInvoiceScreen(viewModel: InvoiceViewModel, onBack: () -> Unit) {
    var customerName by remember { mutableStateOf("") }
    val itemList = remember { mutableStateListOf<InvoiceItem>() }
    var itemName by remember { mutableStateOf("") }
    var quantity by remember { mutableStateOf("") }
    var price by remember { mutableStateOf("") }

    Column(modifier = Modifier.padding(16.dp)) {
        OutlinedTextField(
            value = customerName,
            onValueChange = { customerName = it },
            label = { Text("Customer Name") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = itemName,
            onValueChange = { itemName = it },
            label = { Text("Item Name") },
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = quantity,
            onValueChange = { quantity = it },
            label = { Text("Quantity") },
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = price,
            onValueChange = { price = it },
            label = { Text("Price") },
            modifier = Modifier.fillMaxWidth()
        )
        Button(onClick = {
            val qty = quantity.toIntOrNull() ?: 0
            val prc = price.toDoubleOrNull() ?: 0.0
            val amount = qty * prc
            if (itemName.isNotBlank() && qty > 0 && prc > 0) {
                itemList.add(InvoiceItem(itemName = itemName, itemQuantity = qty, itemPrice = prc, itemAmount = amount, invoiceOwnerId = 0))
                itemName = ""; quantity = ""; price = ""
            }
        }) {
            Text("Add Item")
        }
        LazyColumn {
            items(itemList) {
                Text("${it.itemName} x${it.itemQuantity} = \$${it.itemAmount}")
            }
        }
        Button(onClick = {
            viewModel.addInvoice(customerName, itemList.toList())
            onBack()
        }, modifier = Modifier.fillMaxWidth()) {
            Text("Save Invoice")
        }
    }
}
