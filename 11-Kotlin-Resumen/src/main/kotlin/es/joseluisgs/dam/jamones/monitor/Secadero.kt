package es.joseluisgs.dam.jamones.monitor

import es.joseluisgs.dam.jamones.models.Jamon
import java.util.*


// https://titanwolf.org/Network/Articles/Article?AID=c06e53f3-2635-4b71-9229-c68f2b1fe7f9
class Secadero(private val maxJamones: Int) : LinkedList<Jamon?>() {
    // El monitor
    private val lock = java.lang.Object()

    private var jamonDisponible = false
    private var s: Jamon? = null

    fun sacar(): Jamon? = synchronized(lock){
        while (this.size == 0) {
            try {
                // Si no hay hay sacar, esperamos,
                lock.wait()
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }
        }
        s = this.removeFirst() // Saco el primero
        println("\t*El secadero tiene: " + this.size)
        jamonDisponible = false
        // Activamos
        lock.notifyAll()
        return s // DEvolvemos el jamon
    }

    fun meter(s: Jamon?) = synchronized(lock) {
        while (this.size == maxJamones) {    // Condición de memoria limitada
            try {
                // Si no hay que producir esperamos
                lock.wait()
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }
        }
        addLast(s) //Metemos al final
        println("\t\tEl secadero tiene: " + this.size)
        jamonDisponible = true
        // Ya hay cantidas a consumir, activamos.
        lock.notifyAll()
    }
}
