package utez.edu.mx.liverpool_gerardob.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import utez.edu.mx.liverpool_gerardob.ui.navigation.NavGraph
import utez.edu.mx.liverpool_gerardob.ui.theme.Liverpool_GerardoBTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Liverpool_GerardoBTheme {
                val navController = rememberNavController()
                NavGraph(navController = navController)
            }
        }
    }
}