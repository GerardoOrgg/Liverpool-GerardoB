package utez.edu.mx.liverpool_gerardob.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import utez.edu.mx.liverpool_gerardob.ui.components.ComentarioList
import java.util.Locale
import kotlin.math.roundToInt

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductoDetailsScreen(
    productId: String,
    viewModel: ProdcutoViewModel,
    onBack: () -> Unit
) {
    val productos by viewModel.productos.collectAsState()
    val producto = productos.find { it.id == productId }

    Scaffold(
        containerColor = Color.White,
        topBar = {
            TopAppBar(
                title = {},
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Atrás")
                    }
                },
                actions = {
                    if (producto != null) {
                        IconButton(onClick = { viewModel.toggleFavorito(producto.id) }) {
                            Icon(
                                imageVector = if (producto.isFavorite)
                                    Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                                contentDescription = "Favorito",
                                tint = Color(0xFFCD2C94)
                            )
                        }
                    }
                }
            )
        },
        bottomBar = {
            if (producto != null) {
                Surface(shadowElevation = 8.dp, color = Color.White) {
                    Button(
                        onClick = {  },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 20.dp, vertical = 12.dp)
                            .height(48.dp),
                        shape = RoundedCornerShape(24.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFFCD2C94)
                        )
                    ) {
                        Text("Agregar a mi bolsa", fontSize = 15.sp, fontWeight = FontWeight.Medium)
                    }
                }
            }
        }
    ) { innerPadding ->

        if (producto == null) {
            Box(
                modifier = Modifier.fillMaxSize().padding(innerPadding),
                contentAlignment = Alignment.Center
            ) {
                Text("Producto no encontrado", color = Color(0xFF79747E))
            }
        } else {
            val cantidad = producto.reviews.size
            val promedio = if (cantidad == 0) 0f
            else producto.reviews.map { it.rating }.average().toFloat()
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .verticalScroll(rememberScrollState())
                    .padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .background(Color(0xFFF3EDF7), RoundedCornerShape(16.dp)),
                    contentAlignment = Alignment.Center
                ) {
                    if (producto.image != null) {
                        Image(
                            painter = painterResource(producto.image),
                            contentDescription = producto.name,
                            contentScale = ContentScale.Fit,
                            modifier = Modifier.fillMaxSize()
                        )
                    }
                }

                Text(
                    text = producto.brand.uppercase(),
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFFCD2C94)
                )

                Text(
                    text = producto.name,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF1D1B20)
                )

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(2.dp)
                ) {
                    repeat(5) { index ->
                        Icon(
                            imageVector = Icons.Filled.Star,
                            contentDescription = null,
                            modifier = Modifier.size(16.dp),
                            tint = if (index < promedio.roundToInt())
                                Color(0xFFFFB800) else Color(0xFFE6E0E9)
                        )
                    }
                    if (cantidad == 0) {
                        Text(
                            text = "Sin opiniones",
                            fontSize = 13.sp,
                            color = Color(0xFF49454F),
                            modifier = Modifier.padding(start = 6.dp)
                        )
                    } else {
                        Text(
                            text = "%.1f".format(Locale.US, promedio),
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(start = 6.dp)
                        )
                        Text(
                            text = "($cantidad opiniones)",
                            fontSize = 13.sp,
                            color = Color(0xFF49454F),
                            modifier = Modifier.padding(start = 4.dp)
                        )
                    }
                }

                Text(
                    text = "$" + "%,.2f".format(Locale.US, producto.price),
                    fontSize = 26.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF1D1B20)
                )

                if (producto.isFreeShipping) {
                    Box(
                        modifier = Modifier
                            .background(Color(0xFFE8F5E9), RoundedCornerShape(8.dp))
                            .padding(horizontal = 12.dp, vertical = 6.dp)
                    ) {
                        Text(
                            text = "Envío gratis",
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF2E7D32)
                        )
                    }
                }

                HorizontalDivider(color = Color(0xFFE6E0E9))

                Text(
                    text = "Descripción",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF1D1B20)
                )
                Text(
                    text = producto.description,
                    fontSize = 13.sp,
                    color = Color(0xFF49454F)
                )

                HorizontalDivider(color = Color(0xFFE6E0E9))

                ComentarioList(comentarios = producto.reviews)
            }
        }
    }
}