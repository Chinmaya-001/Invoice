package com.example.invoice

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import com.example.invoice.dataManager.Database
import com.example.invoice.dataManager.InvoiceData
import com.example.invoice.dataManager.ItemListData
import com.example.invoice.dataManager.Repository
import com.example.invoice.dataManager.viewModel
import com.example.invoice.ui.theme.InvoiceTheme
import kotlin.getValue

class MainActivity : ComponentActivity() {
    private val db by lazy {
        Room.databaseBuilder(
            applicationContext,
            Database::class.java,
            name = "note.db"
        ).build()
    }
    private val viewModel by viewModels<viewModel>(
        factoryProducer = {
            object : ViewModelProvider.Factory{
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    return viewModel(Repository(db)) as T
                }
            }
        }
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            InvoiceTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
//                    var costomerName by remember {
//                        mutableStateOf("")
//                    }
//                    var item by remember {
//                        mutableStateOf("")
//                    }
//                    var quantity by remember {
//                        mutableStateOf("")
//                    }
//                    var price by remember {
//                        mutableStateOf("")
//                    }
//                    var amount by remember {
//                        mutableStateOf("")
//                    }
//
//                    val itemList = ItemListData(
//                        itemName = item,
//                        itemQuantity = quantity,
//                        itemPrice = price,
//                        itemAmount = amount
//                    )
//                    val invoicedata = InvoiceData(
//                        comberName = costomerName,
//                        itemListData = itemList
//                    )
                }
            }
        }
    }
}
