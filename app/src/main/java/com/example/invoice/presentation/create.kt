package com.example.invoice.presentation


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.invoice.data.InvoiceItem
import com.example.invoice.data.InvoiceViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateInvoiceScreen(viewModel: InvoiceViewModel, onBack: () -> Unit) {
    var customerName by remember { mutableStateOf("") }
    val itemList = remember { mutableStateListOf<InvoiceItem>() }
    var itemName by remember { mutableStateOf("") }
    var quantity by remember { mutableStateOf("") }
    var price by remember { mutableStateOf("") }


    Scaffold (
        topBar = {
            TopAppBar(
                title = { Text("Create Invoice") }
            )
        }
    ){paddingValues ->
        Column(modifier = Modifier.padding(16.dp).padding(paddingValues)) {
            OutlinedTextField(
                value = customerName,
                onValueChange = { customerName = it },
                label = { Text("Customer Name") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.Top, // Or CenterVertically
                horizontalArrangement = Arrangement.spacedBy(8.dp) // Consistent spacing
            ) {
                OutlinedTextField(
                    value = itemName,
                    onValueChange = { itemName = it },
                    label = { Text("Item Name") },
                    modifier = Modifier.weight(3f),
                    shape = RoundedCornerShape(8.dp),
                    singleLine = true // Often desired for such fields
                )
                OutlinedTextField(
                    value = quantity,
                    onValueChange = { quantity = it },
                    label = { Text("Qty") },
                    modifier = Modifier.weight(1f),
                    shape = androidx.compose.foundation.shape.RoundedCornerShape(8.dp),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number), // Good for quantity
                    singleLine = true
                )
                OutlinedTextField(
                    value = price,
                    onValueChange = { price = it },
                    label = { Text("Rs") },
                    modifier = Modifier.weight(1f),
                    shape = androidx.compose.foundation.shape.RoundedCornerShape(8.dp),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal), // Good for price
                    singleLine = true
                )
            }
            Spacer(Modifier.height(16.dp))
            Button(onClick = {
                val qty = quantity.toIntOrNull() ?: 0
                val prc = price.toDoubleOrNull() ?: 0.0
                val amount = qty * prc
                if (itemName.isNotBlank() && qty > 0 && prc > 0) {
                    itemList.add(
                        InvoiceItem(
                            itemName = itemName,
                            itemQuantity = qty,
                            itemPrice = prc,
                            itemAmount = amount,
                            invoiceOwnerId = 0
                        )
                    )
                    itemName = ""; quantity = ""; price = ""
                }
            }) {
                Text("Add Item")
            }
            LazyColumn {
                items(itemList) {
                    Text("${it.itemName} x${it.itemQuantity} = ${it.itemAmount}")
                }
            }
            Button(onClick = {
                if (customerName != "") {
                    viewModel.addInvoice(customerName, itemList.toList())
                    onBack()
                }
            }, modifier = Modifier.fillMaxWidth()) {
                Text("Save Invoice")
            }
        }
    }
}
