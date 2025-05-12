import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.invoice.dataManager.viewModel
import com.example.invoice.presentation.InvoiceList // Your Composable destinations

@Composable
fun MyAppNavHost(
    viewModel: viewModel
) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "invoice_list") {
        composable("invoice_list") {
            InvoiceList(viewModel = viewModel, navController = navController) // Your InvoiceList Composable
        }
        composable("create_invoice") {
            // Your Create Invoice Composable
        }
        // Add more composable destinations as needed
    }
}