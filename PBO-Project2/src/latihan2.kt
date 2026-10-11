//class Temperature(initialCelsius: Double = 0.0) {
//    private var _celsius: Double = -273.15
//
//    val celsius: Double
//        get() = _celsius
//
//    val fahrenheit: Double
//        get() = (_celsius * 9.0 / 5.0) + 32.0
//
//    val kelvin: Double
//        get() = _celsius + 273.15
//
//    init {
//        setCelsius(initialCelsius)
//    }
//
//    fun setCelsius(value: Double) {
//        if (value >= -273.15) {
//            _celsius = value
//            println("BERHASIL: Suhu Celsius diatur ke $_celsius C")
//        } else {
//            println("GAGAL: Suhu $value C di bawah nol mutlak!")
//        }
//    }
//
//    fun setFahrenheit(value: Double) {
//        val convertedCelsius = (value - 32.0) * 5.0 / 9.0
//        setCelsius(convertedCelsius)
//    }
//
//    fun setKelvin(value: Double) {
//        val convertedCelsius = value - 273.15
//        setCelsius(convertedCelsius)
//    }
//
//    fun display() {
//        println("=" .repeat(40))
//        println("INFORMASI SUHU")
//        println("=" .repeat(40))
//        println("Celsius    : $celsius C")
//        println("Fahrenheit : $fahrenheit F")
//        println("Kelvin     : $kelvin K")
//        println("=" .repeat(40))
//    }
//}
//
//fun main() {
//    val temp = Temperature(25.0)
//    temp.display()
//
//    println()
//    temp.setCelsius(-300.0)
//    temp.display()
//}


//class Counter(
//    val maxValue: Int,
//    val minValue: Int = 0
//) {
//    var value: Int = minValue
//        private set
//
//    fun increment() {
//        if (value < maxValue) {
//            value++
//            println("Increment: Nilai sekarang = $value")
//        } else {
//            println("Gagal Increment: Nilai sudah mencapai maksimum ($maxValue)")
//        }
//    }
//
//    fun decrement() {
//        if (value > minValue) {
//            value--
//            println("Decrement: Nilai sekarang = $value")
//        } else {
//            println("Gagal Decrement: Nilai sudah mencapai minimum ($minValue)")
//        }
//    }
//
//    fun reset() {
//        value = minValue
//        println("Reset: Nilai dikembalikan ke minimum ($value)")
//    }
//
//    fun isAtMax(): Boolean {
//        return value == maxValue
//    }
//
//    fun isAtMin(): Boolean {
//        return value == minValue
//    }
//
//    fun display() {
//        println("=" .repeat(35))
//        println("STATUS COUNTER")
//        println("=" .repeat(35))
//        println("Nilai Saat Ini : $value")
//        println("Nilai Minimum  : $minValue")
//        println("Nilai Maksimum : $maxValue")
//        println("Apakah Max?    : ${if (isAtMax()) "Ya" else "Tidak"}")
//        println("Apakah Min?    : ${if (isAtMin()) "Ya" else "Tidak"}")
//        println("=" .repeat(35))
//    }
//}
//
//fun main() {
//    val counter = Counter(maxValue = 3)
//
//    counter.display()
//    println()
//
//    counter.increment()
//    counter.increment()
//    counter.increment()
//    counter.increment()
//
//    println()
//    counter.display()
//
//    println()
//    counter.decrement()
//    counter.display()
//
//    println()
//    counter.reset()
//    counter.display()
//}


