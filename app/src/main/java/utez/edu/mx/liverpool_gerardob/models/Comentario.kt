package utez.edu.mx.liverpool_gerardob.models

import java.util.UUID

data class Comentario(
    val id: String = UUID.randomUUID().toString(),
    val title: String,
    val comment: String,
    val rating: Float = 5.0f,
    val author: String? = null
)