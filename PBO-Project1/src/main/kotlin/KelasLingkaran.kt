import kotlin.math.PI

class Lingkaran(var jariJari: Double) {

    fun luas(): Double {
        return PI * jariJari * jariJari
    }

    fun keliling(): Double {
        return 2 * PI * jariJari
    }

    fun tampilkan() {
        val luasFormatted = String.format("%.2f", luas())
        val kelilingFormatted = String.format("%.2f", keliling())

        println("Jari-Jari : $jariJari")
        println("Luas      : $luasFormatted")
        println("Keliling  : $kelilingFormatted")
    }
}

fun main() {
    val lingkaran = Lingkaran(7.0)

    println("===== INFORMASI LINGKARAN =====")
    lingkaran.tampilkan()
}