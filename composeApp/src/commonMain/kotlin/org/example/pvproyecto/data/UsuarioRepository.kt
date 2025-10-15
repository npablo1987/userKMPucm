package org.example.pvproyecto.data

import androidx.compose.runtime.mutableStateListOf
import org.example.pvproyecto.models.Usuario

/**
 * Repositorio simple en memoria. Mantiene la lista de usuarios y utilidades básicas.
 */
object UsuarioRepository {
    val usuarios = mutableStateListOf<Usuario>()

    fun add(usuario: Usuario): Boolean {
        if (existsNombreUsuario(usuario.nombreUsuario)) return false
        usuarios.add(usuario)
        return true
    }

    fun existsNombreUsuario(nombreUsuario: String): Boolean =
        usuarios.any { it.nombreUsuario.equals(nombreUsuario, ignoreCase = true) }

    fun login(nombreUsuario: String, password: String): Usuario? =
        usuarios.firstOrNull { it.nombreUsuario == nombreUsuario && it.password == password }
}