package es.joseluisgs.dam.clienteservidor.model

import java.io.Serializable

class Refresco : Serializable {
    // si la declaro como var ya lleva el get and set
    var nombre: String? = null

    init {
        nombre = when ((Math.random() * 7).toInt()) {
            0 -> "Coca Cola"
            1 -> "Sprite"
            2 -> "Fanta"
            3 -> "Tónica"
            4 -> "Nestea"
            5 -> "Cerveza"
            6 -> "Zumo"
            else -> "Agua"
        }
    }
}
