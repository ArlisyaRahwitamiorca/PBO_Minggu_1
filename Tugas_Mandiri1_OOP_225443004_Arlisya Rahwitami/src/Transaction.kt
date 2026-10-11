import java.time.LocalDate

class Transaction(
    val id: String,
    val item: Item,
    val member: Member,
    val borrowDate: String = LocalDate.now().toString(),
    var status: TransactionStatus = TransactionStatus.Borrowed
) {
    fun returnItem(daysLate: Int): Double {
        if (status.isFinal()) return 0.0

        val fine = item.returnItem(daysLate)
        status = if (daysLate > 0) {
            TransactionStatus.Overdue(daysLate)
        } else {
            TransactionStatus.Returned
        }
        return fine
    }

    fun cancel() {
        if (!status.isFinal()) {
            status = TransactionStatus.Cancelled
            item.returnItem(0)
        }
    }

    fun displayTransaction() {
        println(String.format("  > [%s] Trx ID: %-8s | Item: %-25s | Status: %s",
            borrowDate, id, item.title, status.display()))
    }
}