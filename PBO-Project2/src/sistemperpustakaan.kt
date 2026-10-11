/**
 * ============================================================
 * SISTEM MANAJEMEN PERPUSTAKAAN DENGAN ENKAPSULASI PENUH
 * ============================================================
 * Studi kasus komprehensif untuk mendemonstrasikan:
 * 1. Enkapsulasi data buku
 * 2. Private setter untuk status pinjam
 * 3. Validasi di setiap operasi
 * 4. Class interaction yang aman
 * ============================================================
 */

/**
 * Kelas Buku — merepresentasikan satu buku di perpustakaan
 *
 * Prinsip Enkapsulasi:
 * - Semua properti immutable (val) kecuali status peminjaman
 * - Status peminjaman hanya bisa diubah melalui metode pinjam() dan kembalikan()
 * - Peminjam hanya bisa diatur melalui metode pinjam()
 */
class Book(
    val title: String,
    val author: String,
    val isbn: String,
    val year: Int
) {
    // ============================================================
    // PROPERTI
    // ============================================================

    /**
     * Status peminjaman — private setter
     * - Bisa dibaca dari luar (getter public)
     * - Hanya bisa diubah dari dalam kelas (setter private)
     */
    var isBorrowed: Boolean = false
        private set

    /**
     * Nama peminjam — private setter, nullable
     * - Bisa dibaca dari luar
     * - Hanya bisa diubah dari dalam kelas
     */
    var borrower: String? = null
        private set

    /**
     * Ketersediaan buku — computed property
     * Tidak ada backing field — dihitung dari isBorrowed
     */
    val isAvailable: Boolean
        get() = !isBorrowed

    // ============================================================
    // METODE
    // ============================================================

    /**
     * Meminjam buku
     *
     * @param name Nama peminjam
     * @return true jika berhasil, false jika gagal
     */
    fun borrow(name: String): Boolean {
        // Validasi: buku harus tersedia
        if (isBorrowed) {
            println("❌ Buku '$title' sedang dipinjam oleh $borrower")
            return false
        }

        // Validasi: nama peminjam tidak boleh kosong
        if (name.isBlank()) {
            println("❌ Nama peminjam tidak boleh kosong")
            return false
        }

        // Update status
        isBorrowed = true
        borrower = name
        println("✅ Buku '$title' berhasil dipinjam oleh $name")
        return true
    }

    /**
     * Mengembalikan buku
     *
     * @return true jika berhasil, false jika gagal
     */
    fun returnBook(): Boolean {
        // Validasi: buku harus sedang dipinjam
        if (!isBorrowed) {
            println("❌ Buku '$title' tidak sedang dipinjam")
            return false
        }

        val previousBorrower = borrower
        // Update status
        isBorrowed = false
        borrower = null
        println("✅ Buku '$title' berhasil dikembalikan oleh $previousBorrower")
        return true
    }

    /**
     * Menampilkan informasi buku
     */
    fun displayInfo() {
        println("=" .repeat(45))
        println("📚 INFORMASI BUKU")
        println("=" .repeat(45))
        println("Judul    : $title")
        println("Penulis  : $author")
        println("ISBN     : $isbn")
        println("Tahun    : $year")
        println("Status   : ${if (isBorrowed) "🔴 Dipinjam" else "🟢 Tersedia"}")
        if (isBorrowed) {
            println("Peminjam : $borrower")
        }
        println("=" .repeat(45))
    }
}

/**
 * Kelas Perpustakaan — mengelola koleksi buku
 *
 * Prinsip Enkapsulasi:
 * - Daftar buku private — tidak bisa diakses langsung dari luar
 * - Semua operasi melalui metode publik
 */
class Library(val name: String) {
    // ============================================================
    // PROPERTI
    // ============================================================

    /**
     * Daftar buku — private total
     * Tidak ada akses dari luar sama sekali
     */
    private val books = mutableListOf<Book>()

    /**
     * Jumlah buku — computed property
     */
    val totalBooks: Int
        get() = books.size

    /**
     * Jumlah buku tersedia — computed property
     */
    val availableBooks: Int
        get() = books.count { it.isAvailable }

    /**
     * Jumlah buku dipinjam — computed property
     */
    val borrowedBooks: Int
        get() = books.count { it.isBorrowed }

    // ============================================================
    // METODE
    // ============================================================

    /**
     * Menambah buku ke koleksi
     */
    fun addBook(book: Book) {
        books.add(book)
        println("✅ Buku '${book.title}' ditambahkan ke perpustakaan")
    }

    /**
     * Menambah multiple buku sekaligus
     */
    fun addBooks(vararg newBooks: Book) {
        for (book in newBooks) {
            addBook(book)
        }
    }

    /**
     * Mencari buku berdasarkan kata kunci (judul atau penulis)
     *
     * @param keyword Kata kunci pencarian (case-insensitive)
     * @return List buku yang cocok
     */
    fun searchBooks(keyword: String): List<Book> {
        val lowerKeyword = keyword.lowercase()
        return books.filter { book ->
            book.title.lowercase().contains(lowerKeyword) ||
                    book.author.lowercase().contains(lowerKeyword)
        }
    }

    /**
     * Mencari buku berdasarkan ISBN (pencarian tepat)
     *
     * @param isbn ISBN buku
     * @return Buku yang ditemukan, atau null jika tidak ada
     */
    fun findBookByIsbn(isbn: String): Book? {
        return books.find { it.isbn == isbn }
    }

    /**
     * Meminjam buku berdasarkan ISBN
     *
     * @param isbn ISBN buku
     * @param borrower Nama peminjam
     * @return true jika berhasil, false jika gagal
     */
    fun borrowBook(isbn: String, borrower: String): Boolean {
        val book = findBookByIsbn(isbn)
        if (book == null) {
            println("❌ Buku dengan ISBN $isbn tidak ditemukan")
            return false
        }
        return book.borrow(borrower)
    }

