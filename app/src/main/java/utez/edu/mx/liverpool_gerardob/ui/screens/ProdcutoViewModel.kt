package utez.edu.mx.liverpool_gerardob.ui.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import utez.edu.mx.liverpool_gerardob.R
import utez.edu.mx.liverpool_gerardob.models.Comentario
import utez.edu.mx.liverpool_gerardob.models.ProductDetail
import kotlin.collections.filter


class ProdcutoViewModel : ViewModel() {
    private val _productos = MutableStateFlow(productosEjemplo())
    val productos: StateFlow<List<ProductDetail>> = _productos.asStateFlow()

    private val _query = MutableStateFlow("")
    val query: StateFlow<String> = _query.asStateFlow()

    val productosFiltrados: StateFlow<List<ProductDetail>> =
        combine(_productos, _query) { lista, texto ->
            if (texto.isBlank()) lista
            else lista.filter {
                it.name.contains(texto, ignoreCase = true) ||
                        it.brand.contains(texto, ignoreCase = true)
            }
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = _productos.value
        )

    fun onQueryChange(texto: String) {
        _query.value = texto
    }

    fun toggleFavorito(id: String) {
        _productos.update { lista ->
            lista.map { if (it.id == id) it.copy(isFavorite = !it.isFavorite) else it }
        }
    }

    private fun productosEjemplo(): List<ProductDetail> = listOf(
        ProductDetail(
            brand = "Sony",
            name = "PlayStation 5 Pro 2 TB",
            price = 19999.0,
            rating = 4.9f,
            reviewCount = 342,
            description = "Consola con GPU más potente, ray tracing avanzado y PSSR para mejorar la resolución. Incluye SSD de 2 TB, soporta 4K a hasta 120 fps y Wi-Fi 7. Viene con un control DualSense.",
            image = R.drawable.ps5,
            reviews = listOf(
                Comentario(
                    title = "Muy buena compra, vendi mi casa y no me arrepiento.",
                    comment = "Es la mejor compra que he hecho en todo el año con tal de jugar gta 6",
                    rating = 5.0f,
                    author = "Gerardo"
                ),
                Comentario(
                    title = "Todo al 100",
                    comment = "Muy buen producto estoy satisfecho",
                    rating = 5.0f,
                    author = "David R."
                ),
                Comentario(
                    title = "Recomendado",
                    comment = "Todo bien con mi pedido, solo 2 detalles: la entrega por medio de BigSmart se retrasó un par de días, y la caja del PS5 llegó con unos ligeros golpes.",
                    rating = 3.0f,
                    author = "Lus"
                ),
                Comentario(
                    title = "EXCELENTE",
                    comment = "Hice la compra, todo muy bien, el producto llegó pero con unas cuantas fallas.",
                    rating = 4.0f,
                    author = "Jose"
                )
            )
        ),
        ProductDetail(
            brand = "Rockstar Games",
            name = "Grand Theft Auto VI para PS5 (Preventa)",
            price = 1499.0,
            rating = 0f,
            reviewCount = 0,
            description = "La nueva entrega de la saga, ambientada en el estado de Leonida, con Lucia y Jason como protagonistas. Para PlayStation 5.",
            image = R.drawable.gta6
        ),
        ProductDetail(
            brand = "Sony",
            name = "Control inalámbrico DualSense PS5",
            price = 1699.0,
            rating = 4.8f,
            reviewCount = 915,
            description = "Control con retroalimentación háptica y gatillos adaptativos. Micrófono integrado, batería recargable por USB-C y conexión Bluetooth.",
            image = R.drawable.mando,
            reviews = listOf(
                Comentario(
                    title = "Recomendado",
                    comment = "Lo compre porq es el mando que usa the greft G y es increible.",
                    rating = 5.0f,
                    author = "David R."
                ),


            )
        ),
        ProductDetail(
            brand = "Sony",
            name = "Tarjeta PlayStation Plus 12 meses",
            price = 1299.0,
            rating = 4.7f,
            reviewCount = 520,
            description = "Suscripción de 1 año para jugar en línea, con juegos mensuales y almacenamiento en la nube. Código digital que se canjea en la PlayStation Store.",
            image = R.drawable.tarjeta
        ),
        ProductDetail(
            brand = "LG",
            name = "Pantalla LG OLED evo C5 55\" 4K",
            price = 32999.0,
            rating = 4.8f,
            reviewCount = 210,
            description = "Pantalla OLED 4K de 55\" a 120 Hz con Dolby Vision, HDMI 2.1 y webOS. Ideal para consolas: tiene modo juego, VRR y baja latencia.",
            image = R.drawable.tele
        )
    )
}