////KELAS ROOM (KAMAR)
//class Room(
//    val roomNumber: String,
//    val type: String,
//    val pricePerNight: Double
//) {
//    var isOccupied: Boolean = false
//        private set
//
//    var guestName: String? = null
//        private set
//
//    var checkInDate: String? = null
//        private set
//
//    var checkOutDate: String? = null
//        private set
//
//    fun checkIn(guest: String,
//                checkIn: String,
//                checkOut: String): Boolean {
//        return if (!isOccupied) {
//            isOccupied = true
//            guestName = guest
//            checkInDate = checkIn
//            checkOutDate = checkOut
//            println("Berhasil check-in kamar $roomNumber untuk tamu $guest.")
//            true
//        } else {
//            println("Gagal: Kamar $roomNumber sedang terisi oleh $guestName.")
//            false
//        }
//    }
//
//    fun checkOut(): Boolean {
//        return if (isOccupied) {
//            val prevGuest = guestName
//            isOccupied = false
//            guestName = null
//            checkInDate = null
//            checkOutDate = null
//            println("Berhasil check-out kamar $roomNumber dari tamu $prevGuest.")
//            true
//        } else {
//            println("Gagal: Kamar $roomNumber tidak sedang terisi.")
//            false
//        }
//    }
//
//    fun isAvailable(): Boolean = !isOccupied
//
//    fun displayInfo(): String {
//        val status = if (isOccupied) "Terisi ($guestName | $checkInDate s/d $checkOutDate)" else "Tersedia"
//        return """
//          * Nomor Kamar    : $roomNumber
//            Tipe Kamar     : $type
//            Harga per Malam: Rp $pricePerNight
//            Status         : $status
//        """.trimIndent()
//    }
//}
//
////KELAS HOTEL
//class Hotel(val name: String) {
//    private val rooms: MutableList<Room> = mutableListOf()
//    private var totalRevenue: Double = 0.0
//
//    fun addRoom(room: Room) {
//        rooms.add(room)
//    }
//
//    fun findRoom(roomNumber: String): Room? {
//        return rooms.find { it.roomNumber == roomNumber }
//    }
//
//    fun checkIn(roomNumber: String,
//                guest: String,
//                checkIn: String,
//                checkOut: String): Boolean {
//        val room = findRoom(roomNumber)
//        return room?.checkIn(guest, checkIn, checkOut) ?: run {
//            println("Kamar dengan nomor $roomNumber tidak ditemukan.")
//            false
//        }
//    }
//
//    fun checkOut(roomNumber: String): Boolean {
//        val room = findRoom(roomNumber)
//        return if (room != null) {
//            val price = room.pricePerNight
//            if (room.checkOut()) {
//                totalRevenue += price
//                true
//            } else {
//                false
//            }
//        } else {
//            println("Kamar dengan nomor $roomNumber tidak ditemukan.")
//            false
//        }
//    }
//
//    fun getAvailableRooms(): List<Room> = rooms.filter { it.isAvailable() }
//
//    fun getOccupiedRooms(): List<Room> = rooms.filter { it.isOccupied }
//
//    fun displayAllRooms() {
//        println("======DAFTAR SEMUA KAMAR DI $name======")
//        if (rooms.isEmpty()) {
//            println("Belum ada data kamar.")
//        } else {
//            rooms.forEach { println(it.displayInfo()) }
//        }
//    }
//
//    fun displayAvailableRooms() {
//        println("\n=======DAFTAR KAMAR TERSEDIA========")
//        val available = getAvailableRooms()
//        if (available.isEmpty()) {
//            println("Tidak ada kamar yang tersedia.")
//        } else {
//            available.forEach { println(it.displayInfo()) }
//        }
//    }
//
//    fun displayOccupiedRooms() {
//        println("\n=======DAFTAR KAMAR TERISI=======")
//        val occupied = getOccupiedRooms()
//        if (occupied.isEmpty()) {
//            println("Tidak ada kamar yang sedang terisi.")
//        } else {
//            occupied.forEach { println(it.displayInfo()) }
//        }
//    }
//
//    fun getTotalRevenue(): Double = totalRevenue
//}
//
////FUNGSI MAIN
//fun main() {
//    //Membuat objek Hotel dengan nama "Hotel Kampus"
//    val hotel = Hotel("Hotel Kampus")
//
//    //6 kamar dengan tipe berbeda (Standard, Deluxe, Suite)
//    hotel.addRoom(Room("101", "Standard", 300000.0))
//    hotel.addRoom(Room("102", "Standard", 300000.0))
//    hotel.addRoom(Room("201", "Deluxe", 500000.0))
//    hotel.addRoom(Room("202", "Deluxe", 500000.0))
//    hotel.addRoom(Room("301", "Suite", 1000000.0))
//    hotel.addRoom(Room("302", "Suite", 1000000.0))
//    hotel.addRoom(Room("303", "Suite", 1000000.0))
//
//    //Menampilkan semua kamar
//    hotel.displayAllRooms()
//
//    //Check-in beberapa kamar
//    println("\n--- PROSES CHECK-IN ---")
//    hotel.checkIn("101", "Martin Edwards", "2026-08-15", "2026-08-17")
//    hotel.checkIn("201", "Kim Jennie", "2026-08-16", "2026-08-18")
//    hotel.checkIn("301", "Zayyan", "2026-08-15", "2026-08-20")
//    hotel.checkIn("101", "Daniel Caesar", "2026-08-15", "2026-08-16") // Uji gagal: kamar sedang terisi
//
//    //Menampilkan kamar tersedia dan terisi
//    hotel.displayAvailableRooms()
//    hotel.displayOccupiedRooms()
//
//    //Check-out beberapa kamar
//    println("\n--- PROSES CHECK-OUT ---")
//    hotel.checkOut("101")
//    hotel.checkOut("201")
//    hotel.checkOut("102") // Uji gagal: kamar memang tidak terisi
//
//    println()
//
//    //Menampilkan status akhir dan total pendapatan
//    hotel.displayAllRooms()
//    println("==========================================")
//    println("Total Pendapatan Hotel: Rp ${hotel.getTotalRevenue()}")
//    println("==========================================")
//}

