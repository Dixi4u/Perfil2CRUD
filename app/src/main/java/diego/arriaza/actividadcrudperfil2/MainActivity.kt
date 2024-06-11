package diego.arriaza.actividadcrudperfil2

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.content.Intent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import modelo.ClaseConexion

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.txtTicketCard)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val txtCorreo = findViewById<EditText>(R.id.txtCorreoLog)
        val txtContrasena = findViewById<EditText>(R.id.txtContrasenaLog)
        val btnIniciarSesion = findViewById<Button>(R.id.btnIniciarSesion)
        val btnRegistro = findViewById<Button>(R.id.btnReg)

        btnIniciarSesion.setOnClickListener {
            val pantallaPrincipal = Intent(this, MainActivity::class.java)

            GlobalScope.launch(Dispatchers.IO) {
                val objConexion = ClaseConexion().cadenaConexion()

                val comprobarUsuario = objConexion?.prepareStatement("SELECT * FROM tbUsuarios WHERE correo = ? AND contrasena = ?")!!
                comprobarUsuario.setString(1, txtCorreo.text.toString())
                comprobarUsuario.setString(2, txtContrasena.text.toString())
                val resultado = comprobarUsuario.executeQuery()

                if (resultado.next()) {
                    startActivity(pantallaPrincipal)
                }else{
                    println("Usuario no encontrado, verifique las credenciales")
                }
            }
        }

        btnRegistro.setOnClickListener {
            val pantallaRegistro = Intent(this, Registro::class.java)
            startActivity(pantallaRegistro)
        }
    }
}