package diego.arriaza.actividadcrudperfil2

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class detalleTicket : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_detalle_ticket)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        //Recibir los valores
        val UUIDrecibido = intent.getStringExtra("UUID_Ticket")
        val txtTitulorecibido = intent.getStringExtra("Titulo")
        val txtDescripcionrecibido = intent.getStringExtra("Descripcion")
        val txtAutorrecibido = intent.getStringExtra("Autor")
        val txtEmailrecibido = intent.getStringExtra("Email")
        val txtCreacionrecibido = intent.getStringExtra("Fecha_Creacion")
        val txtEstadorecibido = intent.getStringExtra("Estado")
        val txtFinalizacionrecibido = intent.getStringExtra("Fecha_FInalizacion")

        //llamo los elementos
        val txtUUID = findViewById<TextView>(R.id.txtUUIDdet)
        val txtTitulo = findViewById<TextView>(R.id.txtNombreDet)
        val txtDescripcion = findViewById<TextView>(R.id.txtDescripcionDet)
        val txtAutor = findViewById<TextView>(R.id.txtAutorDet)
        val txtEmail = findViewById<TextView>(R.id.txtEmailDet)
        val txtCreacion = findViewById<TextView>(R.id.txtCreacionDet)
        val txtEstado = findViewById<TextView>(R.id.txtEstadoDet)
        val txtFinalizacion = findViewById<TextView>(R.id.txtFInalizacionDet)

        //Asigarle los datos recibidos a mis TextView
        txtUUID.text = UUIDrecibido
        txtTitulo.text = txtTitulorecibido
        txtDescripcion.text = txtDescripcionrecibido
        txtAutor.text = txtAutorrecibido
        txtEmail.text = txtEmailrecibido
        txtCreacion.text = txtCreacionrecibido
        txtEstado.text = txtEstadorecibido
        txtFinalizacion.text = txtFinalizacionrecibido
    }
}