// 1. KELAS ROOM
//class Room(
//    val roomNumber: String,
//    val type: String,
//    val pricePerNight: Double
//) {
//    var isOccupied: Boolean = false
//        private set
//
//    var guestName: String? = null
//        private set
//
//    var checkInDate: String? = null
//        private set
//
//    var checkOutDate: String? = null
//        private set
//
//    fun checkIn(guest: String, checkIn: String, checkOut: String): Boolean {
//        return if (!isOccupied) {
//            isOccupied = true
//            guestName = guest
//            checkInDate = checkIn
//            checkOutDate = checkOut
//            println("[BERHASIL] Check-in Kamar $roomNumber atas nama $guest ($checkIn s.d $checkOut)")
//            true
//        } else {
//            println("[GAGAL] Kamar $roomNumber sudah terisi oleh $guestName")
//            false
//        }
//    }
//
//    fun checkOut(): Boolean {
//        return if (isOccupied) {
//            println("[BERHASIL] Check-out Kamar $roomNumber atas nama $guestName")
//            isOccupied = false
//            guestName = null
//            checkInDate = null
//            checkOutDate = null
//            true
//        } else {
//            println("[GAGAL] Kamar $roomNumber saat ini kosong")
//            false
//        }
//    }
//
//    fun isAvailable(): Boolean = !isOccupied
//
//    fun displayInfo(): String {
//        val statusStr = if (isOccupied) "TERISI" else "TERSEDIA"
//        val guestStr = guestName ?: "-"
//        val dateStr = if (isOccupied) "$checkInDate s/d $checkOutDate" else "-"
//
//        return String.format(
//            "| %-6s | %-10s | Rp %10.2f | %-10s | %-18s | %-23s |",
//            roomNumber, type, pricePerNight, statusStr, guestStr, dateStr
//        )
//    }
//}
//
//// 2. KELAS HOTEL
//class Hotel(
//    val name: String
//) {
//    private val rooms: MutableList<Room> = mutableListOf()
//    private var totalRevenue: Double = 0.0
//
//    fun addRoom(room: Room) {
//        rooms.add(room)
//        println("[SISTEM] Kamar ${room.roomNumber} (${room.type}) berhasil ditambahkan.")
//    }
//
//    fun findRoom(roomNumber: String): Room? {
//        return rooms.find { it.roomNumber.equals(roomNumber, ignoreCase = true) }
//    }
//
//    fun checkIn(roomNumber: String, guest: String, checkIn: String, checkOut: String): Boolean {
//        val room = findRoom(roomNumber)
//        return if (room != null) {
//            room.checkIn(guest, checkIn, checkOut)
//        } else {
//            println("[GAGAL] Kamar $roomNumber tidak ditemukan")
//            false
//        }
//    }
//
//    fun checkOut(roomNumber: String): Boolean {
//        val room = findRoom(roomNumber)
//        return if (room != null && room.isOccupied) {
//            val price = room.pricePerNight
//            val success = room.checkOut()
//            if (success) {
//                totalRevenue += price
//            }
//            success
//        } else if (room != null && !room.isOccupied) {
//            println("[GAGAL] Kamar $roomNumber tidak terisi")
//            false
//        } else {
//            println("[GAGAL] Kamar $roomNumber tidak ditemukan")
//            false
//        }
//    }
//
//    fun getAvailableRooms(): List<Room> = rooms.filter { it.isAvailable() }
//
//    fun getOccupiedRooms(): List<Room> = rooms.filter { it.isOccupied }
//
//    private fun printTableHeader() {
//        println("+--------+------------+---------------+------------+--------------------+-------------------------+")
//        println("| No.    | Tipe       | Harga/Malam   | Status     | Nama Tamu          | Periode Menginap        |")
//        println("+--------+------------+---------------+------------+--------------------+-------------------------+")
//    }
//
//    private fun printTableFooter() {
//        println("+--------+------------+---------------+------------+--------------------+-------------------------+")
//    }
//
//    fun displayAllRooms() {
//        println("\n>>> DAFTAR SELURUH KAMAR ($name)")
//        printTableHeader()
//        if (rooms.isEmpty()) {
//            println("| Tidak ada data kamar.                                                                           |")
//        } else {
//            rooms.forEach { println(it.displayInfo()) }
//        }
//        printTableFooter()
//    }
//
//    fun displayAvailableRooms() {
//        println("\n>>> DAFTAR KAMAR TERSEDIA")
//        printTableHeader()
//        val available = getAvailableRooms()
//        if (available.isEmpty()) {
//            println("| Tidak ada kamar tersedia saat ini.                                                              |")
//        } else {
//            available.forEach { println(it.displayInfo()) }
//        }
//        printTableFooter()
//    }
//
//    fun displayOccupiedRooms() {
//        println("\n>>> DAFTAR KAMAR TERISI")
//        printTableHeader()
//        val occupied = getOccupiedRooms()
//        if (occupied.isEmpty()) {
//            println("| Tidak ada kamar terisi saat ini.                                                                |")
//        } else {
//            occupied.forEach { println(it.displayInfo()) }
//        }
//        printTableFooter()
//    }
//
//    fun getTotalRevenue(): Double = totalRevenue
//}
//
//// 3. FUNGSI MAIN()
//fun main() {
//    println("===================================================================================================")
//    println("                                SISTEM MANAJEMEN HOTEL KAMPUS                                      ")
//    println("===================================================================================================")
//
//    val hotelKampus = Hotel("Hotel Kampus")
//
//    println("\n--- 1. REGISTRASI KAMAR ---")
//    hotelKampus.addRoom(Room("101", "Standard", 300000.0))
//    hotelKampus.addRoom(Room("102", "Standard", 300000.0))
//    hotelKampus.addRoom(Room("201", "Deluxe", 500000.0))
//    hotelKampus.addRoom(Room("202", "Deluxe", 500000.0))
//    hotelKampus.addRoom(Room("301", "Suite", 900000.0))
//    hotelKampus.addRoom(Room("302", "Suite", 900000.0))
//
//    // Tampilkan semua kamar awal
//    hotelKampus.displayAllRooms()
//
//    println("\n--- 2. TRANSAKSI CHECK-IN ---")
//    hotelKampus.checkIn("101", "Budi Santoso", "13/08/2026", "15/08/2026")
//    hotelKampus.checkIn("201", "Siti Rahayu", "13/08/2026", "14/08/2026")
//    hotelKampus.checkIn("301", "Ahmad Fauzi", "14/08/2026", "16/08/2026")
//
//    // Uji validasi check-in pada kamar yang sudah terisi
//    hotelKampus.checkIn("101", "Eko Prasetyo", "13/08/2026", "14/08/2026")
//
//    // Tampilkan kamar tersedia & terisi
//    hotelKampus.displayAvailableRooms()
//    hotelKampus.displayOccupiedRooms()
//
//    println("\n--- 3. TRANSAKSI CHECK-OUT ---")
//    hotelKampus.checkOut("101")
//    hotelKampus.checkOut("201")
//
//    // Uji validasi check-out pada kamar kosong
//    hotelKampus.checkOut("102")
//
//    // Tampilkan status akhir seluruh kamar
//    hotelKampus.displayAllRooms()
//
//    println("\n===================================================================================================")
//    println(String.format("TOTAL PENDAPATAN HOTEL : Rp %,12.2f", hotelKampus.getTotalRevenue()))
//    println("===================================================================================================")
//}

