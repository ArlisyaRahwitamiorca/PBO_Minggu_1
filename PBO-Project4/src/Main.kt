/**
 * Kelas abstrak yang merepresentasikan item umum di perpustakaan.
 *
 * @property id ID unik untuk item perpustakaan.
 * @property title Judul dari item.
 * @property year Tahun terbit atau rilis item.
 */
abstract class Item(
    val id: String,
    val title: String,
    val year: Int
) {
    /**
     * Status ketersediaan item. Hanya dapat diubah dari dalam kelas.
     */
    var isAvailable: Boolean = true
        private set

    /**
     * Menghitung besaran denda keterlambatan per hari.
     *
     * @return Nominal denda per hari dalam rupiah.
     */
    abstract fun calculateFinePerDay(): Double

    /**
     * Mengembalikan nama jenis item.
     *
     * @return Jenis item sebagai String.
     */
    abstract fun getItemType(): String

    /**
     * Mengembalikan batas maksimal durasi peminjaman item.
     *
     * @return Maksimal hari peminjaman.
     */
    abstract fun getMaxBorrowDays(): Int

    /**
     * Melakukan proses peminjaman item.
     *
     * @return [Boolean] true jika berhasil dipinjam, false jika item sedang tidak tersedia.
     */
    fun borrow(): Boolean {
        return if (isAvailable) {
            isAvailable = false
            println("[SUKSES] Item \"$title\" (ID: $id) berhasil dipinjam.")
            true
        } else {
            println("[ERROR] Item \"$title\" (ID: $id) sedang tidak tersedia.")
            false
        }
    }

    /**
     * Melakukan proses pengembalian item dan mengkalkulasi denda jika ada keterlambatan.
     *
     * @param daysLate Jumlah hari keterlambatan pengembalian.
     * @return Total denda keterlambatan yang harus dibayar.
     */
    fun returnItem(daysLate: Int = 0): Double {
        if (!isAvailable) {
            isAvailable = true
            val totalFine = daysLate * calculateFinePerDay()
            println("[SUKSES] Item \"$title\" (ID: $id) berhasil dikembalikan.")
            if (totalFine > 0) {
                println("[DENDA] Keterlambatan: $daysLate hari. Total Denda: Rp ${String.format("%,.2f", totalFine)}")
            }
            return totalFine
        } else {
            println("[PERINGATAN] Item \"$title\" (ID: $id) saat ini tidak sedang dipinjam.")
            return 0.0
        }
    }

    /**
     * Menampilkan informasi dasar dari item.
     */
    open fun displayInfo() {
        val statusStr = if (isAvailable) "Tersedia" else "Dipinjam"
        println("ID Item          : $id")
        println("Judul            : $title")
        println("Tahun            : $year")
        println("Jenis Item       : ${getItemType()}")
        println("Status           : $statusStr")
        println("Max Pinjam       : ${getMaxBorrowDays()} hari")
        println("Denda / Hari     : Rp ${String.format("%,.2f", calculateFinePerDay())}")
    }
}




/**
 * Kelas yang merepresentasikan Buku, mewarisi [Item].
 *
 * @param id ID unik buku.
 * @param title Judul buku.
 * @param year Tahun terbit buku.
 * @property author Penulis buku.
 * @property pages Jumlah halaman buku.
 * @property genre Genre dari buku.
 */
class Book(
    id: String,
    title: String,
    year: Int,
    val author: String,
    val pages: Int,
    val genre: String
) : Item(id, title, year) {

    override fun calculateFinePerDay(): Double = 2000.0

    override fun getItemType(): String = "Buku"

    override fun getMaxBorrowDays(): Int = 14

    override fun displayInfo() {
        super.displayInfo()
        println("Penulis          : $author")
        println("Jumlah Halaman   : $pages")
        println("Genre            : $genre")
    }
}


/**
 * Kelas yang merepresentasikan Jurnal, mewarisi [Item].
 *
 * @param id ID unik jurnal.
 * @param title Judul jurnal.
 * @param year Tahun terbit jurnal.
 * @property publisher Penerbit jurnal.
 * @property volume Volume edisi jurnal.
 * @property issueNumber Nomor terbitan edisi jurnal.
 */
class Journal(
    id: String,
    title: String,
    year: Int,
    val publisher: String,
    val volume: Int,
    val issueNumber: Int
) : Item(id, title, year) {

    override fun calculateFinePerDay(): Double = 3000.0

    override fun getItemType(): String = "Jurnal"

    override fun getMaxBorrowDays(): Int = 7

    override fun displayInfo() {
        super.displayInfo()
        println("Penerbit         : $publisher")
        println("Volume           : $volume")
        println("Edisi            : $issueNumber")
    }
}



