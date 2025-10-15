package org.example.pvproyecto.vistas
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.example.pvproyecto.models.Persona
import org.example.pvproyecto.vistas.components.CampoTextField

@Composable
fun FormScreen(
    onGuardar: (Persona) -> Unit,
    onVerTabla: () -> Unit,
) {
    var rut by remember { mutableStateOf("") }
    var nombre by remember { mutableStateOf("") }
    var apellidoP by remember { mutableStateOf("") }
    var apellidoM by remember { mutableStateOf("") }
    var correo by remember { mutableStateOf("") }
    var telefono by remember { mutableStateOf("") }
    var ciudad by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Registro de Personas", style = MaterialTheme.typography.titleLarge)
        Spacer(modifier = Modifier.height(16.dp))

        CampoTextField("Rut", rut, onChange = { rut = it } )
        CampoTextField("Nombre", nombre, onChange = { nombre = it })
        CampoTextField("Ap Paterno", apellidoP, onChange = { apellidoP = it })
        CampoTextField("Ap Materno", apellidoM, onChange = { apellidoM = it })
        CampoTextField("Correo", correo, onChange = { correo = it })
        CampoTextField("Teléfono", telefono, onChange = { telefono = it })
        CampoTextField("Ciudad", ciudad, onChange = { ciudad = it })

        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            Button(onClick = {
                onGuardar(
                    Persona(
                        rut = rut,
                        nombre = nombre,
                        apellidoP = apellidoP,
                        apellidoM = apellidoM,
                        correo = correo,
                        telefono = telefono,
                        ciudad = ciudad
                    )
                )
                // limpiar
                rut = ""; nombre = ""; apellidoP = ""; apellidoM = ""
                correo = ""; telefono = ""; ciudad = ""
            }) {
                Text("Guardar")
            }
            OutlinedButton(onClick = onVerTabla) {
                Text("Ver Tabla")
            }
        }
    }
}