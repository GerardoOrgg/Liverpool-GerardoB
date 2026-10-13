package utez.edu.mx.liverpool_gerardob.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import utez.edu.mx.liverpool_gerardob.models.Comentario
import kotlin.math.roundToInt

@Composable
fun Comentario(
    comentario: Comentario,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFF5F5F5))
    ) {
        Column(modifier = Modifier.padding(12.dp)) {

            Row(horizontalArrangement = Arrangement.spacedBy(2.dp)) {
                repeat(5) { index ->
                    Icon(
                        imageVector = Icons.Filled.Star,
                        contentDescription = null,
                        modifier = Modifier.size(16.dp),
                        tint = if (index < comentario.rating.roundToInt())
                            Color(0xFFFFB800) else Color(0xFFE6E0E9)
                    )
                }
            }
            
            Text(
                text = comentario.title,
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF1D1B20),
                modifier = Modifier.padding(top = 6.dp)
            )

            Text(
                text = comentario.comment,
                fontSize = 12.sp,
                color = Color(0xFF49454F),
                modifier = Modifier.padding(top = 4.dp)
            )

            comentario.author?.let { autor ->
                Text(
                    text = "— $autor",
                    fontSize = 11.sp,
                    color = Color(0xFF79747E),
                    modifier = Modifier.padding(top = 6.dp)
                )
            }
        }
    }
}

