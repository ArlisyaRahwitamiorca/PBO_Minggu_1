class persegipanjang(
    var panjang: Double,
    var lebar: Double
){
    fun hitungluas(): Double{
        return panjang * lebar
    }

    fun ubahukuran(panjangbaru: Double, lebarbaru: Double){
        panjang = panjangbaru
        lebar = lebarbaru
    }

    fun isSquare(): Boolean {
        return panjang == lebar
    }
}

fun main(){
    val pp = persegipanjang(10.0, 5.0)

    println("=====Kondisi Awal=====")
    println("Panjang : ${pp.panjang}")
    println("Lebar   : ${pp.lebar}")
    println("Luas    : ${pp.hitungluas()}")
    println("Apakah persegi (isSquare)? ${pp.isSquare()})")

    println("\n---------------------------------\n")

    pp.ubahukuran(15.0, 15.0)
    println("=====Setelah Ditest Ukuran Sama (15 x 15)=====")
    println("Panjang : ${pp.panjang}")
    println("Lebar   : ${pp.lebar}")
    println("Luas    : ${pp.hitungluas()}")
    println("Apakah persegi (isSquare)? ${pp.isSquare()})")
}