// 1. KELAS BUKU
class Buku(
    val judul: String,
    val penulis: String,
    val isbn: String,
    val tahunTerbit: Int
) {
    // Properti status dengan private setter (enkapsulasi penuh)
    var isDipinjam: Boolean = false
        private set

    var peminjam: String? = null
        private set

    // Metode untuk meminjam buku jika tersedia
    fun pinjam(namaPeminjam: String): Boolean {
        return if (!isDipinjam) {
            isDipinjam = true
            peminjam = namaPeminjam
            println("[BERHASIL] Buku \"$judul\" berhasil dipinjam oleh $namaPeminjam.")
            true
        } else {
            println("[GAGAL] Buku \"$judul\" sedang dipinjam oleh $peminjam.")
            false
        }
    }

    // Metode untuk mengembalikan buku
    fun kembalikan(): Boolean {
        return if (isDipinjam) {
            println("[BERHASIL] Buku \"$judul\" berhasil dikembalikan oleh $peminjam.")
            isDipinjam = false
            peminjam = null
            true
        } else {
            println("[GAGAL] Buku \"$judul\" saat ini tidak sedang dipinjam.")
            false
        }
    }

    // Metode untuk mengecek ketersediaan buku
    fun isTersedia(): Boolean = !isDipinjam

    // Format output rapi dalam bentuk baris tabel
    fun tampilkanInfo(): String {
        val statusStr = if (isDipinjam) "Dipinjam" else "Tersedia"
        val peminjamStr = peminjam ?: "-"

        return String.format(
            "| %-13s | %-28s | %-20s | %-12d | %-10s | %-18s |",
            isbn, judul, penulis, tahunTerbit, statusStr, peminjamStr
        )
    }
}

 //2. KELAS PERPUSTAKAAN