    /**
     * Mengembalikan buku berdasarkan ISBN
     *
     * @param isbn ISBN buku
     * @return true jika berhasil, false jika gagal
     */
    fun returnBook(isbn: String): Boolean {
        val book = findBookByIsbn(isbn)
        if (book == null) {
            println("❌ Buku dengan ISBN $isbn tidak ditemukan")
            return false
        }
        return book.returnBook()
    }

    /**
     * Menampilkan semua buku
     */
    fun displayAllBooks() {
        println("=" .repeat(45))
        println("📚 DAFTAR SEMUA BUKU — $name")
        println("=" .repeat(45))
        println("Total: $totalBooks buku")
        println("Tersedia: $availableBooks buku")
        println("Dipinjam: $borrowedBooks buku")
        println("-" .repeat(45))

        if (books.isEmpty()) {
            println("   Belum ada buku")
        } else {
            for ((index, book) in books.withIndex()) {
                println("${index + 1}. ${book.title} — ${book.author}")
                println("   ISBN: ${book.isbn} | Status: ${if (book.isBorrowed) "🔴 Dipinjam" else "🟢 Tersedia"}")
                if (book.isBorrowed) {
                    println("   Peminjam: ${book.borrower}")
                }
            }
        }
        println("=" .repeat(45))
    }

    /**
     * Menampilkan buku yang tersedia
     */
    fun displayAvailableBooks() {
        val available = books.filter { it.isAvailable }
        println("=" .repeat(45))
        println("🟢 BUKU TERSEDIA — $name")
        println("=" .repeat(45))
        println("Jumlah: ${available.size} buku")
        println("-" .repeat(45))

        if (available.isEmpty()) {
            println("   Tidak ada buku tersedia")
        } else {
            for ((index, book) in available.withIndex()) {
                println("${index + 1}. ${book.title} — ${book.author} (ISBN: ${book.isbn})")
            }
        }
        println("=" .repeat(45))
    }

    /**
     * Menampilkan buku yang sedang dipinjam
     */
    fun displayBorrowedBooks() {
        val borrowed = books.filter { it.isBorrowed }
        println("=" .repeat(45))
        println("🔴 BUKU DIPINJAM — $name")
        println("=" .repeat(45))
        println("Jumlah: ${borrowed.size} buku")
        println("-" .repeat(45))

        if (borrowed.isEmpty()) {
            println("   Tidak ada buku yang dipinjam")
        } else {
            for ((index, book) in borrowed.withIndex()) {
                println("${index + 1}. ${book.title} — ${book.author}")
                println("   Peminjam: ${book.borrower} | ISBN: ${book.isbn}")
            }
        }
        println("=" .repeat(45))
    }
}

/**
 * Fungsi utama — demo sistem perpustakaan
 */
fun main() {
    println("=" .repeat(55))
    println("📚 DEMO SISTEM PERPUSTAKAAN DENGAN ENKAPSULASI")
    println("=" .repeat(55))
    println()

    // Buat perpustakaan
    val library = Library("Perpustakaan Kampus")

    // Buat beberapa buku
    val book1 = Book("Pemrograman Kotlin", "Budi Santoso", "978-602-1234-001", 2023)
    val book2 = Book("Dasar-Dasar OOP", "Siti Rahayu", "978-602-1234-002", 2022)
    val book3 = Book("Algoritma dan Struktur Data", "Ahmad Fauzi", "978-602-1234-003", 2023)
    val book4 = Book("Database Sistem", "Dewi Lestari", "978-602-1234-004", 2021)
    val book5 = Book("Jaringan Komputer", "Rizki Pratama", "978-602-1234-005", 2022)

    // Tambahkan buku ke perpustakaan
    library.addBooks(book1, book2, book3, book4, book5)
    println()

    // Tampilkan semua buku
    library.displayAllBooks()
    println()

    // Lakukan peminjaman
    println("--- PROSES PEMINJAMAN ---")
    library.borrowBook("978-602-1234-001", "Mahasiswa A")
    library.borrowBook("978-602-1234-003", "Mahasiswa B")
    library.borrowBook("978-602-1234-005", "Mahasiswa C")
    println()

    // Tampilkan buku tersedia dan dipinjam
    library.displayAvailableBooks()
    println()
    library.displayBorrowedBooks()
    println()

    // Kembalikan buku
    println("--- PROSES PENGEMBALIAN ---")
    library.returnBook("978-602-1234-001")
    library.returnBook("978-602-1234-003")
    println()

    // Tampilkan status akhir
    library.displayAllBooks()
    println()

    // Cari buku
    println("--- PENCARIAN BUKU ---")
    val searchResults = library.searchBooks("Kotlin")
    println("Hasil pencarian 'Kotlin': ${searchResults.size} buku ditemukan")
    for (book in searchResults) {
        println("   - ${book.title} oleh ${book.author}")
    }
    println()

    // Demonstrasi bahwa status buku tidak bisa diubah langsung dari luar
    println("--- DEMONSTRASI ENKAPSULASI ---")
    println("Mencoba mengubah status buku langsung dari luar...")
    // book1.isBorrowed = true  // ❌ ERROR: Cannot assign to 'isBorrowed': the setter is private
    // book1.borrower = "X"     // ❌ ERROR: Cannot assign to 'borrower': the setter is private
    println("✅ Status buku TIDAK BISA diubah langsung dari luar — enkapsulasi berhasil!")

    println()
    println("=" .repeat(55))
    println("🏁 PROGRAM SELESAI")
    println("=" .repeat(55))
}