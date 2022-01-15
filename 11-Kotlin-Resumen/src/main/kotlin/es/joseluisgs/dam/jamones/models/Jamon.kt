package es.joseluisgs.dam.jamones.models

class Jamon(id: Int, idGranja: String) {

    var peso: Int
    var id: Int
    var idGranja: String
    var lote = 0

    fun setIdGranaga(idGranja: String) {
        this.idGranja = idGranja
    }

    override fun toString(): String {
        return "Jamon{lote=$lote, id=$id, peso=$peso, idGranja=$idGranja}"
    }

    companion object {
        private const val idContador = 1
    }

    // Init es si queremos hacer algo más en el super que las asignaciones
    init {
        peso = Math.floor(Math.random() * (9 - 6 + 1) + 6).toInt()
        this.idGranja = idGranja

        // Si queremos que un jamon tenga un id segun la granja, se puede dar el case que Granja1 y Granja2 produzcan el jamon 3
        this.id = id
        // Si queremos que cada jamon temga un id distinto indepedientemente de la granja
        //this.id = idContador;
        //idContador++;
    }
}
