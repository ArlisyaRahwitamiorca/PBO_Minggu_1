fun main() {
    // 1. Inisialisasi Library
    val library = Library("Perpustakaan Kampus")

    // 2. Tambah Item Wajib
    library.addItems(
        Book("B001", "Pemrograman Kotlin", 2023, "Budi Santoso", 350, "Programming"),
        Book("B002", "Dasar-Dasar OOP", 2022, "Siti Rahayu", 280, "Education"),
        Journal("J001", "Jurnal Teknologi Informasi", 2023, "ITB", 15, 2),
        Journal("J002", "Jurnal Pendidikan", 2022, "UGM", 10, 1),
        DVD("D001", "Inception", 2010, "Christopher Nolan", 148, "Sci-Fi"),
        DVD("D002", "The Matrix", 1999, "Wachowski", 136, "Action")
    )

    // 3. Registrasi Anggota Wajib
    library.registerMember("M001", "Ahmad Fauzi", "ahmad@email.com", "08123456789")
    library.registerMember("M002", "Dewi Lestari", "dewi@email.com", "08129876543")
    library.registerMember("M003", "Rizky Pratama", "rizky@email.com", "08125678901")

    // 4. Tampilkan Semua Item
    library.displayAllItems()

    // 5. Peminjaman (Skenario A)
    println("\n--- EKSEKUSI PEMINJAMAN ---")
    library.borrowItem("M001", "B001")
    library.borrowItem("M001", "D001")
    library.borrowItem("M002", "J001")
    library.borrowItem("M003", "B002")

    // 6. Tampilkan Item Tersedia
    library.displayAvailableItems()

    // 7. Tampilkan Transaksi Anggota
    library.findMember("M001")?.displayTransactions()
    library.findMember("M002")?.displayTransactions()

    // 8. Pengembalian (Skenario B)
    println("\n--- EKSEKUSI PENGEMBALIAN ---")
    library.returnItem("M001", "B001", daysLate = 0)
    library.returnItem("M002", "J001", daysLate = 3)

    // 9. Tampilkan Transaksi Setelah Pengembalian
    library.findMember("M001")?.displayTransactions()
    library.findMember("M002")?.displayTransactions()

    // 10. Demonstrasi Polimorfisme
    println("\n--- DEMONSTRASI POLIMORFISME ---")
    val sampleList: List<Item> = listOf(
        Book("X01", "Test Book", 2024, "Author X", 100, "Gen"),
        Journal("Y01", "Test Journal", 2024, "Pub Y", 1, 1),
        DVD("Z01", "Test DVD", 2024, "Dir Z", 90, "Gen")
    )
    for (item in sampleList) {
        println("${item.getItemType()} - Denda/hari: Rp${item.calculateFinePerDay()}")
    }

    // 11. Demonstrasi Sealed Class
    println("\n--- DEMONSTRASI SEALED CLASS ---")
    val statuses: List<TransactionStatus> = listOf(
        TransactionStatus.Borrowed,
        TransactionStatus.Returned,
        TransactionStatus.Overdue(5),
        TransactionStatus.Cancelled
    )
    for (status in statuses) {
        println(status.display())
    }

    // 12. Demonstrasi Smart Casting & as?
    println("\n--- DEMONSTRASI SMART CASTING ---")
    val targetItem = library.findItem("B001")
    if (targetItem != null) {
        if (targetItem is Book) {
            println("Item B001 adalah Buku dengan penulis: ${targetItem.author}")
        }
        val castedDVD = targetItem as? DVD
        if (castedDVD != null) {
            println("Casting berhasil ke DVD: ${castedDVD.director}")
        } else {
            println("Casting ke DVD bernilai null (gagal karena objek asli adalah Buku).")
        }
    }

    // 13. Demonstrasi Enkapsulasi
    println("\n--- DEMONSTRASI ENKAPSULASI ---")
    println("Properti 'isAvailable' pada item bersifat private set, sehingga tidak bisa diubah sembarangan dari luar kelas.")
    println("Properti 'email' dan 'phone' pada Member bersifat private val, akses wajib melalui fungsi getter getEmail() / getPhone().")

    // 14. Tampilkan Laporan Akhir
    library.displayReport()
}