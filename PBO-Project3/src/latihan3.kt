//shapes
//import kotlin.math.PI
//
////Superclass: Shape
//open class Shape(val name: String) {
//    open fun luas(): Double = 0.0
//
//    fun tampilkanInfo() {
//        println("Bentuk: $name")
//        println("Luas  : ${luas()}")
//        println("-------------------")
//    }
//}
//
////Subclass: Rectangle
//class Rectangle(val panjang: Double,
//                val lebar: Double) : Shape("Rectangle") {
//    override fun luas(): Double = panjang * lebar
//}
//
////Subclass: Circle
//class Circle(val jariJari: Double) : Shape("Circle") {
//    override fun luas(): Double = PI * jariJari * jariJari
//}
//
////Subclass: Triangle
//class Triangle(val alas: Double,
//               val tinggi: Double) : Shape("Triangle") {
//    override fun luas(): Double = 0.5 * alas * tinggi
//}
//
////Fungsi Utama
//fun main() {
//    val persegiPanjang = Rectangle(10.0, 6.0)
//    val lingkaran = Circle(7.0)
//    val segitiga = Triangle(6.0, 8.0)
//
//    println("======BENTUK======")
//
//    persegiPanjang.tampilkanInfo()
//    lingkaran.tampilkanInfo()
//    segitiga.tampilkanInfo()
//}


//Kelas Induk: Product
//open class Product(
//    val id: String,
//    val name: String,
//    val price: Double
//){
//    open fun getDiscountPrice(): Double = price
//
//    fun tampilkanInfo() {
//        println("ID             : $id")
//        println("Nama Produk    : $name")
//        println("Harga Normal   : Rp $price")
//        println("Harga Diskon   : Rp ${getDiscountPrice()}")
//        println("----------------------------------")
//    }
//}
//
////Subclass: ElectronicProduct
//class ElectronicProduct(
//    id: String,
//    name: String,
//    price: Double,
//    val warrantyMonths: Int
//) : Product(id, name, price) {
//    override fun getDiscountPrice(): Double {
//        return if (warrantyMonths > 12) price * 0.90 else price
//    }
//}
//
////Subclass: FoodProduct
//class FoodProduct(
//    id: String,
//    name: String,
//    price: Double,
//    val expiryDate: String,
//    val isNearExpiry: Boolean
//) : Product(id, name, price) {
//    override fun getDiscountPrice(): Double {
//        return if (isNearExpiry) price * 0.80 else price
//    }
//}
//
////Sublass
//class ClothingProduct(
//    id: String,
//    name: String,
//    price: Double,
//    val size: String,
//    val material: String
//) : Product(id, name, price) {
//    override fun getDiscountPrice(): Double {
//        return if (size.equals("XL", ignoreCase = true)) price * 0.85 else price
//    }
//}
//
////Fungsi Utama
//fun main() {
//    val laptop = ElectronicProduct("E01", "Earphone", 150000.0, 24)
//    val susu = FoodProduct("F01", "Susu UHT", 20000.0, "2026-08-20", true)
//    val jaket = ClothingProduct("C01", "Jaket Parasut", 300000.0, "XL", "Kanvas")
//
//    println("===== DAFTAR PRODUK & DISKON =====")
//    laptop.tampilkanInfo()
//    susu.tampilkanInfo()
//    jaket.tampilkanInfo()
//}

