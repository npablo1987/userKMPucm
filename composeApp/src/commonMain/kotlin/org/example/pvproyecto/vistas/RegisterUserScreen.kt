package org.example.pvproyecto.vistas
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.example.pvproyecto.data.UsuarioRepository
import org.example.pvproyecto.models.Usuario
import org.example.pvproyecto.vistas.components.CampoPasswordField
import org.example.pvproyecto.vistas.components.CampoTextField


@Composable
fun RegisterUserScreen(
    onRegistradoOK: () -> Unit,
    onCancelar: () -> Unit
) {
    // Campos heredados (Persona)
    var rut by remember { mutableStateOf("") }
    var nombre by remember { mutableStateOf("") }
    var apellidoP by remember { mutableStateOf("") }
    var apellidoM by remember { mutableStateOf("") }
    var correo by remember { mutableStateOf("") }
    var telefono by remember { mutableStateOf("") }
    var ciudad by remember { mutableStateOf("") }

    // Campos extra (Usuario)
    var fechaNacimiento by remember { mutableStateOf("") }
    var nombreUsuario by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    var error by remember { mutableStateOf<String?>(null) }
    var ok by remember { mutableStateOf<String?>(null) }

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Registrar usuario", style = MaterialTheme.typography.titleLarge)
        Spacer(Modifier.height(16.dp))

        // Persona
        CampoTextField("RUT", rut, onChange = { rut = it })
        CampoTextField("Nombre", nombre, onChange = { nombre = it })
        CampoTextField("Apellido Paterno", apellidoP, onChange = { apellidoP = it })
        CampoTextField("Apellido Materno", apellidoM, onChange = { apellidoM = it })
        CampoTextField("Correo", correo, onChange = { correo = it })
        CampoTextField("Teléfono", telefono, onChange = { telefono = it })
        CampoTextField("Ciudad", ciudad, onChange = { ciudad = it })

        // Usuario (extra)
        CampoTextField("Fecha Nacimiento (YYYY-MM-DD)", fechaNacimiento , onChange = { fechaNacimiento = it })
        CampoTextField("Nombre de usuario", nombreUsuario, onChange = { nombreUsuario = it })
        CampoPasswordField("Contraseña", password, onChange = { password = it })

        error?.let {
            Text(it, color = MaterialTheme.colorScheme.error, style = MaterialTheme.typography.bodyMedium)
            Spacer(Modifier.height(8.dp))
        }
        ok?.let {
            Text(it, color = MaterialTheme.colorScheme.primary, style = MaterialTheme.typography.bodyMedium)
            Spacer(Modifier.height(8.dp))
        }

        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            Button(onClick = {
                // Validaciones mínimas
                if (nombreUsuario.isBlank() || password.isBlank() || nombre.isBlank()) {
                    error = "Nombre, nombre de usuario y contraseña son obligatorios."
                    ok = null
                    return@Button
                }
                if (UsuarioRepository.existsNombreUsuario(nombreUsuario.trim())) {
                    error = "El nombre de usuario ya existe."
                    ok = null
                    return@Button
                }

                val u = Usuario(
                    rut = rut.trim(),
                    nombre = nombre.trim(),
                    apellidoP = apellidoP.trim(),
                    apellidoM = apellidoM.trim(),
                    correo = correo.trim(),
                    telefono = telefono.trim(),
                    ciudad = ciudad.trim(),
                    fechaNacimiento = fechaNacimiento.trim(),
                    password = password,
                    nombreUsuario = nombreUsuario.trim()
                )
                val agregado = UsuarioRepository.add(u)
                if (agregado) {
                    error = null
                    ok = "Usuario registrado correctamente."
                    onRegistradoOK()
                } else {
                    error = "No se pudo registrar (usuario existente)."
                    ok = null
                }
            }) {
                Text("Registrar")
            }
            OutlinedButton(onClick = onCancelar) {
                Text("Cancelar")
            }
        }
    }
}