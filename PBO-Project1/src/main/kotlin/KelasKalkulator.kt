//class Kalkulator {
//
//    fun tambah(a: Int, b: Int): Int {
//        return a + b
//    }
//
//    fun kurang(a: Int, b: Int): Int {
//        return a - b
//    }
//
//    fun kali(a: Int, b: Int): Int {
//        return a * b
//    }
//
//    fun bagi(a: Int, b: Int): Int {
//        if (b == 0) {
//            println("Peringatan: Tidak bisa melakukan pembagian dengan angka nol!")
//            return 0
//        }
//        return a / b
//    }
//
//    fun tampilkanOperasi(a: Int, b: Int, operator: Char) {
//        val hasil = when (operator) {
//            '+' -> tambah(a, b)
//            '-' -> kurang(a, b)
//            '*' -> kali(a, b)
//            '/' -> bagi(a, b)
//            else -> {
//                println("Peringatan: Operator '$operator' tidak valid!")
//                return
//            }
//        }
//        println("$a $operator $b = $hasil")
//    }
//}
//
//// Fungsi main untuk menguji kelas Kalkulator dengan Int
//fun main() {
//    val kalkulator = Kalkulator()
//
//    println("=== Pengujian Metode Matematika (Int) ===")
//    println("Penjumlahan : " + kalkulator.tambah(10, 5))
//    println("Pengurangan : " + kalkulator.kurang(10, 5))
//    println("Perkalian   : " + kalkulator.kali(10, 5))
//
//    // Hasil pembagian integer akan membuang angka di belakang koma
//    println("Pembagian   : " + kalkulator.bagi(10, 3)) // Hasilnya 3 (bukan 3.33)
//
//    // Uji pembagian dengan angka nol
//    println("Pembagian 0 : " + kalkulator.bagi(10, 0))
//
//    println("\n=== Pengujian tampilkanOperasi() ===")
//    kalkulator.tampilkanOperasi(12, 4, '+')
//    kalkulator.tampilkanOperasi(15, 3, '-')
//    kalkulator.tampilkanOperasi(6, 7, '*')
//    kalkulator.tampilkanOperasi(20, 5, '/')
//    kalkulator.tampilkanOperasi(8, 0, '/') // Pembagian dengan nol
//}


//versi double
class Kalkulator {

    fun tambah(a: Double, b: Double): Double {
        return a + b
    }

    fun kurang(a: Double, b: Double): Double {
        return a - b
    }

    fun kali(a: Double, b: Double): Double {
        return a * b
    }

    fun bagi(a: Double, b: Double): Double {
        if (b == 0.0) {
            println("Peringatan: Tidak bisa melakukan pembagian dengan angka nol!")
            return 0.0
        }
        return a / b
    }

    fun tampilkanOperasi(a: Double, b: Double, operator: Char) {
        val hasil = when (operator) {
            '+' -> tambah(a, b)
            '-' -> kurang(a, b)
            '*' -> kali(a, b)
            '/' -> bagi(a, b)
            else -> {
                println("Peringatan: Operator '$operator' tidak valid!")
                return
            }
        }
        println("$a $operator $b = $hasil")
    }
}

fun main() {
    val kalkulator = Kalkulator()

    println("=== Pengujian Metode Matematika (Double) ===")
    println("Penjumlahan : " + kalkulator.tambah(10.0, 5.0))
    println("Pengurangan : " + kalkulator.kurang(10.0, 5.0))
    println("Perkalian   : " + kalkulator.kali(10.0, 5.0))

    println("Pembagian   : " + kalkulator.bagi(10.0, 3.0))

    println("Pembagian 0 : " + kalkulator.bagi(10.0, 0.0))

    println("\n=== Pengujian tampilkanOperasi() ===")
    kalkulator.tampilkanOperasi(12.0, 4.0, '+')
    kalkulator.tampilkanOperasi(15.5, 3.2, '-')
    kalkulator.tampilkanOperasi(6.5, 2.0, '*')
    kalkulator.tampilkanOperasi(20.0, 5.0, '/')
    kalkulator.tampilkanOperasi(8.0, 0.0, '/')
}