class Perpustakaan(
    val nama: String
) {
    // Properti daftarBuku bersifat private
    private val daftarBuku: MutableList<Buku> = mutableListOf()

    // Menambah buku ke daftar
    fun tambahBuku(buku: Buku) {
        daftarBuku.add(buku)
        println("[SISTEM] Buku \"${buku.judul}\" (ISBN: ${buku.isbn}) berhasil ditambahkan ke $nama.")
    }

    // Mencari buku berdasarkan judul atau penulis (case-insensitive)
    fun cariBuku(keyword: String): List<Buku> {
        return daftarBuku.filter {
            it.judul.contains(keyword, ignoreCase = true) ||
                    it.penulis.contains(keyword, ignoreCase = true)
        }
    }

    // Meminjam buku berdasarkan ISBN
    fun pinjamBuku(isbn: String, peminjam: String): Boolean {
        val buku = daftarBuku.find { it.isbn.equals(isbn, ignoreCase = true) }
        return if (buku != null) {
            buku.pinjam(peminjam)
        } else {
            println("[GAGAL] Buku dengan ISBN $isbn tidak ditemukan.")
            false
        }
    }

    // Mengembalikan buku berdasarkan ISBN
    fun kembalikanBuku(isbn: String): Boolean {
        val buku = daftarBuku.find { it.isbn.equals(isbn, ignoreCase = true) }
        return if (buku != null) {
            buku.kembalikan()
        } else {
            println("[GAGAL] Buku dengan ISBN $isbn tidak ditemukan.")
            false
        }
    }

    // Helper untuk membuat header tabel yang seragam
    private fun printTableHeader() {
        println("+---------------+------------------------------+----------------------+--------------+------------+--------------------+")
        println("| ISBN          | Judul Buku                   | Penulis              | Tahun Terbit | Status     | Peminjam           |")
        println("+---------------+------------------------------+----------------------+--------------+------------+--------------------+")
    }

    private fun printTableFooter() {
        println("+---------------+------------------------------+----------------------+--------------+------------+--------------------+")
    }

    // Menampilkan semua buku
    fun tampilkanSemuaBuku() {
        println("\n>>> DAFTAR SEMUA BUKU ($nama)")
        printTableHeader()
        if (daftarBuku.isEmpty()) {
            println("| Tidak ada buku yang terdaftar.                                                                                  |")
        } else {
            daftarBuku.forEach { println(it.tampilkanInfo()) }
        }
        printTableFooter()
    }

    // Menampilkan buku yang tersedia
    fun tampilkanBukuTersedia() {
        println("\n>>> DAFTAR BUKU TERSEDIA")
        printTableHeader()
        val tersedia = daftarBuku.filter { it.isTersedia() }
        if (tersedia.isEmpty()) {
            println("| Tidak ada buku yang tersedia saat ini.                                                                          |")
        } else {
            tersedia.forEach { println(it.tampilkanInfo()) }
        }
        printTableFooter()
    }

    // Menampilkan buku yang sedang dipinjam
    fun tampilkanBukuDipinjam() {
        println("\n>>> DAFTAR BUKU YANG SEDANG DIPINJAM")
        printTableHeader()
        val dipinjam = daftarBuku.filter { it.isDipinjam }
        if (dipinjam.isEmpty()) {
            println("| Tidak ada buku yang sedang dipinjam saat ini.                                                                   |")
        } else {
            dipinjam.forEach { println(it.tampilkanInfo()) }
        }
        printTableFooter()
    }
}

 //3. FUNGSI MAIN()
