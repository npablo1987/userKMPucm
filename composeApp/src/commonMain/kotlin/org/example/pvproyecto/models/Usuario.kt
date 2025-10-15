package org.example.pvproyecto.models

class Usuario(
    // Campos heredados
    rut: String,
    nombre: String,
    apellidoP: String,
    apellidoM: String,
    correo: String,
    telefono: String,
    ciudad: String,
    // Campos adicionales
    var fechaNacimiento: String,
    var password: String,
    var nombreUsuario: String,
) : Persona(
    rut = rut,
    nombre = nombre,
    apellidoP = apellidoP,
    apellidoM = apellidoM,
    correo = correo,
    telefono = telefono,
    ciudad = ciudad
) {
    override fun toString(): String =
        super.toString() + " - user:$nombreUsuario fecha:$fechaNacimiento"
}