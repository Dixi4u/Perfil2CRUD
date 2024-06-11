package RecyclerViewHelper

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import diego.arriaza.actividadcrudperfil2.R
import diego.arriaza.actividadcrudperfil2.detalle_mascota
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import modelo.ClaseConexion
import modelo.dataClassTicket


class Adaptador(private var Datos: List<dataClassTicket>) : RecyclerView.Adapter<ViewHolder>() {

    fun actualizarLista(nuevaLista: List<dataClassTicket>) {
        Datos = nuevaLista
        notifyDataSetChanged()
    }

    fun actualicePantalla(uuid: String, nuevoNombre: String){
        val index = Datos.indexOfFirst { it.UUID_Ticket == uuid }
        Datos[index].Titulo = nuevoNombre
        notifyDataSetChanged()
    }


    fun eliminarDatos(nombreMascota: String, posicion: Int){
        //Actualizo la lista de datos y notifico al adaptador
        val listaDatos = Datos.toMutableList()
        listaDatos.removeAt(posicion)

        GlobalScope.launch(Dispatchers.IO){
            //1- Creamos un objeto de la clase conexion
            val objConexion = ClaseConexion().cadenaConexion()

            //2- Crear una variable que contenga un PrepareStatement
            val deleteMascota = objConexion?.prepareStatement("delete from Ticket where Titulo = ?")!!
            deleteMascota.setString(1, nombreMascota)
            deleteMascota.executeUpdate()

            val commit = objConexion.prepareStatement("commit")!!
            commit.executeUpdate()
        }
        Datos = listaDatos.toList()
        // Notificar al adaptador sobre los cambios
        notifyItemRemoved(posicion)
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val vista =
            LayoutInflater.from(parent.context).inflate(R.layout.activity_tickets_cards, parent, false)

        return ViewHolder(vista)
    }

    override fun getItemCount() = Datos.size

}
