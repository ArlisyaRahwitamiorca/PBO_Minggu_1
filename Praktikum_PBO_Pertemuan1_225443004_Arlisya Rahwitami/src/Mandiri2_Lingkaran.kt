import kotlin.math.PI

class Lingkaran(var JariJari: Double) {
    fun hitungLuas(): Double {
        return Math.PI * JariJari * JariJari
    }
    fun hitungKeliling(): Double {
        return 2 * Math.PI * JariJari
    }
}

fun main() {
    val lingkaran1 = Lingkaran(7.0)
    val lingkaran2 = Lingkaran(14.0)

    println("=====Lingkaran 1 (r = ${lingkaran1.JariJari})=====")
    val luas1Formatted = String.format("%.2f", lingkaran1.hitungLuas())
    val keliling1Formatted = String.format("%.2f", lingkaran1.hitungKeliling())
    println("Jari-Jari : ${lingkaran1.JariJari}")
    println("Luas      : $luas1Formatted")
    println("keliling  : $keliling1Formatted")

    println()

    println("===== Lingkaran 2 (r = ${lingkaran2.JariJari})=====")
    val luas2Formatted = String.format("%.2f", lingkaran2.hitungLuas())
    val keliling2Formatted = String.format("%.2f", lingkaran2.hitungKeliling())
    println("Jari-Jari : ${lingkaran2.JariJari}")
    println("Luas      : $luas2Formatted")
    println("keliling  : $keliling2Formatted")
}