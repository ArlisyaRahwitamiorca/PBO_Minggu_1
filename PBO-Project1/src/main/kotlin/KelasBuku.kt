class Buku(
    val judul: String,
    val penulis: String,
    val tahunTerbit: Int,
    var isDipinjam: Boolean = false,
    var peminjam: String? = null
) {
    fun pinjam(namaPeminjam: String) {
        if (!isDipinjam) {
            isDipinjam = true
            peminjam = namaPeminjam
            println("Buku \"$judul\" berhasil dipinjam oleh $namaPeminjam.")
        } else {
            println("Buku \"$judul\" sedang dipinjam oleh ${peminjam ?: "orang lain"}.")
        }
    }

    fun kembalikan() {
        if (isDipinjam) {
            println("Buku \"$judul\" telah dikembalikan oleh $peminjam.")
            isDipinjam = false
            peminjam = null
        } else {
            println("Buku \"$judul\" saat ini tidak sedang dipinjam.")
        }
    }

    fun tampilkanInfo() {
        println("=== Informasi Buku ===")
        println("Judul        : $judul")
        println("Penulis      : $penulis")
        println("Tahun Terbit : $tahunTerbit")
        println("Status       : ${if (isDipinjam) "Dipinjam" else "Tersedia"}")
        println("Peminjam     : ${peminjam ?: "-"}")
        println("----------------------")
    }
}

// Main function untuk menguji kelas Buku
fun main() {
    // 1. Membuat objek buku (isDipinjam dan peminjam menggunakan nilai default)
    val buku1 = Buku("Pemrograman Kotlin", "Andi Prasetyo", 2023)

    // Tampilkan kondisi awal
    buku1.tampilkanInfo()

    // 2. Meminjam buku
    buku1.pinjam("Arlisya")
    buku1.tampilkanInfo()

    // 3. Mengembalikan buku
    buku1.kembalikan()
    buku1.tampilkanInfo()
}