/**
 * Kelas yang merepresentasikan DVD, mewarisi [Item].
 *
 * @param id ID unik DVD.
 * @param title Judul DVD.
 * @param year Tahun rilis DVD.
 * @property director Sutradara film/konten DVD.
 * @property duration Durasi pemutaran DVD dalam menit.
 * @property genre Genre dari DVD.
 */
class DVD(
    id: String,
    title: String,
    year: Int,
    val director: String,
    val duration: Int,
    val genre: String
) : Item(id, title, year) {

    override fun calculateFinePerDay(): Double = 5000.0

    override fun getItemType(): String = "DVD"

    override fun getMaxBorrowDays(): Int = 3

    override fun displayInfo() {
        super.displayInfo()
        println("Sutradara        : $director")
        println("Durasi           : $duration menit")
        println("Genre            : $genre")
    }
}




/**
 * Sealed Class yang mewakili status transaksi peminjaman item perpustakaan.
 */
sealed class TransactionStatus {

    /**
     * Mengembalikan teks representasi dari status transaksi.
     *
     * @return String status transaksi.
     */
    abstract fun display(): String

    /**
     * Mengecek apakah status transaksi bersifat final (selesai).
     *
     * @return [Boolean] true jika status Returned atau Cancelled, false jika sebaliknya.
     */
    fun isFinal(): Boolean = this is Returned || this is Cancelled

    /**
     * Subclass status untuk item yang sedang dipinjam.
     */
    object Borrowed : TransactionStatus() {
        override fun display(): String = "Dipinjam"
    }

    /**
     * Subclass status untuk item yang sudah dikembalikan tepat waktu.
     */
    object Returned : TransactionStatus() {
        override fun display(): String = "Sudah Dikembalikan"
    }

    /**
     * Subclass status untuk transaksi yang terlambat dikembalikan.
     *
     * @property daysLate Jumlah hari keterlambatan.
     */
    data class Overdue(val daysLate: Int) : TransactionStatus() {
        override fun display(): String = "Terlambat ($daysLate hari)"
    }

    /**
     * Subclass status untuk transaksi peminjaman yang dibatalkan.
     */
    object Cancelled : TransactionStatus() {
        override fun display(): String = "Dibatalkan"
    }
}



import java.time.LocalDate

/**
 * Kelas yang merepresentasikan satu transaksi peminjaman.
 *
 * @property id ID unik transaksi.
 * @property item Item perpustakaan yang dipinjam.
 * @property member Anggota yang meminjam item.
 * @property borrowDate Tanggal dimulainya peminjaman.
 * @property status Status aktif transaksi.
 */
class Transaction(
    val id: String,
    val item: Item,
    val member: Member,
    val borrowDate: String = LocalDate.now().toString(),
    var status: TransactionStatus = TransactionStatus.Borrowed
) {
    /**
     * Memproses pengembalian item dalam transaksi ini.
     *
     * @param daysLate Jumlah hari keterlambatan.
     * @return Besaran denda yang dihitung dari pengembalian item.
     */
    fun returnItem(daysLate: Int): Double {
        if (status.isFinal()) {
            println("[ERROR] Transaksi $id sudah bersifat final dan tidak dapat diubah.")
            return 0.0
        }

        val fine = item.returnItem(daysLate)
        status = if (daysLate > 0) {
            TransactionStatus.Overdue(daysLate)
        } else {
            TransactionStatus.Returned
        }
        return fine
    }

    /**
     * Membatalkan transaksi peminjaman.
     */
    fun cancel() {
        if (status.isFinal()) {
            println("[ERROR] Transaksi $id sudah selesai/batal, tidak dapat dibatalkan lagi.")
        } else {
            status = TransactionStatus.Cancelled
            item.returnItem(0)
            println("[SUKSES] Transaksi $id berhasil dibatalkan.")
        }
    }

    /**
     * Menampilkan detail informasi dari transaksi.
     */
    fun displayTransaction() {
        println("ID Transaksi     : $id")
        println("Tanggal Pinjam   : $borrowDate")
        println("Peminjam         : ${member.name} (${member.id})")
        println("Item             : ${item.title} [${item.getItemType()}]")
        println("Status           : ${status.display()}")
    }
}



import java.time.LocalDate

