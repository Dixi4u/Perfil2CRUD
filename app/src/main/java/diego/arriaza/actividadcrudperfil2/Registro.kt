package diego.arriaza.actividadcrudperfil2

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import modelo.ClaseConexion
import java.util.UUID
import android.widget.Toast
import android.content.Intent

class Registro : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_registro)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.constraint)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val txtNombre = findViewById<EditText>(R.id.txtNombre)
        val txtCorreo = findViewById<EditText>(R.id.txtCorreo)
        val txtContrasena = findViewById<EditText>(R.id.txtContrasena)
        val btnRegistrarse = findViewById<Button>(R.id.btnRegistrarse)
        val btnLogIn = findViewById<Button>(R.id.btnLogIn)

        btnRegistrarse.setOnClickListener {
            GlobalScope.launch(Dispatchers.IO){
                val objConexion = ClaseConexion().cadenaConexion()

                val crearUsuario =
                    objConexion?.prepareStatement("Insert into tbUsuarios (UUID_usuario, nombre, correo, contrasena) values (?,?,?,?)")!!
                crearUsuario.setString(1, UUID.randomUUID().toString())
                crearUsuario.setString(2, txtNombre.text.toString())
                crearUsuario.setString(3, txtCorreo.text.toString())
                crearUsuario.setString(4, txtContrasena.text.toString())
                crearUsuario.executeUpdate()
                withContext(Dispatchers.Main){
                    Toast.makeText(this@Registro, "Usuario registrado", Toast.LENGTH_SHORT).show()
                    txtNombre.setText("")
                    txtCorreo.setText("")
                    txtContrasena.setText("")
                }
            }
        }

        btnLogIn.setOnClickListener {
            val LogIn = Intent(this, MainActivity::class.java)
            startActivity(LogIn)
        }
    }
}