package utez.edu.mx.liverpool_gerardob.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import utez.edu.mx.liverpool_gerardob.models.Comentario

@Composable
fun ComentarioList(
    comentarios: List<Comentario>,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = "Opiniones de clientes",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF1D1B20)
        )

        if (comentarios.isEmpty()) {
            Text(
                text = "Aún no hay opiniones",
                fontSize = 13.sp,
                color = Color(0xFF79747E)
            )
        } else {
            comentarios.forEach { comentario ->
                Comentario(comentario = comentario)
            }
        }
    }
}