/**
 * Kelas yang merepresentasikan satu transaksi peminjaman.
 *
 * @property id ID unik transaksi.
 * @property item Item perpustakaan yang dipinjam.
 * @property member Anggota yang meminjam item.
 * @property borrowDate Tanggal dimulainya peminjaman.
 * @property status Status aktif transaksi.
 */
class Transaction(
    val id: String,
    val item: Item,
    val member: Member,
    val borrowDate: String = LocalDate.now().toString(),
    var status: TransactionStatus = TransactionStatus.Borrowed
) {
    /**
     * Memproses pengembalian item dalam transaksi ini.
     *
     * @param daysLate Jumlah hari keterlambatan.
     * @return Besaran denda yang dihitung dari pengembalian item.
     */
    fun returnItem(daysLate: Int): Double {
        if (status.isFinal()) {
            println("[ERROR] Transaksi $id sudah bersifat final dan tidak dapat diubah.")
            return 0.0
        }

        val fine = item.returnItem(daysLate)
        status = if (daysLate > 0) {
            TransactionStatus.Overdue(daysLate)
        } else {
            TransactionStatus.Returned
        }
        return fine
    }

    /**
     * Membatalkan transaksi peminjaman.
     */
    fun cancel() {
        if (status.isFinal()) {
            println("[ERROR] Transaksi $id sudah selesai/batal, tidak dapat dibatalkan lagi.")
        } else {
            status = TransactionStatus.Cancelled
            item.returnItem(0)
            println("[SUKSES] Transaksi $id berhasil dibatalkan.")
        }
    }

    /**
     * Menampilkan detail informasi dari transaksi.
     */
    fun displayTransaction() {
        println("ID Transaksi     : $id")
        println("Tanggal Pinjam   : $borrowDate")
        println("Peminjam         : ${member.name} (${member.id})")
        println("Item             : ${item.title} [${item.getItemType()}]")
        println("Status           : ${status.display()}")
    }
}




/**
 * Kelas yang merepresentasikan anggota perpustakaan.
 *
 * @property id ID unik anggota.
 * @property name Nama anggota.
 * @param email Email anggota.
 * @param phone Nomor telepon anggota.
 */
class Member(
    val id: String,
    val name: String,
    private val email: String,
    private val phone: String
) {
    private val transactions: MutableList<Transaction> = mutableListOf()

    /**
     * Jumlah total transaksi yang telah/sedang dilakukan anggota.
     */
    val transactionCount: Int
        get() = transactions.size

    /**
     * Total denda akumulatif dari transaksi bermasalah.
     */
    val totalFines: Double
        get() = transactions.sumOf { trx ->
            val st = trx.status
            if (st is TransactionStatus.Overdue) {
                st.daysLate * trx.item.calculateFinePerDay()
            } else {
                0.0
            }
        }

    /**
     * Jumlah peminjaman item yang sedang aktif.
     */
    val activeBorrows: Int
        get() = transactions.count { it.status is TransactionStatus.Borrowed }

    /**
     * Mengembalikan alamat email anggota.
     */
    fun getEmail(): String = email

    /**
     * Mengembalikan nomor telepon anggota.
     */
    fun getPhone(): String = phone

    /**
     * Meminjam item perpustakaan baru.
     *
     * @param item Objek item yang ingin dipinjam.
     * @return Objek [Transaction] jika sukses, null jika gagal.
     */
    fun borrowItem(item: Item): Transaction? {
        if (!item.isAvailable) {
            println("[ERROR] $name gagal meminjam \"${item.title}\" karena item tidak tersedia.")
            return null
        }

        if (activeBorrows >= 3) {
            println("[ERROR] $name telah mencapai batas maksimal peminjaman (3 item aktif).")
            return null
        }

        if (item.borrow()) {
            val trxId = "TRX-${System.currentTimeMillis()}"
            val newTrx = Transaction(trxId, item, this)
            transactions.add(newTrx)
            println("[SUKSES] Transaksi peminjaman dibuat untuk $name dengan ID Transaksi: $trxId.")
            return newTrx
        }

        return null
    }

    /**
     * Mengembalikan item yang sedang dipinjam anggota.
     *
     * @param item Objek item yang dikembalikan.
     * @param daysLate Jumlah hari keterlambatan.
     * @return Nominal denda dari transaksi tersebut.
     */
    fun returnItem(item: Item, daysLate: Int = 0): Double {
        val trx = transactions.find { it.item.id == item.id && it.status is TransactionStatus.Borrowed }
        return if (trx != null) {
            trx.returnItem(daysLate)
        } else {
            println("[ERROR] Transaksi aktif untuk item \"${item.title}\" oleh $name tidak ditemukan.")
            0.0
        }
    }

    /**
     * Mengembalikan seluruh daftar transaksi milik anggota.
     */
    fun getTransactions(): List<Transaction> = transactions.toList()

    /**
     * Menampilkan profil ringkas beserta status pinjaman anggota.
     */
    fun displayInfo() {
        println("ID Anggota       : $id")
        println("Nama             : $name")
        println("Email            : $email")
        println("Telepon          : $phone")
        println("Total Transaksi  : $transactionCount")
        println("Pinjam Aktif     : $activeBorrows / 3")
        println("Total Denda      : Rp ${String.format("%,.2f", totalFines)}")
    }

    /**
     * Menampilkan riwayat transaksi anggota secara terperinci.
     */
    fun displayTransactions() {
        println("=== Riwayat Transaksi Anggota: $name ($id) ===")
        if (transactions.isEmpty()) {
            println("Belum ada riwayat transaksi.")
        } else {
            transactions.forEachIndexed { index, trx ->
                println("--- Transaksi #${index + 1} ---")
                trx.displayTransaction()
            }
        }
    }
}





