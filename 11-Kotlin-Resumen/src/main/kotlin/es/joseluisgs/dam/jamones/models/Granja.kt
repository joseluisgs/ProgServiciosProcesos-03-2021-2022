package es.joseluisgs.dam.jamones.models

import es.joseluisgs.dam.jamones.monitor.Secadero

class Granja : Thread {
    private var secadero: Secadero
    private var cant = 0
    private var ms = 0
    private var prioridad = 0
    private var id: String? = null

    constructor(id: String?, secadero: Secadero, cant: Int, ms: Int, prioridad: Int) {
        this.id = id
        this.secadero = secadero
        this.cant = cant
        this.ms = ms
        this.prioridad = prioridad
    }

    constructor(p: Secadero) {
        secadero = p
    }

    override fun run() {
        // Cambiamos la prioridad
        priority = prioridad
        for (i in 1 until cant + 1) {
            val j = Jamon(i, id!!)
            println("Granja " + id + "-> Produzco Jamón: " + i + ": " + j.id + " de " + j.peso + "KG")
            secadero.meter(j)
        }
        try {
            sleep(ms.toLong())
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
    }
}