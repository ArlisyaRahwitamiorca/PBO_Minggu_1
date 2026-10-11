//class Mahasiswa(
//    val nim: String,
//    val nama: String,
//    val jurusan: String,
//    val ipk: Double
//){
//    fun hitungpredikat(): String{
//        return when{
//            ipk >= 3.5 -> "Cumlaude"
//            ipk >= 3.0 -> "Sangat Memuaskan"
//            ipk >= 2.75 -> "Memuaskan"
//            else -> "Cukup"
//        }
//    }
//
//    fun tampil(){
//        println("==============================")
//        println("DATA MAHASISWA")
//        println("==============================")
//        println("NIM: $nim")
//        println("NAMA: $nama")
//        println("Jurusan: $jurusan")
//        println("IPK: $ipk")
//        println("Predikat: ${hitungpredikat()}")
//        println("==============================")
//    }
//}
//
//fun main(){
//    val daftarMahasiswa = mutableListOf<Mahasiswa>()
//    val jumlahData = 3
//    var counter = 1
//
//    println("=====INPUT DATA MAHASISWA=====")
//
//    while (counter <= jumlahData){
//        println("\n--- Data Mahasiswa ke-$counter ---")
//
//        print("Masukan NIM: ")
//        val nim = readln()
//
//        print("Masukan Nama: ")
//        val nama = readln()
//
//        print("Masukan Jurusan: ")
//        val jurusan = readln()
//
//        print("Masukan IPK: ")
//        val ipkInput= readln()
//        val ipk = ipkInput.toDoubleOrNull() ?: 0.0
//
//        val mhs = Mahasiswa(nim, nama, jurusan, ipk)
//        daftarMahasiswa.add(mhs)
//
//        counter++
//    }
//
//    println("\n--- Output ---")
//    for (mhs in daftarMahasiswa){
//        mhs.tampil()
//        println()
//    }
//}


//yang lainnya

//class Mahasiswa(
//    val nim: String,
//    val nama: String,
//    var jurusan: String,
//    var ipk: Double
//) {
//    init {
//        println("✅ Mahasiswa $nama dengan NIM $nim berhasil didaftarkan!")
//
//        if (ipk < 0.0 || ipk > 4.0) {
//            println("⚠️ PERINGATAN: IPK $ipk tidak valid! IPK harus antara 0.0 - 4.0")
//        }
//    }
//
//    fun tampilkan() {
//        println("=".repeat(50))
//        println("📋 DATA MAHASISWA")
//        println("=".repeat(50))
//        println("NIM     : $nim")
//        println("Nama    : $nama")
//        println("Jurusan : $jurusan")
//        println("IPK     : $ipk")
//        println("Predikat: ${hitungPredikat()}")
//        println("Status  : ${if (isLulus()) "✅ LULUS" else "❌ TIDAK LULUS"}")
//        println("=".repeat(50))
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
//    println("=".repeat(50))
//    println("🎓 SISTEM MANAJEMEN MAHASISWA INTERAKTIF")
//    println("=".repeat(50))
//    println()
//
//    val daftarMahasiswa = mutableListOf<Mahasiswa>()
//    val jumlahData = 3
//    var counter = 1
//
//    while (counter <= jumlahData) {
//        println("--- Input Data Mahasiswa ke-$counter ---")
//
//        print("Masukkan NIM    : ")
//        val nim = readln()
//
//        print("Masukkan Nama   : ")
//        val nama = readln()
//
//        print("Masukkan Jurusan: ")
//        val jurusan = readln()
//
//        print("Masukkan IPK    : ")
//        val ipkInput = readln()
//        val ipk = ipkInput.toDoubleOrNull() ?: 0.0
//
//        val mhs = Mahasiswa(nim, nama, jurusan, ipk)
//        daftarMahasiswa.add(mhs)
//
//        println()
//        counter++
//    }
//
//    println("=".repeat(50))
//    println("📋 HASIL REKAPITULASI DATA MAHASISWA")
//    println("=".repeat(50))
//    println()
//
//    for (mhs in daftarMahasiswa) {
//        mhs.tampilkan()
//        println()
//    }
//
//    println("=".repeat(50))
//    println("🏁 PROGRAM SELESAI")
//    println("=".repeat(50))
//}




//yang lainnya lagi
//class Mahasiswa(
//    val nim: String,
//    val nama: String,
//    var jurusan: String,
//    var ipk: Double
//) {
//    // Method untuk menentukan predikat berdasarkan IPK
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
//    // Method mengecek apakah lulus
//    fun isLulus(): Boolean {
//        return ipk >= 2.0
//    }
//
//    // Method tampilkan data
//    fun tampilkan() {
//        println("=".repeat(50))
//        println("📋 DATA MAHASISWA")
//        println("=".repeat(50))
//        println("NIM      : $nim")
//        println("Nama     : $nama")
//        println("Jurusan  : $jurusan")
//        println("IPK      : $ipk")
//        println("Predikat : ${hitungPredikat()}")
//        println("Status   : ${if (isLulus()) "✅ LULUS" else "❌ TIDAK LULUS"}")
//        println("=".repeat(50))
//    }
//}
//
//fun main() {
//    val daftarMahasiswa = mutableListOf<Mahasiswa>()
//    val jumlahData = 3
//    var counter = 1
//
//    println("=".repeat(50))
//    println("🎓 INPUT DATA MAHASISWA")
//    println("=".repeat(50))
//
//    while (counter <= jumlahData) {
//        println("\n--- Data Mahasiswa ke-$counter ---")
//
//        print("Masukkan NIM    : ")
//        val nim = readln()
//
//        print("Masukkan Nama   : ")
//        val nama = readln()
//
//        print("Masukkan Jurusan: ")
//        val jurusan = readln()
//
//        print("Masukkan IPK    : ")
//        val ipkInput = readln()
//        val ipk = ipkInput.toDoubleOrNull() ?: 0.0
//
//        // Objek dibuat tanpa memicu pesan init
//        val mhs = Mahasiswa(nim, nama, jurusan, ipk)
//        daftarMahasiswa.add(mhs)
//
//        counter++
//    }
//
//    // Output akhir
//    println("\n" + "=".repeat(50))
//    println("📋 HASIL DATA MAHASISWA")
//    println("=".repeat(50))
//
//    for (mhs in daftarMahasiswa) {
//        mhs.tampilkan()
//        println()
//    }
//}

//yang lain lain lainnya