fun main() {
    println("====================================================================================================================")
    println("                                SISTEM MANAJEMEN PERPUSTAKAAN KAMPUS                                                ")
    println("====================================================================================================================")

    // 1. Buat objek Perpustakaan dengan nama "Perpustakaan Kampus"
    val perpustakaan = Perpustakaan("Perpustakaan Kampus")

    println("\n--- 1. REGISTRASI BUKU ---")
    // 2. Tambahkan minimal 5 buku dengan data berbeda
    perpustakaan.tambahBuku(Buku("Pemrograman Kotlin", "Ahmad Supardi", "978-0131103621", 2023))
    perpustakaan.tambahBuku(Buku("Struktur Data Lanjut", "Budi Raharjo", "978-0262033848", 2021))
    perpustakaan.tambahBuku(Buku("Sistem Basis Data", "Citra Dewi", "978-0136086208", 2022))
    perpustakaan.tambahBuku(Buku("Jaringan Komputer", "Dian Sastro", "978-0132126953", 2020))
    perpustakaan.tambahBuku(Buku("Kecerdasan Buatan", "Eko Prasetyo", "978-0136042594", 2024))

    // 3. Tampilkan semua buku
    perpustakaan.tampilkanSemuaBuku()

    println("\n--- 2. PROSES PEMINJAMAN BUKU ---")
    // 4. Lakukan peminjaman buku oleh beberapa mahasiswa
    perpustakaan.pinjamBuku("978-0131103621", "Arlisya Rahwitami")
    perpustakaan.pinjamBuku("978-0262033848", "Siti Rahayu")

    // Uji coba meminjam buku yang sudah dipinjam (pengujian validasi)
    perpustakaan.pinjamBuku("978-0131103621", "Ahmad Fauzi")

    // 5. Tampilkan buku yang tersedia dan yang dipinjam
    perpustakaan.tampilkanBukuTersedia()
    perpustakaan.tampilkanBukuDipinjam()

    println("\n--- 3. PROSES PENGEMBALIAN BUKU ---")
    // 6. Kembalikan salah satu buku
    perpustakaan.kembalikanBuku("978-0131103621") // Peminjaman Arlisya dikembalikan

    // Uji coba mengembalikan buku yang tidak dipinjam (pengujian validasi)
    perpustakaan.kembalikanBuku("978-0136086208")

    println("\n--- 4. STATUS AKHIR ---")
    // 7. Tampilkan status akhir semua buku
    perpustakaan.tampilkanSemuaBuku()

    println("\n====================================================================================================================")
    println("                                            PROGRAM SELESAI                                                         ")
    println("====================================================================================================================")
}