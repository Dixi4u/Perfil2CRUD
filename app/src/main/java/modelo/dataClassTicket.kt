package modelo

import java.util.UUID

data class dataClassTicket(
    val uuid: String,
    var Titulo: String,
    val Descripcion: String,
    val Autor: String,
    val Email: String,
    val Creacion: String,
    val Estado: String,
    val Finalizacion: String

)
