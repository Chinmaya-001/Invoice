package com.example.invoice.presentation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.invoice.data.InvoiceViewModel

@Composable
fun InvoiceApp(viewModel: InvoiceViewModel = viewModel()) {
    val navController = rememberNavController()
    NavHost(navController, startDestination = "list") {
        composable("list") {
            InvoiceListScreen(viewModel = viewModel, onCreate = {
                navController.navigate("create")
            }, onView = { id ->
                navController.navigate("detail/$id")
            })
        }
        composable("create") {
            CreateInvoiceScreen(viewModel = viewModel, onBack = {
                navController.popBackStack()
            })
        }
        composable("detail/{invoiceId}", arguments = listOf(navArgument("invoiceId") { type = NavType.IntType })) {
            val id = it.arguments?.getInt("invoiceId") ?: 0
            InvoiceDetailScreen(invoiceId = id, viewModel = viewModel, onBack = {
                navController.popBackStack()
            },
                onEdit = {
                    navController.navigate("edit/$id")
                })
        }
        composable("edit/{invoiceId}", arguments = listOf(navArgument("invoiceId") { type = NavType.IntType })) {
            val id = it.arguments?.getInt("invoiceId") ?: 0
            InvoiceEditScreen(invoiceId = id, viewModel = viewModel, onBack = {
                navController.popBackStack()
            })
        }

    }
}
