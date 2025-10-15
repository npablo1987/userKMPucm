package org.example.pvproyecto.vistas

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.example.pvproyecto.data.UsuarioRepository
import org.example.pvproyecto.vistas.components.CampoPasswordField
import org.example.pvproyecto.vistas.components.CampoTextField

@Composable
fun LoginScreen(
    onLoginOk: () -> Unit,
    onIrARegistrar: () -> Unit
) {
    var nombreUsuario by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var error by remember { mutableStateOf<String?>(null) }

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text("Iniciar sesión", style = MaterialTheme.typography.titleLarge)
        Spacer(Modifier.height(16.dp))

        CampoTextField("Nombre de usuario", nombreUsuario, onChange =  { nombreUsuario = it } )
        CampoPasswordField("Contraseña", password, { password = it } )

        error?.let {
            Text(it, color = MaterialTheme.colorScheme.error, style = MaterialTheme.typography.bodyMedium)
            Spacer(Modifier.height(8.dp))
        }

        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            Button(onClick = {
                val user = UsuarioRepository.login(nombreUsuario.trim(), password)
                if (user != null) {
                    error = null
                    onLoginOk()
                } else {
                    error = "Credenciales inválidas."
                }
            }) {
                Text("Ingresar")
            }
            OutlinedButton(onClick = onIrARegistrar) {
                Text("Registrar")
            }
        }
    }
}