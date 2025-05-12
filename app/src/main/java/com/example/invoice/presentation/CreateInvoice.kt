package com.example.invoice.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.invoice.dataManager.InvoiceData
import com.example.invoice.dataManager.ItemListData
import com.example.invoice.dataManager.viewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateInvoice(
    viewModel: viewModel,
    navController: NavController
) {
    var itemNumber by remember { mutableStateOf(1) }

    var customerName by remember {
        mutableStateOf("")
    }
    var item by remember {
        mutableStateOf("")
    }
    var quantity by remember {
        mutableStateOf("")
    }
    var price by remember {
        mutableStateOf("")
    }
    var amount by remember {
        mutableStateOf("")
    }

    val itemList = ItemListData(
        itemName = item,
        itemQuantity = quantity,
        itemPrice = price,
        itemAmount = amount
    )
    val invoicedata = InvoiceData(
        customerName = customerName,
        itemListData = itemList
    )
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(text = "Create Invoice", modifier = Modifier.weight(1f))
                        Spacer(Modifier.weight(4f))
                    }
                }
            )
        },
    ) { paddingValues ->
        Column(
            Modifier
                .padding(paddingValues)
                .padding(horizontal = 16.dp)
        ) {
            Text(
                "Customer Name",
                modifier = Modifier.padding(8.dp),
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                maxLines = 1,
            )
            OutlinedTextField(
                value = "${invoicedata.customerName}",
                onValueChange = {
                    customerName = it
                },
                modifier = Modifier.fillMaxWidth()
            )
            LazyColumn {
                items(itemNumber) { itemlist ->
                    Row {
                        Column {
                            Text(
                                "Item Name",
                                modifier = Modifier.padding(8.dp),
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold,
                                maxLines = 1,
                            )
                            OutlinedTextField(
                                value = "${itemList.itemName}",
                                onValueChange = {
                                    item = it
                                },
                                modifier = Modifier.fillMaxWidth()
                            )
                        }
                        Column {
                            Text(
                                "Qty",
                                modifier = Modifier.padding(8.dp),
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold,
                                maxLines = 1,
                            )
                            OutlinedTextField(
                                value = "${itemList.itemQuantity}",
                                onValueChange = {
                                    quantity = it
                                },
                                modifier = Modifier.fillMaxWidth()
                            )
                        }
                        Column {
                            Text(
                                "Price",
                                modifier = Modifier.padding(8.dp),
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold,
                                maxLines = 1,
                            )
                            OutlinedTextField(
                                value = "${itemList.itemPrice}",
                                onValueChange = {
                                    price = it
                                },
                                modifier = Modifier.fillMaxWidth()
                            )
                        }
                        Column {
                            Text("Amt",
                                modifier = Modifier.padding(8.dp),
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold,
                                maxLines = 1,
                            )
                            OutlinedTextField(
                                value = "${itemList.itemAmount}",
                                onValueChange = {
                                    amount= it
                                },
                                modifier = Modifier.fillMaxWidth()
                            )
                        }
                    }
                }
            }
            Button(
                onClick = {
                  itemNumber +=1
                },
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
            ) {
                Text("Add Item")

            }
        }
    }
}