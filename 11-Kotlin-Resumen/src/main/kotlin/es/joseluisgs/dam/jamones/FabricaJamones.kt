package es.joseluisgs.dam.jamones

import es.joseluisgs.dam.jamones.models.Granja
import es.joseluisgs.dam.jamones.models.Mensajero
import es.joseluisgs.dam.jamones.monitor.Secadero

object FabricaJamones {
        fun runFabrica() {
            val MAX_JAMONES = 30
            val INTER_GRANJA = 1000
            val TAMANO = 10
            val TAM_LOTE = 3
            val INTER_MENSA = 3000
            val PRIO1 = 4
            val PRIO2 = 8
            val secadero = Secadero(TAMANO)
            val gr1 = Granja("Granja1", secadero, MAX_JAMONES, INTER_GRANJA, PRIO1)
            val gr2 = Granja("Granja2", secadero, MAX_JAMONES, INTER_GRANJA, PRIO2)
            val em = Mensajero(secadero, 2 * MAX_JAMONES / TAM_LOTE, INTER_MENSA, TAM_LOTE)
            gr1.start()
            gr2.start()
            em.start()

            try {
                gr1.join()
                gr2.join()
                em.join()

            } catch (ex: InterruptedException) {
                System.err.println(ex.message)
            }

            System.exit(0)
    }
}
