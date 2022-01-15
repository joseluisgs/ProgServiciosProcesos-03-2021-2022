package es.joseluisgs.dam.clienteservidor.server

import es.joseluisgs.dam.clienteservidor.model.Maquina
import es.joseluisgs.dam.clienteservidor.model.Refresco
import java.net.ServerSocket
import java.net.Socket


object Server: Thread() {
    override fun run() {
        //numero de refrescos a meter
        val cantidad = 100
        //lista de caramelos
        val listaRefrescos = Maquina()
        //creo los refrescos y los añado a la lista
        for (i in 0 until cantidad) {
            listaRefrescos.add(Refresco())
        }
        val servidor: ServerSocket
        var cliente: Socket
        val puerto = 5555
        println("Servidor arrancado y esperando conexiones...")
        try {
            servidor = ServerSocket(puerto)
            while (listaRefrescos.size() > 0) { //cuando se nos acaben los refrescos paramos el servidor
                println("Esperando...")
                cliente = servidor.accept()
                println("La maquina tiene: " + listaRefrescos.size() + " refrescos")
                println("Peticion de cliente -> " + cliente.inetAddress + " --- " + cliente.port)
                println("La maquina tiene: " + listaRefrescos.size() + " refrescos")
                val gc = GestionClientes(cliente, listaRefrescos)
                gc.start()
            }
            println("Servidor finalizado...")
            servidor.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}
