//01_materi_pertemuan1
//class Mahasiswa(
//    val nim: String,
//    val nama: String,
//    var jurusan: String,
//    var ipk: Double
//) {
//    // Blok inisialisasi - dijalankan saat objek dibuat
//    init {
//        println("✅ Mahasiswa $nama dengan NIM $nim berhasil didaftarkan!")
//
//        // Validasi IPK
//        if (ipk < 0.0 || ipk > 4.0) {
//            println("⚠️ PERINGATAN: IPK $ipk tidak valid! IPK harus antara 0.0 - 4.0")
//        }
//    }
//
//    fun tampilkan() {
//        println("=" .repeat(50))
//        println("📋 DATA MAHASISWA")
//        println("=" .repeat(50))
//        println("NIM     : $nim")
//        println("Nama    : $nama")
//        println("Jurusan : $jurusan")
//        println("IPK     : $ipk")
//        println("Predikat: ${hitungPredikat()}")
//        println("=" .repeat(50))
//    }
//
//    fun hitungPredikat(): String {
//        return when {
//            ipk >= 3.5 -> "🏆 Cumlaude (Dengan Pujian)"
//            ipk >= 3.0 -> "⭐ Sangat Memuaskan"
//            ipk >= 2.5 -> "✅ Memuaskan"
//            ipk >= 2.0 -> "📖 Cukup"
//            else -> "📚 Perlu Perbaikan"
//        }
//    }
//
//    fun updateIpk(ipkBaru: Double): Boolean {
//        return if (ipkBaru in 0.0..4.0) {
//            ipk = ipkBaru
//            println("✅ IPK $nama berhasil diperbarui menjadi $ipkBaru")
//            true
//        } else {
//            println("❌ Gagal: IPK $ipkBaru tidak valid (harus 0.0 - 4.0)")
//            false
//        }
//    }
//
//    fun isLulus(): Boolean {
//        return ipk >= 2.0
//    }
//}
//
//fun main() {
//    println("=" .repeat(50))
//    println("🎓 SISTEM MANAJEMEN MAHASISWA")
//    println("=" .repeat(50))
//    println()
//
//
//    val mhs1 = Mahasiswa("TI2024001", "Budi Santoso", "Teknik Informatika", 3.75)
//    val mhs2 = Mahasiswa("TI2024002", "Siti Rahayu", "Sistem Informasi", 3.20)
//    val mhs3 = Mahasiswa("TI2024003", "Ahmad Fauzi", "Teknik Komputer", 1.80)
//
//    println()
//
//
//    mhs1.tampilkan()
//    println()
//    mhs2.tampilkan()
//    println()
//    mhs3.tampilkan()
//    println()
//
//    println("=" .repeat(50))
//    println("🔄 DEMONSTRASI UPDATE DATA")
//    println("=" .repeat(50))
//
//    println("Status kelulusan ${mhs3.nama}: ${if (mhs3.isLulus()) "✅ LULUS" else "❌ TIDAK LULUS"}")
//    mhs3.updateIpk(2.50)
//    println("Status kelulusan ${mhs3.nama} (baru): ${if (mhs3.isLulus()) "✅ LULUS" else "❌ TIDAK LULUS"}")
//    mhs3.tampilkan()
//
//    println()
//    println("=" .repeat(50))
//    println("🏁 PROGRAM SELESAI")
//    println("=" .repeat(50))
//}


//tugas pertemuan1

//class Mahasiswa(
//    val nim: String,
//    val nama: String,
//    var jurusan: String,
//    var ipk: Double
//) {
//    // mengambil 2 digit akhir nim untuk angkatan
//    val angkatan: Int = nim.takeLast(2).toInt()
//
//    // menampilkan data mahasiswa
//    fun tampilkan() {
//        println("NIM      : $nim")
//        println("Nama     : $nama")
//        println("Jurusan  : $jurusan")
//        println("IPK      : $ipk")
//        println("Angkatan : $angkatan")
//        println("Predikat : ${predikat()}")
//    }
//
//    // mengembalikan predikat berdasarkan IPK
//    fun predikat(): String {
//        if (ipk >= 3.5) {
//            return "Cumlaude"
//        } else if (ipk >= 3.0) {
//            return "Sangat Memuaskan"
//        } else if (ipk >= 2.5) {
//            return "Memuaskan"
//        } else if (ipk >= 2.0) {
//            return "Cukup"
//        } else {
//            return "Perlu Perbaikan"
//        }
//    }
//    // mengecek apakah mahasiswa lulus
//    fun isLulus(): Boolean {
//        return ipk >= 2.0
//    }
//    // mengupdate IPK dengan validasi
//    fun updateIpk(ipkBaru: Double) {
//        if (ipkBaru in 0.0..4.0) {
//            ipk = ipkBaru
//            println("IPK $nama berhasil diperbarui menjadi $ipkBaru")
//        } else {
//            println("Gagal update, IPK harus di antara 0.0 - 4.0")
//        }
//    }
//}
//fun main() {
//    // membuat 5 objek mahasiswa
//    val mhs1 = Mahasiswa("225443501", "Budi Santoso", "Teknik Informatika", 3.75)
//    val mhs2 = Mahasiswa("225443402", "Siti Rahayu", "Teknik Otomasi", 3.20)
//    val mhs3 = Mahasiswa("225443303", "Ahmad Fauzi", "Teknik Informatika", 1.80)
//    val mhs4 = Mahasiswa("225443204", "Dewi Lestari", "Teknik Mekatronika", 3.90)
//    val mhs5 = Mahasiswa("225443104", "Rian Hidayat", "Teknik Otomasi", 2.40)
//    // menyimpan semua mahasiswa dalam satu list agar mudah diproses
//    val daftarMahasiswa = listOf(mhs1, mhs2, mhs3, mhs4, mhs5)
//    // menampilkan data semua mahasiswa
//    println("DATA SELURUH MAHASISWA")
//    for (mhs in daftarMahasiswa) {
//        mhs.tampilkan()
//        println("")
//    }
//    // menampilkan daftar mahasiswa yang lulus
//    println("MAHASISWA LULUS")
//    for (mhs in daftarMahasiswa) {
//        if (mhs.isLulus()) {
//            println("${mhs.nama} - IPK: ${mhs.ipk}")
//        }
//    }
//    println("")
//    // menampilkan daftar mahasiswa dengan predikat Cumlaude
//    println("MAHASISWA CUMLAUDE")
//    for (mhs in daftarMahasiswa) {
//        if (mhs.ipk >= 3.5) {
//            println("${mhs.nama} - IPK: ${mhs.ipk}")
//        }
//    }
//    println("")
//    // demonstrasi update IPK untuk salah satu mahasiswa
//    println("UPDATE IPK")
//    println("Sebelum update:")
//    mhs3.tampilkan()
//    mhs3.updateIpk(2.60)
//    println("Sesudah update:")
//    mhs3.tampilkan()
//}