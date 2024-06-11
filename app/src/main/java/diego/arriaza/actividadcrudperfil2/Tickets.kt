package diego.arriaza.actividadcrudperfil2

import RecyclerViewHelper.Adaptador
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import modelo.ClaseConexion
import modelo.dataClassTicket
import java.util.UUID

class Tickets : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_tickets)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.txtTicketCard)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val txtNombret = findViewById<EditText>(R.id.txtNombreTic)
        val txtDescripcion = findViewById<EditText>(R.id.txtDescripcion)
        val txtAutor = findViewById<EditText>(R.id.txtAutor)
        val txtEmail = findViewById<EditText>(R.id.txtEmail)
        val txtCreacion = findViewById<EditText>(R.id.txtCreacion)
        val txtEstado = findViewById<EditText>(R.id.txtEstado)
        val txtFinalizacion = findViewById<EditText>(R.id.txtFinalizacion)
        val btnAgregar = findViewById<Button>(R.id.btnAgregar)
        val rcvTickets = findViewById<RecyclerView>(R.id.rcvTicket)

        rcvTickets.layoutManager = LinearLayoutManager(this)

        fun obtenerDatos(): List<dataClassTicket> {
            //1- Creo un objeto de la clase conexion
            val objConexion = ClaseConexion().cadenaConexion()

            //2- Creo un Statement
            val statement = objConexion?.createStatement()
            val resulSet = statement?.executeQuery("select * from Ticket")!!

            val ticket = mutableListOf<dataClassTicket>()

            while (resulSet.next()) {
                val uuid = resulSet.getString("UUID_Ticket")
                val Titulo = resulSet.getString("Titulo")
                val Descripcion = resulSet.getString("Descripcion")
                val Autor = resulSet.getString("Autor")
                val Email = resulSet.getString("Email")
                val Creacion = resulSet.getString("Fecha_Creacion")
                val Estado = resulSet.getString("Estado")
                val Finalizacion = resulSet.getString("Fecha_Finalizacion")

                val ticket = dataClassTicket(uuid, Titulo, Descripcion, Autor, Email, Creacion, Estado, Finalizacion)
                ticket.add(ticket)
            }
            return ticket
        }

        CoroutineScope(Dispatchers.IO).launch {
            val Tickets = obtenerDatos()
            withContext(Dispatchers.Main) {
                val adapter = Adaptador(Tickets)
                rcvTickets.adapter = adapter
            }
        }

        btnAgregar.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch{
                val objConexion = ClaseConexion().cadenaConexion()

                val addTicket = objConexion?.prepareStatement("insert into Ticket(UUID_Ticket,Titulo,Descripcion,Autor,Email,Fecha_Creacion,Estado,Fecha_Finalizacion) values(?, ?, ?, ?, ?, ?, ?, ?)")
                addTicket?.setString(1, UUID.randomUUID().toString())
                addTicket?.setString(2, txtNombret.text.toString())
                addTicket?.setString(3, txtDescripcion.text.toString())
                addTicket?.setString(4, txtAutor.text.toString())
                addTicket?.setString(5, txtEmail.text.toString())
                addTicket?.setString(6, txtCreacion.text.toString())
                addTicket?.setString(7, txtEstado.text.toString())
                addTicket?.setString(8, txtFinalizacion.text.toString())

                val nuevosTickets = obtenerDatos()
                withContext(Dispatchers.Main) {
                    (rcvTickets.adapter as? Adaptador)?.actualizarLista(nuevosTickets)
                }


            }
        }
    }
}