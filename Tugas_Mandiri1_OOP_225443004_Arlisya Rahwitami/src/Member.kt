class Member(
    val id: String,
    val name: String,
    private val email: String,
    private val phone: String
) {
    private val transactions: MutableList<Transaction> = mutableListOf()

    val transactionCount: Int
        get() = transactions.size

    val totalFines: Double
        get() = transactions.sumOf { tx ->
            val status = tx.status
            if (status is TransactionStatus.Overdue) {
                status.daysLate * tx.item.calculateFinePerDay()
            } else 0.0
        }

    val activeBorrows: Int
        get() = transactions.count { it.status is TransactionStatus.Borrowed }

    fun getEmail(): String = email
    fun getPhone(): String = phone

    fun borrowItem(item: Item): Transaction? {
        if (!item.isAvailable || activeBorrows >= 3) return null

        item.borrow()
        val newTx = Transaction(
            id = "TRX-${System.currentTimeMillis().toString().takeLast(5)}",
            item = item,
            member = this
        )
        transactions.add(newTx)
        return newTx
    }

    fun returnItem(item: Item, daysLate: Int = 0): Double {
        val activeTx = transactions.find { it.item.id == item.id && it.status is TransactionStatus.Borrowed }
        return activeTx?.returnItem(daysLate) ?: 0.0
    }

    fun getTransactions(): List<Transaction> = transactions.toList()

    fun displayInfo() {
        println(String.format("| %-5s | %-15s | %-22s | %-13s | %-6d | Rp %-10.0f |",
            id, name, email, phone, activeBorrows, totalFines))
    }

    fun displayTransactions() {
        println("\n--- Riwayat Transaksi Anggota: $name ---")
        if (transactions.isEmpty()) {
            println("  (Belum ada transaksi)")
        } else {
            for (tx in transactions) {
                tx.displayTransaction()
            }
        }
    }
}