package org.example.pvproyecto

import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowPosition
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState

fun main() = application {
    val state = rememberWindowState(
        width = 1920.dp,
        height = 1080.dp,
        position = WindowPosition(Alignment.Center) // opcional: centrar
    )

    Window(
        onCloseRequest = ::exitApplication,
        title = "pvproyecto",
        state = state
    ) {
        App()
    }
}
