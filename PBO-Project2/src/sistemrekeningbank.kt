/**
 * ============================================================
 * SISTEM REKENING BANK DENGAN ENKAPSULASI PENUH
 * ============================================================
 * Demonstrasi:
 * 1. Private setter untuk melindungi saldo
 * 2. Validasi di setiap metode
 * 3. Enkapsulasi data dan perilaku
 * ============================================================
 */

class BankAccount(
    private val accountNumber: String,
    private val ownerName: String
) {
    // ============================================================
    // PROPERTI
    // ============================================================

    /**
     * Saldo rekening — public getter, private setter
     *
     * private set berarti:
     * - Siapa pun bisa MEMBACA saldo (getter public)
     * - HANYA kelas ini yang bisa MENGUBAH saldo (setter private)
     */
    var balance: Long = 0
        private set

    /**
     * Riwayat transaksi — private total
     * Tidak ada akses dari luar sama sekali
     */
    private val transactionHistory = mutableListOf<String>()

    /**
     * Jumlah transaksi — computed property
     * Tidak ada backing field — dihitung dari riwayat
     */
    val transactionCount: Int
        get() = transactionHistory.size

    /**
     * Status rekening berdasarkan saldo — computed property
     */
    val status: String
        get() = when {
            balance > 100_000_000 -> "💎 Premium"
            balance > 10_000_000 -> "⭐ Gold"
            balance > 1_000_000 -> "🥈 Silver"
            balance > 0 -> "🥉 Bronze"
            else -> "⚫ Tidak Aktif"
        }

    // ============================================================
    // METODE
    // ============================================================

    /**
     * Menyetor uang ke rekening
     *
     * @param amount Jumlah uang yang disetor (harus > 0)
     * @return true jika berhasil, false jika gagal
     */
    fun deposit(amount: Long): Boolean {
        // Validasi: jumlah harus positif
        if (amount <= 0) {
            println("❌ ERROR: Jumlah deposit harus lebih dari 0")
            return false
        }

        // Update saldo (memanggil private setter)
        balance += amount

        // Catat riwayat
        transactionHistory.add("💰 DEPOSIT: +Rp ${formatRupiah(amount)}")
        println("✅ Deposit Rp ${formatRupiah(amount)} berhasil")
        return true
    }

    /**
     * Menarik uang dari rekening
     *
     * @param amount Jumlah uang yang ditarik (harus > 0 dan <= saldo)
     * @return true jika berhasil, false jika gagal
     */
    fun withdraw(amount: Long): Boolean {
        // Validasi: jumlah harus positif
        if (amount <= 0) {
            println("❌ ERROR: Jumlah penarikan harus lebih dari 0")
            return false
        }

        // Validasi: saldo harus mencukupi
        if (amount > balance) {
            println("❌ ERROR: Saldo tidak mencukupi")
            println("   Saldo: Rp ${formatRupiah(balance)}")
            println("   Dibutuhkan: Rp ${formatRupiah(amount)}")
            return false
        }

        // Update saldo
        balance -= amount

        // Catat riwayat
        transactionHistory.add("🏧 WITHDRAW: -Rp ${formatRupiah(amount)}")
        println("✅ Penarikan Rp ${formatRupiah(amount)} berhasil")
        return true
    }

    /**
     * Transfer uang ke rekening lain
     *
     * @param target Rekening tujuan
     * @param amount Jumlah uang yang ditransfer
     * @return true jika berhasil, false jika gagal
     */
    fun transfer(target: BankAccount, amount: Long): Boolean {
        // Validasi: tidak bisa transfer ke diri sendiri
        if (this === target) {
            println("❌ ERROR: Tidak bisa transfer ke rekening sendiri")
            return false
        }

        // Tarik dari rekening ini
        if (!withdraw(amount)) {
            return false
        }

        // Setor ke rekening tujuan
        target.deposit(amount)

        // Catat riwayat tambahan
        transactionHistory.add("🔄 TRANSFER KELUAR: -Rp ${formatRupiah(amount)} ke ${target.ownerName}")
        println("✅ Transfer Rp ${formatRupiah(amount)} ke ${target.ownerName} berhasil")
        return true
    }

    /**
     * Menampilkan informasi rekening
     */
    fun displayInfo() {
        println("=" .repeat(55))
        println("🏦 INFORMASI REKENING")
        println("=" .repeat(55))
        println("Nomor Rekening : $accountNumber")
        println("Nama Pemilik   : $ownerName")
        println("Saldo          : Rp ${formatRupiah(balance)}")
        println("Status         : $status")
        println("Total Transaksi: $transactionCount")
        println("=" .repeat(55))
    }

    /**
     * Menampilkan riwayat transaksi
     *
     * @param limit Jumlah transaksi yang ditampilkan (default: semua)
     */
    fun displayHistory(limit: Int = transactionHistory.size) {
        println("=" .repeat(55))
        println("📋 RIWAYAT TRANSAKSI (${minOf(limit, transactionHistory.size)} terakhir)")
        println("=" .repeat(55))

        if (transactionHistory.isEmpty()) {
            println("   Belum ada transaksi")
        } else {
            val start = maxOf(0, transactionHistory.size - limit)
            for (i in start until transactionHistory.size) {
                println("   ${i + 1}. ${transactionHistory[i]}")
            }
        }
        println("=" .repeat(55))
    }

    /**
     * Format Rupiah (helper method — private)
     */
    private fun formatRupiah(nominal: Long): String {
        val str = nominal.toString()
        val builder = StringBuilder()
        var count = 0
        for (i in str.length - 1 downTo 0) {
            builder.insert(0, str[i])
            count++
            if (count % 3 == 0 && i > 0) {
                builder.insert(0, ".")
            }
        }
        return builder.toString()
    }
}

/**
 * Fungsi utama — demo sistem perbankan
 */
fun main() {
    println("=" .repeat(55))
    println("🏦 DEMO SISTEM PERBANKAN DENGAN ENKAPSULASI")
    println("=" .repeat(55))
    println()

    // Buat beberapa rekening
    val accountBudi = BankAccount("1234567890", "Budi Santoso")
    val accountSiti = BankAccount("0987654321", "Siti Rahayu")

    // Tampilkan info awal
    println("--- INFO REKENING AWAL ---")
    accountBudi.displayInfo()
    println()
    accountSiti.displayInfo()
    println()

    // Lakukan transaksi
    println("--- MELAKUKAN TRANSAKSI ---")
    accountBudi.deposit(500000)
    accountBudi.deposit(250000)
    accountSiti.deposit(1000000)
    accountBudi.withdraw(100000)
    accountBudi.transfer(accountSiti, 200000)
    accountBudi.withdraw(700000)  // Gagal — saldo tidak cukup
    println()

    // Tampilkan info akhir
    println("--- INFO REKENING AKHIR ---")
    accountBudi.displayInfo()
    println()
    accountSiti.displayInfo()
    println()

    // Tampilkan riwayat
    accountBudi.displayHistory(5)
    println()
    accountSiti.displayHistory()

    // Demonstrasi bahwa saldo tidak bisa diubah langsung dari luar
    println()
    println("--- DEMONSTRASI ENKAPSULASI ---")
    println("Mencoba mengubah saldo langsung dari luar...")
    // accountBudi.balance = 9999999  // ❌ ERROR: Cannot assign to 'balance': the setter is private
    println("✅ Saldo TIDAK BISA diubah langsung dari luar — enkapsulasi berhasil!")

    println()
    println("=" .repeat(55))
    println("🏁 PROGRAM SELESAI")
    println("=" .repeat(55))
}