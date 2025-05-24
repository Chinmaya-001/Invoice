package com.example.invoice

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.invoice.data.InvoiceViewModel
import com.example.invoice.presentation.InvoiceApp
import com.example.invoice.ui.theme.InvoiceTheme

class MainActivity : ComponentActivity() {
    private val viewModel: InvoiceViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            InvoiceTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    InvoiceApp(viewModel = viewModel)
                }
            }
        }
    }
}
