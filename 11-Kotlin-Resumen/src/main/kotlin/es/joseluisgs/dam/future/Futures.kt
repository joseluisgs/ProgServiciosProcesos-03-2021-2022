package es.joseluisgs.dam.future

import java.io.*
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit
import java.util.stream.Collectors

// Hace el Singleton
object Futures {
    fun runFuture() {

        // Construyo el proceso ping
        val processBuilder = ProcessBuilder()
        // Debemos hacer ping 5 veces, por eso -c 5
        processBuilder.command("bash", "-c", "ping -c 5 github.com")

        // Como es asíncrono uso un pool
        val pool = Executors.newSingleThreadExecutor()
        // tambien puedo usar y lanzar todo aquí
        // CompletableFuture<String> futuree = CompletableFuture.supplyAsync(() -> { });
        try {
            // Lanzamos el proceso
            val ping = processBuilder.start()
            // Usamos Grep para filtrar los resultados
            val grep = Runtime.getRuntime().exec("grep min/avg/max/mdev")

            // Mientras se realiza...
            println("Iniciando medida...")

            // ProcessReadTask task = new ProcessReadTask(process.getInputStream());
            // Esperamos porque recibimos una promesa, es asincrono

            // Le decimos que se ejecute una tarea como hilo
            val future = pool.submit<String> {
                var response = ""
                // Nuestra entrada es ping
                var input = ping.inputStream
                // Le mandamos al grp
                val output = grep.outputStream

                // Para leer y escribir
                var br = BufferedReader(InputStreamReader(input))
                val pw = PrintWriter(OutputStreamWriter(output))
                // Leemos linea a linea y las pasamos al grep
                // Lo hago con la Api Stream, si no sería usar un while con readLine
                try {
                    br.lines().forEach { x: String? -> pw.println(x) }

                    //ping.waitFor();
                    br.close()
                    pw.close()
                    input.close()
                    output.close()

                    // Analizamos la salida de grep
                    input = grep.inputStream // Leemos la salida de grep
                    br = BufferedReader(InputStreamReader(input))
                    // Lo leao usando la API Stream su no seeria con un while y Readline
                    response = br.lines()
                        .collect(Collectors.toList())[0]

                    // Esperamos a que termine
                    // grep.waitFor();
                    input.close()
                    br.close()
                } catch (e: IOException) {
                    e.printStackTrace()
                }
                response
            }

            // no bloqueo, se puede usar otras tareas aquí
            // Esperamos mientras no esté terminada
            while (!future.isDone) {
                println("Procesando medición...")
                Thread.sleep(500)
            }

            // Esperamos que se cumpla la tarea 6 segundos
            val result = future[6, TimeUnit.SECONDS]

            // Imprimimos el resultado
            println("Medidas de conexión Enterpise \uD83D\uDE80: $result")
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            pool.shutdown()
        }
    }
}
