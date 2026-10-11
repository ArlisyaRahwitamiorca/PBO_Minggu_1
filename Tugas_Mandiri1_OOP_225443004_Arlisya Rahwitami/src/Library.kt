class Library(val name: String) {
    private val items: MutableList<Item> = mutableListOf()
    private val members: MutableList<Member> = mutableListOf()
    private val transactions: MutableList<Transaction> = mutableListOf()

    val totalItems: Int get() = items.size
    val availableItems: Int get() = items.count { it.isAvailable }
    val totalMembers: Int get() = members.size
    val totalTransactions: Int get() = transactions.size

    fun addItem(item: Item) { items.add(item) }
    fun addItems(vararg newItems: Item) { for (item in newItems) items.add(item) }

    fun findItem(id: String): Item? = items.find { it.id.equals(id, ignoreCase = true) }

    fun registerMember(id: String, name: String, email: String, phone: String): Boolean {
        if (members.any { it.id.equals(id, ignoreCase = true) }) return false
        members.add(Member(id, name, email, phone))
        return true
    }

    fun findMember(id: String): Member? = members.find { it.id.equals(id, ignoreCase = true) }

    fun borrowItem(memberId: String, itemId: String): Transaction? {
        val member = findMember(memberId)
        val item = findItem(itemId)
        if (member == null || item == null || !item.isAvailable) return null

        val tx = member.borrowItem(item)
        if (tx != null) transactions.add(tx)
        return tx
    }

    fun returnItem(memberId: String, itemId: String, daysLate: Int = 0): Double {
        val member = findMember(memberId)
        val item = findItem(itemId)
        if (member == null || item == null) return 0.0
        return member.returnItem(item, daysLate)
    }

    fun displayAllItems() {
        println("\n===============================================================================")
        println("                        KATALOG SEMUA ITEM PERPUSTAKAAN                        ")
        println("===============================================================================")
        println(String.format("| %-5s | %-8s | %-30s | %-6s | %-10s |", "ID", "Kategori", "Judul Item", "Tahun", "Status"))
        println("+-------+----------+--------------------------------+--------+------------+")
        for (item in items) {
            item.displayInfo()
        }
        println("+-------+----------+--------------------------------+--------+------------+")
        println("Total Item: $totalItems | Tersedia: $availableItems | Dipinjam: ${totalItems - availableItems}")
    }

    fun displayAvailableItems() {
        println("\n==========================================================")
        println("                 DAFTAR ITEM YANG TERSEDIA                ")
        println("==========================================================")
        println(String.format("| %-5s | %-8s | %-30s |", "ID", "Kategori", "Judul Item"))
        println("+-------+----------+--------------------------------+")
        val availableList = items.filter { it.isAvailable }
        if (availableList.isEmpty()) {
            println("|                  Tidak ada item tersedia               |")
        } else {
            for (item in availableList) {
                println(String.format("| %-5s | %-8s | %-30s |", item.id, item.getItemType(), item.title))
            }
        }
        println("+-------+----------+--------------------------------+")
    }

    fun displayAllMembers() {
        println("\n=========================================================================================================")
        println("                                      DAFTAR ANGGOTA PERPUSTAKAAN                                       ")
        println("=========================================================================================================")
        println(String.format("| %-5s | %-15s | %-22s | %-13s | %-6s | %-13s |", "ID", "Nama Anggota", "Email", "Telepon", "Pinjam", "Total Denda"))
        println("+-------+-----------------+------------------------+---------------+--------+---------------+")
        for (member in members) {
            member.displayInfo()
        }
        println("+-------+-----------------+------------------------+---------------+--------+---------------+")
    }

    fun displayReport() {
        val totalDendaSemua = transactions.sumOf { tx ->
            val status = tx.status
            if (status is TransactionStatus.Overdue) status.daysLate * tx.item.calculateFinePerDay() else 0.0
        }

        println("\n==================================================")
        println("           LAPORAN RINGKASAN PERPUSTAKAAN         ")
        println("==================================================")
        println(String.format("Nama Perpustakaan : %s", name))
        println(String.format("Total Item        : %d", totalItems))
        println(String.format("Item Tersedia     : %d", availableItems))
        println(String.format("Item Dipinjam     : %d", totalItems - availableItems))
        println(String.format("Total Anggota     : %d", totalMembers))
        println(String.format("Total Transaksi   : %d", totalTransactions))
        println(String.format("Akumulasi Denda   : Rp %,.0f", totalDendaSemua))
        println("==================================================")
    }
}