//kendaraan
//open class Vehicle(
//    val brand: String,
//    val model: String,
//    val year: Int,
//    val price: Double
//) {
//    var isSold: Boolean = false
//        private set
//
//    open fun calculateTax(): Double = price * 0.10
//
//    fun sell(): Boolean = if (!isSold) { isSold = true; true } else false
//
//    fun isAvailable(): Boolean = !isSold
//
//    open fun displayInfo(): String {
//        val statusStr = if (isSold) "Terjual" else "Tersedia"
//        return String.format(
//            "| %-10s | %-12s | %-4d | Rp %12.0f | Rp %10.0f | %-9s |",
//            brand, model, year, price, calculateTax(), statusStr
//        )
//    }
//}
//
//open class Car(
//    brand: String, model: String, year: Int, price: Double,
//    val numberOfDoors: Int, val fuelType: String
//) : Vehicle(brand, model, year, price) {
//    override fun calculateTax(): Double = price * 0.12
//}
//
//class Motorcycle(
//    brand: String, model: String, year: Int, price: Double,
//    val engineCapacity: Int, val type: String
//) : Vehicle(brand, model, year, price) {
//    override fun calculateTax(): Double = price * 0.05
//}
//
//class Truck(
//    brand: String, model: String, year: Int, price: Double,
//    numberOfDoors: Int, fuelType: String,
//    val loadCapacity: Double, val numberOfAxles: Int
//) : Car(brand, model, year, price, numberOfDoors, fuelType) {
//    override fun calculateTax(): Double = price * 0.15
//}
//
//class Dealership(val name: String) {
//    private val vehicles = mutableListOf<Vehicle>()
//
//    fun addVehicle(vehicle: Vehicle) {
//        vehicles.add(vehicle)
//    }
//
//    fun findVehicle(brand: String, model: String): Vehicle? =
//        vehicles.find { it.brand.equals(brand, ignoreCase = true) && it.model.equals(model, ignoreCase = true) }
//
//    fun sellVehicle(brand: String, model: String): Boolean =
//        findVehicle(brand, model)?.sell() ?: false
//
//    fun getAvailableVehicles(): List<Vehicle> = vehicles.filter { it.isAvailable() }
//
//    fun getSoldVehicles(): List<Vehicle> = vehicles.filter { it.isSold }
//
//    private fun printTableHeader() {
//        println("+------------+--------------+------+----------------+---------------+-----------+")
//        println("| Merek      | Model        | Tahun| Harga          | Pajak         | Status    |")
//        println("+------------+--------------+------+----------------+---------------+-----------+")
//    }
//
//    private fun printTableFooter() {
//        println("+------------+--------------+------+----------------+---------------+-----------+")
//    }
//
//    fun displayAllVehicles() {
//        println("\n>>> DAFTAR SELURUH KENDARAAN — $name")
//        printTableHeader()
//        if (vehicles.isEmpty()) {
//            println("| Tidak ada data kendaraan.                                                    |")
//        } else {
//            vehicles.forEach { println(it.displayInfo()) }
//        }
//        printTableFooter()
//    }
//
//    fun displayAvailableVehicles() {
//        println("\n>>> DAFTAR KENDARAAN TERSEDIA")
//        printTableHeader()
//        val available = getAvailableVehicles()
//        if (available.isEmpty()) {
//            println("| Tidak ada kendaraan tersedia saat ini.                                       |")
//        } else {
//            available.forEach { println(it.displayInfo()) }
//        }
//        printTableFooter()
//    }
//
//    fun getTotalRevenue(): Double = getSoldVehicles().sumOf { it.price }
//}
//
//fun main() {
//    println("=======================================================================================")
//    println("                                  DEMO DEALER KENDARAAN                                ")
//    println("=======================================================================================")
//
//    val dealer = Dealership("Dealer Motor Jaya")
//
//    // Tambahkan 2 mobil, 2 motor, 2 truk
//    dealer.addVehicle(Car("BMW", "X7", 2018, 2_500_000_000.0, 5, "Bensin"))
//    dealer.addVehicle(Car("Hyundai", "Ioniq 9", 2023, 850_000_000.0, 5, "Listrik"))
//    dealer.addVehicle(Motorcycle("Beat", "Karbu", 2009, 15_000_000.0, 150, "Matic"))
//    dealer.addVehicle(Motorcycle("Yamaha", "NMAX", 2023, 32_000_000.0, 155, "Matic"))
//    dealer.addVehicle(Truck("Mitsubishi", "Fuso", 2020, 500_000_000.0, 2, "Diesel", 5.0, 2))
//    dealer.addVehicle(Truck("Hino", "Ranger", 2026, 1_000_000_000.0, 2, "Diesel", 12.0, 3))
//
//    // Tampilkan semua kendaraan awal
//    dealer.displayAllVehicles()
//
//    println("\n--- PROSES PENJUALAN KENDARAAN ---")
//    dealer.sellVehicle("BMW", "X7")
//    dealer.sellVehicle("Yamaha", "NMAX")
//
//    // Tampilkan kendaraan yang masih tersedia
//    dealer.displayAvailableVehicles()
//
//    println("\n=======================================================================================")
//    println(String.format("TOTAL PENDAPATAN DEALER : Rp %,15.0f", dealer.getTotalRevenue()))
//    println("=======================================================================================")
//}


////KELAS PRODUK
open class Product(
    val id: String,
    val nama: String,
    val harga: Double,
    var stok: Int
) {

    open fun hitungDiskon(): Double = 0.0

    //Harga setelah diskon (harga-diskon)
    fun hitungHargaJual(): Double = harga - hitungDiskon()

    //Mengurangi stok
    fun kurangiStok(jumlah: Int): Boolean {
        if (stok >= jumlah) {
            stok -= jumlah
            return true
        }
        return false
    }

    //Menampilkan info produk
    open fun tampilkanInfo(): String {
        return """
            ID          : $id
            Nama        : $nama
            Harga       : Rp${harga.toInt()}
            Diskon      : Rp${hitungDiskon().toInt()}
            Harga Jual  : Rp${hitungHargaJual().toInt()}
            Stok        : $stok
            -------------------------------------------
        """.trimIndent()
    }
}

