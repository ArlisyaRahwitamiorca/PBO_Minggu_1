abstract class Item(
    val id: String,
    val title: String,
    val year: Int
) {
    var isAvailable: Boolean = true
        private set

    abstract fun calculateFinePerDay(): Double
    abstract fun getItemType(): String
    abstract fun getMaxBorrowDays(): Int

    fun borrow(): Boolean {
        if (isAvailable) {
            isAvailable = false
            return true
        }
        return false
    }

    fun returnItem(daysLate: Int = 0): Double {
        isAvailable = true
        return daysLate * calculateFinePerDay()
    }

    open fun displayInfo() {
        val statusStr = if (isAvailable) "Tersedia" else "Dipinjam"
        println(String.format("| %-5s | %-8s | %-30s | %-6d | %-10s |", id, getItemType(), title, year, statusStr))
    }
}