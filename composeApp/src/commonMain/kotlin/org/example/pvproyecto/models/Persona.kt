package org.example.pvproyecto.models

open class Persona(
    var rut: String,
    var nombre: String,
    var apellidoP: String,
    var apellidoM: String,
    var correo: String,
    var telefono: String,
    var ciudad: String,
) {
    override
    fun toString(): String = "$rut - $nombre $apellidoP $correo $telefono $ciudad"

}