/**
 * Kelas utama perpustakaan yang bertindak sebagai pengelola item, anggota, dan transaksi.
 *
 * @property name Nama dari perpustakaan.
 */
class Library(
    val name: String
) {
    private val items: MutableList<Item> = mutableListOf()
    private val members: MutableList<Member> = mutableListOf()
    private val transactions: MutableList<Transaction> = mutableListOf()

    val totalItems: Int
        get() = items.size

    val availableItems: Int
        get() = items.count { it.isAvailable }

    val totalMembers: Int
        get() = members.size

    val totalTransactions: Int
        get() = transactions.size

    /**
     * Menambahkan item baru ke koleksi perpustakaan.
     */
    fun addItem(item: Item) {
        items.add(item)
        println("[SISTEM] Item \"${item.title}\" (ID: ${item.id}) telah dimasukkan ke perpustakaan.")
    }

    /**
     * Menambahkan beberapa item sekaligus.
     */
    fun addItems(vararg newItems: Item) {
        for (item in newItems) {
            addItem(item)
        }
    }

    /**
     * Mencari item berdasarkan ID.
     */
    fun findItem(id: String): Item? {
        return items.find { it.id.equals(id, ignoreCase = true) }
    }

    /**
     * Mencari koleksi item berdasarkan judul atau ID.
     */
    fun searchItems(keyword: String): List<Item> {
        return items.filter {
            it.title.contains(keyword, ignoreCase = true) || it.id.contains(keyword, ignoreCase = true)
        }
    }

    /**
     * Memproses pendaftaran anggota baru.
     */
    fun registerMember(id: String, name: String, email: String, phone: String): Boolean {
        return if (findMember(id) == null) {
            val newMember = Member(id, name, email, phone)
            members.add(newMember)
            println("[SISTEM] Anggota $name ($id) berhasil terdaftar.")
            true
        } else {
            println("[ERROR] ID Anggota $id sudah digunakan.")
            false
        }
    }

    /**
     * Mencari data anggota berdasarkan ID.
     */
    fun findMember(id: String): Member? {
        return members.find { it.id.equals(id, ignoreCase = true) }
    }

    /**
     * Memproses transaksi peminjaman item oleh anggota.
     */
    fun borrowItem(memberId: String, itemId: String): Transaction? {
        val member = findMember(memberId)
        val item = findItem(itemId)

        if (member == null) {
            println("[ERROR] Data anggota dengan ID $memberId tidak ditemukan.")
            return null
        }
        if (item == null) {
            println("[ERROR] Data item dengan ID $itemId tidak ditemukan.")
            return null
        }

        val trx = member.borrowItem(item)
        if (trx != null) {
            transactions.add(trx)
        }
        return trx
    }

    /**
     * Memproses transaksi pengembalian item oleh anggota.
     */
    fun returnItem(memberId: String, itemId: String, daysLate: Int = 0): Double {
        val member = findMember(memberId)
        val item = findItem(itemId)

        if (member == null) {
            println("[ERROR] Data anggota dengan ID $memberId tidak ditemukan.")
            return 0.0
        }
        if (item == null) {
            println("[ERROR] Data item dengan ID $itemId tidak ditemukan.")
            return 0.0
        }

        return member.returnItem(item, daysLate)
    }

    /**
     * Menampilkan seluruh koleksi item perpustakaan.
     */
    fun displayAllItems() {
        println("==================================================")
        println("       DAFTAR SELURUH ITEM ($name)")
        println("==================================================")
        if (items.isEmpty()) {
            println("Belum ada koleksi item.")
        } else {
            items.forEach {
                it.displayInfo()
                println("--------------------------------------------------")
            }
        }
        println("Total Item: $totalItems | Tersedia: $availableItems")
    }

    /**
     * Menampilkan item yang dapat dipinjam saja.
     */
    fun displayAvailableItems() {
        println("==================================================")
        println("          DAFTAR ITEM TERSEDIA                    ")
        println("==================================================")
        val availables = items.filter { it.isAvailable }
        if (availables.isEmpty()) {
            println("Tidak ada item yang sedang tersedia.")
        } else {
            availables.forEach { item ->
                println("[${item.id}] ${item.title} (${item.getItemType()}) - Max ${item.getMaxBorrowDays()} hari")
            }
        }
    }

    /**
     * Menampilkan seluruh data anggota perpustakaan.
     */
    fun displayAllMembers() {
        println("==================================================")
        println("       DAFTAR ANGGOTA ($name)")
        println("==================================================")
        if (members.isEmpty()) {
            println("Belum ada anggota terdaftar.")
        } else {
            members.forEach {
                it.displayInfo()
                println("--------------------------------------------------")
            }
        }
    }

    /**
     * Menampilkan seluruh transaksi yang pernah diproses.
     */
    fun displayAllTransactions() {
        println("==================================================")
        println("       DAFTAR SEMUA TRANSAKSI ($name)")
        println("==================================================")
        if (transactions.isEmpty()) {
            println("Belum ada catatan transaksi.")
        } else {
            transactions.forEach {
                it.displayTransaction()
                println("--------------------------------------------------")
            }
        }
    }

    /**
     * Menampilkan rangkuman statistik perpustakaan secara menyeluruh.
     */
    fun displayReport() {
        val totalDendaSistem = members.sumOf { it.totalFines }
        println("==================================================")
        println("          LAPORAN UTAMA $name            ")
        println("==================================================")
        println("Nama Perpustakaan: $name")
        println("Total Koleksi    : $totalItems item")
        println("  - Tersedia     : $availableItems item")
        println("  - Dipinjam     : ${totalItems - availableItems} item")
        println("Total Anggota    : $totalMembers orang")
        println("Total Transaksi  : $totalTransactions transaksi")
        println("Total Denda      : Rp ${String.format("%,.2f", totalDendaSistem)}")
        println("==================================================")
    }
}




