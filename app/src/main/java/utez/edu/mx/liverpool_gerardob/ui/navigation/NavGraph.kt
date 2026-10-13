package utez.edu.mx.liverpool_gerardob.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import utez.edu.mx.liverpool_gerardob.ui.screens.MenuScreen
import utez.edu.mx.liverpool_gerardob.ui.screens.ProdcutoViewModel
import utez.edu.mx.liverpool_gerardob.ui.screens.ProductoDetailsScreen
import utez.edu.mx.liverpool_gerardob.ui.screens.ProductoListScreen



sealed class Route(val path: String) {
    object Menu : Route("menu")
    object Lista : Route("lista")
    object Detalle : Route("detalle/{productId}") {
        fun createRoute(id: String) = "detalle/$id"
    }
}

@Composable
fun NavGraph(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {

    val viewModel: ProdcutoViewModel = viewModel()

    NavHost(
        navController = navController,
        startDestination = Route.Menu.path,
        modifier = modifier
    ) {

        composable(Route.Menu.path) {
            MenuScreen(
                onVerProductos = { navController.navigate(Route.Lista.path) }
            )
        }

        composable(Route.Lista.path) {
            ProductoListScreen(
                viewModel = viewModel,
                onBack = { navController.popBackStack() },
                onProductClick = { id ->
                    navController.navigate(Route.Detalle.createRoute(id))
                }
            )
        }

        composable(
            route = Route.Detalle.path,
            arguments = listOf(navArgument("productId") { type = NavType.StringType })
        ) { entry ->
            ProductoDetailsScreen(
                productId = entry.arguments?.getString("productId") ?: "",
                viewModel = viewModel,
                onBack = { navController.popBackStack() }
            )
        }
    }
}