sealed class TransactionStatus {
    object Borrowed : TransactionStatus()
    object Returned : TransactionStatus()
    data class Overdue(val daysLate: Int) : TransactionStatus()
    object Cancelled : TransactionStatus()

    fun display(): String {
        return when (this) {
            is Borrowed -> "[📖] Sedang Dipinjam"
            is Returned -> "[✔] Sudah Dikembalikan"
            is Overdue -> "[⚠] Terlambat ($daysLate hari)"
            is Cancelled -> "[❌] Dibatalkan"
        }
    }

    fun isFinal(): Boolean = this is Returned || this is Cancelled
}