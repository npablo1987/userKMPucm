package org.example.pvproyecto

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.example.pvproyecto.models.Persona
import org.example.pvproyecto.vistas.FormScreen
import org.example.pvproyecto.vistas.LoginScreen
import org.example.pvproyecto.vistas.RegisterUserScreen
import org.example.pvproyecto.vistas.TableScreen
import org.jetbrains.compose.ui.tooling.preview.Preview

import pvproyecto.composeapp.generated.resources.Res
import pvproyecto.composeapp.generated.resources.compose_multiplatform

private enum class Pantalla { LOGIN, REGISTRO, FORMULARIO, TABLA }

@Composable
@Preview
fun App() {
    var pantalla by remember { mutableStateOf(Pantalla.LOGIN) }
    val personas: SnapshotStateList<Persona> = remember { mutableStateListOf<Persona>() }

    MaterialTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                when (pantalla) {
                    Pantalla.LOGIN -> LoginScreen(
                        onLoginOk = { pantalla = Pantalla.FORMULARIO },
                        onIrARegistrar = { pantalla = Pantalla.REGISTRO }
                    )
                    Pantalla.REGISTRO -> RegisterUserScreen(
                        onRegistradoOK = { pantalla = Pantalla.LOGIN },
                        onCancelar = { pantalla = Pantalla.LOGIN }
                    )
                    Pantalla.FORMULARIO -> FormScreen(
                        onGuardar = { personas.add(it) },
                        onVerTabla = { pantalla = Pantalla.TABLA }
                    )
                    Pantalla.TABLA -> TableScreen(
                        personas = personas,
                        onVolver = { pantalla = Pantalla.FORMULARIO }
                    )
                }
            }
        }
    }
}