package es.joseluisgs.dam.clienteservidor.client

import es.joseluisgs.dam.clienteservidor.model.Maquina
import java.io.DataOutputStream
import java.io.ObjectInputStream
import java.net.InetAddress
import java.net.Socket
import java.util.*


object Client: Thread() {
    override fun run() {
        val direccion: InetAddress
        val servidor: Socket
        val puerto = 5555

        println("Cliente Conectado al Servidor")
        println("¿Cuantos refrescos quieres?")
        val sc = Scanner(System.`in`)
        val numRefrescosPedir: Int = sc.nextInt()
        try {
            direccion = InetAddress.getLocalHost() // dirección local
            //direccion = InetAddress.getByName("172.17.209.114");
            servidor = Socket(direccion, puerto) //equivalente al send
            println("Conectado a Servidor ...")

            //le envio el numero de refrescos que quiero
            val numRefrescos = DataOutputStream(servidor.getOutputStream())
            numRefrescos.writeInt(numRefrescosPedir)

            //recibo un array con las lista de refrescos
            val listaRe = ObjectInputStream(servidor.getInputStream())
            val listaRefrescos: Maquina = listaRe.readObject() as Maquina
            if (listaRefrescos.size() > 0) {
                for (i in 0 until listaRefrescos.size()) {
                    System.out.println("Ha llegado un refresco de -> " + listaRefrescos.get(i).nombre)
                }
            } else {
                println("Ya no hay más refrescos")
            }
            numRefrescos.close()
            listaRe.close()
            servidor.close()
            println("Desconectado ...")
        } catch (e: Exception) {
            System.err.println("Servidor desconectado")
            //e.printStackTrace();
        }
    }
}
