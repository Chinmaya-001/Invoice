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
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.invoice.dataManager.viewModel
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InvoiceList(
    viewModel: viewModel,
    navController: NavController
){
    val invoiceList by viewModel.getInvoice().observeAsState(initial = listOf())

    Scaffold (
        topBar = {
            TopAppBar(
                title = {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ){
                        Text(text = "Invoice", modifier = Modifier.weight(1f))
                        Spacer(Modifier.weight(4f))
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    navController.navigate("create_invoice")
                }
            ){
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add Invoice"
                )
            }
        }
    ){paddingValues ->
        Column(
            Modifier.padding(paddingValues)
                .padding(horizontal = 16.dp)
        ){
            LazyColumn {
                items(invoiceList){invoice->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                        colors = CardDefaults.cardColors(MaterialTheme.colorScheme.surface)
                    ) {
                        Row {
                            Text(
                                "${invoice.customerName}",
                                modifier = Modifier.padding(8.dp),
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold,
                                maxLines = 1,
                            )
                            Icon(
                                imageVector = Icons.Default.Delete,
                                contentDescription = "Delete Invoice",
                                modifier = Modifier.clickable(onClick = {
                                    viewModel.deleteInvoice(invoice)
                                })
                            )
                        }
                    }
                }
            }
        }
    }
}