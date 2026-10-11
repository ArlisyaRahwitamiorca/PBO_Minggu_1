class Book(
    id: String,
    title: String,
    year: Int,
    val author: String,
    val pages: Int,
    val genre: String
) : Item(id, title, year) {

    override fun calculateFinePerDay(): Double = 2000.0
    override fun getItemType(): String = "Buku"
    override fun getMaxBorrowDays(): Int = 14

    override fun displayInfo() {
        super.displayInfo()
        println(String.format("        -> Penulis: %s | Halaman: %d | Genre: %s", author, pages, genre))
    }
}