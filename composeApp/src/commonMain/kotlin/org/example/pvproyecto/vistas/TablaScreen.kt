package org.example.pvproyecto.vistas

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.example.pvproyecto.models.Persona

@Composable
fun TableScreen(
    personas: List<Persona>,
    onVolver: () -> Unit,
) {
    Column(modifier = Modifier.fillMaxSize()) {

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("Personas Registradas", style = MaterialTheme.typography.titleMedium)
            OutlinedButton(onClick = onVolver) { Text("Volver") }
        }

        Spacer(Modifier.height(8.dp))

        // Cabecera
        Row(modifier = Modifier.fillMaxWidth()) {
            Text("Rut", modifier = Modifier.weight(1f))
            Text("Nombre", modifier = Modifier.weight(1f))
            Text("Ap Paterno", modifier = Modifier.weight(1f))
            Text("Ap Materno", modifier = Modifier.weight(1f))
            Text("Correo", modifier = Modifier.weight(1f))
            Text("Teléfono", modifier = Modifier.weight(1f))
            Text("Ciudad", modifier = Modifier.weight(1f))
        }

        Spacer(Modifier.height(8.dp))

        LazyColumn {
            items(personas) { p ->
                Row(modifier = Modifier.fillMaxWidth()) {
                    Text(p.rut, modifier = Modifier.weight(1f))
                    Text(p.nombre, modifier = Modifier.weight(1f))
                    Text(p.apellidoP, modifier = Modifier.weight(1f))
                    Text(p.apellidoM, modifier = Modifier.weight(1f))
                    Text(p.correo, modifier = Modifier.weight(1f))
                    Text(p.telefono, modifier = Modifier.weight(1f))
                    Text(p.ciudad, modifier = Modifier.weight(1f))
                }
                Spacer(Modifier.height(2.dp))
            }
        }
    }
}