//Subclass: ELEKTRONIK
class Elektronik(
    id: String, nama: String, harga: Double, stok: Int,
    val garansi: Int, val daya: Int
) : Product(id, nama, harga, stok) {

    // Diskon 10% jika garansi > 12 bulan
    override fun hitungDiskon(): Double {
        return if (garansi > 12) harga * 0.10 else 0.0
    }
}

//Subclass: MAKANAN
class Makanan(
    id: String,
    nama: String,
    harga: Double,
    stok: Int,
    val sisaHariKadaluarsa: Int, val berat: Double
) : Product(id, nama, harga, stok) {

    // Diskon 20% jika tanggal kadaluarsa kurang dari 7 hari
    override fun hitungDiskon(): Double {
        return if (sisaHariKadaluarsa < 7) harga * 0.20 else 0.0
    }
}

//Subclass: PAKAIAN
class Pakaian(
    id: String, nama: String, harga: Double, stok: Int,
    val ukuran: String, val bahan: String
) : Product(id, nama, harga, stok) {

    // Diskon 15% jika ukuran XL
    override fun hitungDiskon(): Double {
        return if (ukuran.uppercase() == "XL") harga * 0.15 else 0.0
    }
}


//KELAS TOKO
class Toko(val nama: String) {
    private val daftarProduk = mutableListOf<Product>()

    fun tambahProduk(produk: Product) {
        daftarProduk.add(produk)
    }

    fun cariProduk(keyword: String): List<Product> {
        return daftarProduk.filter { it.nama.contains(keyword, ignoreCase = true) }
    }

    fun tampilkanSemuaProduk() {
        for (produk in daftarProduk) {
            println(produk.tampilkanInfo())
        }
    }

    fun tampilkanProdukByKategori(kategori: String) {
        for (produk in daftarProduk) {
            val sesuai = when (kategori.lowercase()) {
                "elektronik" -> produk is Elektronik
                "makanan" -> produk is Makanan
                "pakaian" -> produk is Pakaian
                else -> false
            }
            if (sesuai) println(produk.tampilkanInfo())
        }
    }

    fun getDaftarProduk(): List<Product> = daftarProduk
}

//MAIN FUNCTION
fun main() {
    val toko = Toko("Toko Serba Ada")

    // Tambah 6 Produk (2 per kategori)
    val e1 = Elektronik("EL1", "Laptop", 15000000.0, 10, garansi = 24, daya = 180)
    val e2 = Elektronik("EL2", "Earphone Wireless", 250000.0, 10, garansi = 6, daya = 5)

    val m1 = Makanan("MA1", "Bolen Lilit", 8000.0, 20, sisaHariKadaluarsa = 3, berat = 250.0)
    val m2 = Makanan("MA2", "Susu UHT", 20000.0, 30, sisaHariKadaluarsa = 30, berat = 1000.0)

    val p1 = Pakaian("PA1", "Kaos Polos XL", 80000.0, 10, ukuran = "XL", bahan = "Katun")
    val p2 = Pakaian("PA2", "Celana Jeans Biru", 150000.0, 8, ukuran = "M", bahan = "Jeans")

    toko.tambahProduk(e1)
    toko.tambahProduk(e2)
    toko.tambahProduk(m1)
    toko.tambahProduk(m2)
    toko.tambahProduk(p1)
    toko.tambahProduk(p2)

    println("=== SEMUA PRODUK ===")
    toko.tampilkanSemuaProduk()

    println("\n=== PRODUK DISKON ===")
    for (produk in toko.getDaftarProduk()) {
        if (produk.hitungDiskon() > 0) {
            println(produk.tampilkanInfo())
        }
    }

    println("\n=== SIMULASI PEMBELIAN ===")
    println("Beli 2 Laptop: ${if (e1.kurangiStok(2)) "Berhasil" else "Gagal"}")
    println("Beli 5 Bolen Lilit: ${if (m1.kurangiStok(5)) "Berhasil" else "Gagal"}")
    println("Beli 10 Celana Jeans Biru (Stok cuma 8): ${if (p2.kurangiStok(10)) "Berhasil" else "Gagal"}")

    println("\n=== STATUS AKHIR PRODUK ===")
    toko.tampilkanSemuaProduk()
}