class Karyawan(
    val nama: String,
    var gajipokok: Double
){
    fun tampilgaji(){
        val gajiFormatted = String.format("Rp %,d", gajipokok.toInt())
        println("Nama: $nama")
        println("Gaji: $gajiFormatted")
    }

    fun naikgaji(persen: Double){
        gajipokok += gajipokok * (persen / 100)
    }
}

fun main() {
    val karyawan1 = Karyawan("Billy", 9000000.0)

    println("=====Kondisi Awal=====")
    karyawan1.tampilgaji()

    karyawan1.naikgaji(15.0)

    println("\n=====Setelah Naik Gaji (15%)=====")
    karyawan1.tampilgaji()
}