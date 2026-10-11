//fun main() {
//    class Mahasiswa(val nim: String, val nama: String, var umur: Int) {
//        fun tampilkan() {
//            println("NIM: $nim, Nama: $nama, Umur: $umur")
//        }
//
//        fun bertambahUmur() {
//            umur++
//        }
//    }
//    val mhs = Mahasiswa("12345", "Budi Santoso", 20)
//    mhs.tampilkan()
//    mhs.bertambahUmur()
//    println("Umur sekarang: ${mhs.umur}")
//}

// File: Mahasiswa.kt

/**
 * Kelas Mahasiswa merepresentasikan data mahasiswa
 * @param nim Nomor Induk Mahasiswa (String)
 * @param nama Nama lengkap mahasiswa (String)
 * @param jurusan Jurusan mahasiswa (String)
 * @param ipk Indeks Prestasi Kumulatif (Double)
 */
class Mahasiswa(
    val nim: String,
    val nama: String,
    var jurusan: String,
    var ipk: Double
) {
    /**
     * Menampilkan seluruh data mahasiswa
     */
    fun tampilkan() {
        println("=" .repeat(40))
        println("DATA MAHASISWA")
        println("=" .repeat(40))
        println("NIM     : $nim")
        println("Nama    : $nama")
        println("Jurusan : $jurusan")
        println("IPK     : $ipk")
        println("Predikat: ${predikat()}")
        println("=" .repeat(40))
    }

    /**
     * Menentukan predikat kelulusan berdasarkan IPK
     * @return String predikat kelulusan
     */
    fun predikat(): String {
        return when {
            ipk >= 3.5 -> "Cumlaude"
            ipk >= 3.0 -> "Sangat Memuaskan"
            ipk >= 2.5 -> "Memuaskan"
            else -> "Perlu Perbaikan"
        }
    }
}

/**
 * Fungsi utama program
 */
fun main() {
    // Membuat 3 objek mahasiswa
    val mhs1 = Mahasiswa("TI2024001", "Budi Santoso", "Teknik Informatika", 3.75)
    val mhs2 = Mahasiswa("TI2024002", "Siti Rahayu", "Sistem Informasi", 3.20)
    val mhs3 = Mahasiswa("TI2024003", "Ahmad Fauzi", "Teknik Komputer", 2.80)

    // Menampilkan data semua mahasiswa
    mhs1.tampilkan()
    mhs2.tampilkan()
    mhs3.tampilkan()

    // Demonstrasi perubahan data
    println("\n--- DEMONSTRASI PERUBAHAN DATA ---")
    println("Sebelum: ${mhs1.nama} - Jurusan: ${mhs1.jurusan}")
    mhs1.jurusan = "Teknik Informatika (Mobile)"
    println("Sesudah: ${mhs1.nama} - Jurusan: ${mhs1.jurusan}")

    // Demonstrasi perubahan IPK
    println("\n--- DEMONSTRASI PERUBAHAN IPK ---")
    println("IPK ${mhs2.nama}: ${mhs2.ipk} -> Predikat: ${mhs2.predikat()}")
    mhs2.ipk = 3.55
    println("IPK ${mhs2.nama} (baru): ${mhs2.ipk} -> Predikat: ${mhs2.predikat()}")
}


//fun main() {
//    println("Hello, World!")
//}