/**
 * Fungsi utama tempat menjalankan alur skenario demonstrasi wajib program.
 */
fun main() {
    println("--------------------------------------------------")
    println(" SKENARIO DEMONSTRASI PERPUSTAKAAN DIGITAL ")
    println("--------------------------------------------------\n")

    // 1. Inisialisasi Perpustakaan
    println("=== 1. INISIALISASI PERPUSTAKAAN ===")
    val library = Library("Perpustakaan Kampus")
    println("Perpustakaan \"${library.name}\" telah dibuat.\n")

    // 2. Tambah Item
    println("=== 2. MENAMBAHKAN ITEM KOLEKSI ===")
    val b1 = Book("B001", "Pemrograman Kotlin", 2023, "Budi Santoso", 350, "Programming")
    val b2 = Book("B002", "Dasar-Dasar OOP", 2022, "Siti Rahayu", 280, "Education")
    val j1 = Journal("J001", "Jurnal Teknologi Informasi", 2023, "ITB", 15, 2)
    val j2 = Journal("J002", "Jurnal Pendidikan", 2022, "UGM", 10, 1)
    val d1 = DVD("D001", "Inception", 2010, "Christopher Nolan", 148, "Sci-Fi")
    val d2 = DVD("D002", "The Matrix", 1999, "Wachowski", 136, "Action")

    library.addItems(b1, b2, j1, j2, d1, d2)
    println()

    // 3. Registrasi Anggota
    println("=== 3. REGISTRASI ANGGOTA ===")
    library.registerMember("M001", "Ahmad Fauzi", "ahmad@email.com", "08123456789")
    library.registerMember("M002", "Dewi Lestari", "dewi@email.com", "08129876543")
    library.registerMember("M003", "Rizky Pratama", "rizky@email.com", "08125678901")
    println()

    // 4. Tampilkan Semua Item
    println("=== 4. MENAMPILKAN SELURUH ITEM ===")
    library.displayAllItems()
    println()

    // 5. Peminjaman (Skenario A)
    println("=== 5. PEMINJAMAN ITEM (SKENARIO A) ===")
    library.borrowItem("M001", "B001") // Ahmad pinjam Pemrograman Kotlin
    library.borrowItem("M001", "D001") // Ahmad pinjam Inception
    library.borrowItem("M002", "J001") // Dewi pinjam Jurnal TI
    library.borrowItem("M003", "B002") // Rizky pinjam Dasar OOP
    println()

    // 6. Tampilkan Item Tersedia
    println("=== 6. MENAMPILKAN ITEM TERSEDIA ===")
    library.displayAvailableItems()
    println()

    // 7. Tampilkan Transaksi Anggota
    println("=== 7. TRANSAKSI ANGGOTA (AHMAD & DEWI) ===")
    val ahmad = library.findMember("M001")
    val dewi = library.findMember("M002")
    ahmad?.displayTransactions()
    println()
    dewi?.displayTransactions()
    println()

    // 8. Pengembalian (Skenario B)
    println("=== 8. PENGEMBALIAN ITEM (SKENARIO B) ===")
    println("-> Ahmad mengembalikan B001 tepat waktu (daysLate = 0):")
    library.returnItem("M001", "B001", daysLate = 0)
    println()
    println("-> Dewi mengembalikan J001 terlambat 3 hari (daysLate = 3):")
    library.returnItem("M002", "J001", daysLate = 3)
    println()

    // 9. Tampilkan Transaksi Setelah Pengembalian
    println("=== 9. STATUS TRANSAKSI SETELAH PENGEMBALIAN ===")
    ahmad?.displayTransactions()
    println()
    dewi?.displayTransactions()
    println()

    // 10. Demonstrasi Polimorfisme
    println("=== 10. DEMONSTRASI POLIMORFISME ===")
    val sampleList: List<Item> = listOf(b1, j1, d1)
    for (item in sampleList) {
        println("${item.getItemType()} - Judul: \"${item.title}\" | Denda/hari: Rp ${String.format("%,.2f", item.calculateFinePerDay())}")
    }
    println()

    // 11. Demonstrasi Sealed Class
    println("=== 11. DEMONSTRASI SEALED CLASS ===")
    val statusList: List<TransactionStatus> = listOf(
        TransactionStatus.Borrowed,
        TransactionStatus.Returned,
        TransactionStatus.Overdue(5),
        TransactionStatus.Cancelled
    )

    for (st in statusList) {
        val resultText = when (st) {
            is TransactionStatus.Borrowed -> "Status: ${st.display()} (Masih dalam masa pinjam)"
            is TransactionStatus.Returned -> "Status: ${st.display()} (Selesai)"
            is TransactionStatus.Overdue -> "Status: ${st.display()} (Harus bayar denda)"
            is TransactionStatus.Cancelled -> "Status: ${st.display()} (Transaksi dibatalkan)"
        }
        println(resultText)
    }
    println()

    // 12. Demonstrasi Smart Casting
    println("=== 12. DEMONSTRASI SMART CASTING ===")
    val testItem: Item = b1
    if (testItem is Book) {
        println("Item ID ${testItem.id} adalah sebuah Buku dengan penulis: ${testItem.author}")
    } else if (testItem is Journal) {
        println("Item ID ${testItem.id} adalah sebuah Jurnal")
    } else if (testItem is DVD) {
        println("Item ID ${testItem.id} adalah sebuah DVD")
    }

    val tryDvdCast: DVD? = testItem as? DVD
    println("Hasil mencoba casting testItem (B001) ke DVD dengan 'as?': $tryDvdCast")
    println()

    // 13. Demonstrasi Enkapsulasi
    println("=== 13. DEMONSTRASI ENKAPSULASI ===")
    println("1. Properti `isAvailable` di kelas Item menggunakan `private set`.")
    println("   Uji coba perintah `b1.isAvailable = false` akan menghasilkan Kompiler Error.")
    println("2. Properti `email` dan `phone` pada kelas Member diset `private val`.")
    println("   Uji coba akses `ahmad.email` secara langsung dari luar kelas akan ditolak kompiler.")
    println("   Gunakan method `ahmad.getEmail()` untuk memperoleh datanya secara terkontrol.")
    println()

    // 14. Laporan Akhir
    println("=== 14. LAPORAN AKHIR PERPUSTAKAAN ===")
    library.displayReport()
}






