package es.joseluisgs.dam

import es.joseluisgs.dam.clienteservidor.client.Client
import es.joseluisgs.dam.clienteservidor.server.Server
import es.joseluisgs.dam.future.Futures
import es.joseluisgs.dam.jamones.FabricaJamones


fun main(args: Array<String>) {
    println("Hola Kotlin")

    //println("Trabajando con Futures");

    // Ejemplo con futures
    //Futures.runFuture();

    // Ejemplo productor Consumidor
    //println("Productor Consumidor");
    //FabricaJamones.runFabrica();

    // Cliente servidor
    // Para no hacerme dos apps lanzo un cliente y un servidor cada uno en un hilo
    println("Cliente Servidor");
    Server.start();
    Thread.sleep(1000);
    Client.start();

}

