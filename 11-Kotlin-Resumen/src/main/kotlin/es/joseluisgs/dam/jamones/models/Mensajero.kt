package es.joseluisgs.dam.jamones.models

import es.joseluisgs.dam.jamones.monitor.Secadero


class Mensajero(secadero: Secadero, cant: Int, ms: Int, tam: Int) : Thread() {
    private val secadero: Secadero
    private val cant: Int
    private val ms: Int
    private val tam: Int
    override fun run() {
        val misJamones = ArrayList<Jamon>()
        for (i in 1 until cant + 1) {
            // Mensajero saca 3 jamones
            for (k in 0 until tam) {
                val j: Jamon? = secadero.sacar()
                j!!.lote = i // LE asigno el lote
                misJamones.add(j)
                println("Mensajero-> Paquete Lote: " + i + ": empaqueto Jamon: " + j.id + " " + j.peso + "KG de: " + j.idGranja)
            }
            imprimirLote(misJamones)
            misJamones.clear()
            try {
                sleep(ms.toLong())
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }
        }
    }

    private fun imprimirLote(misJamones: ArrayList<Jamon>) {
        println("\t->Imprimiendo Lote")
        for (j in misJamones) {
            println("\t->$j")
        }
        // O con foreach
       /* misJamones.forEach {
            println("\t->$it")
        }*/
    }

    init {
        this.secadero = secadero
        this.cant = cant
        this.ms = ms
        this.tam = tam
    }
}
