//import kotlin.math.PI
//
//// 1. KELAS INDUK: Shape
//open class Shape {
//    open fun area(): Double = 0.0
//    open fun perimeter(): Double = 0.0
//    open fun name(): String = "Bentuk Umum"
//}
//
//// 2. SUBCLASS: Circle
//class Circle(val radius: Double) : Shape() {
//    override fun area(): Double = PI * radius * radius
//    override fun perimeter(): Double = 2 * PI * radius
//    override fun name(): String = "Lingkaran"
//}
//
//// 3. SUBCLASS: Rectangle
//open class Rectangle(val width: Double, val height: Double) : Shape() {
//    override fun area(): Double = width * height
//    override fun perimeter(): Double = 2 * (width + height)
//    override fun name(): String = "Persegi Panjang"
//}
//
//// 4. SUBCLASS: Square (mewarisi Rectangle)
//class Square(val side: Double) : Rectangle(side, side) {
//    override fun name(): String = "Persegi"
//}
//
//// 5. FUNGSI main()
//fun main() {
//    // Buat List<Shape> berisi berbagai shape
//    val shapes: List<Shape> = listOf(
//        Circle(7.0),
//        Rectangle(5.0, 4.0),
//        Square(6.0)
//    )
//
//    // Loop dan tampilkan area & perimeter setiap shape
//    for (shape in shapes) {
//        println("=== ${shape.name()} ===")
//        println("Luas (Area)       : ${shape.area()}")
//        println("Keliling (Perimeter): ${shape.perimeter()}")
//
//        // Gunakan when dengan 'is' untuk menampilkan tipe spesifik
//        print("Detail Spesifik   : ")
//        when (shape) {
//            is Circle -> println("Jari-jari (radius) = ${shape.radius}")
//            is Square -> println("Sisi (side) = ${shape.side}")
//            is Rectangle -> println("Lebar = ${shape.width}, Tinggi = ${shape.height}")
//        }
//        println("----------------------------------------")
//    }
//}







// 1. Sealed class untuk Status Pesanan
sealed class OrderStatus {
    data class Success(val data: String, val orderId: String) : OrderStatus()
    data class Failed(val reason: String, val errorCode: Int) : OrderStatus()
    data class Processing(val progress: Int) : OrderStatus()
    object Pending : OrderStatus()
}

// 2. Fungsi penangan status yang ekshaustif dengan format yang rapi
fun handleOrderStatus(status: OrderStatus): String {
    return when (status) {
        is OrderStatus.Success ->
            "[✔] STATUS : BERHASIL\n    ID Pesanan   : ${status.orderId}\n    Detail Data  : ${status.data}"
        is OrderStatus.Failed ->
            "[✖] STATUS : GAGAL\n    Alasan       : ${status.reason}\n    Kode Error   : ${status.errorCode}"
        is OrderStatus.Processing ->
            "[⏳] STATUS : DIPROSES\n    Progres      : ${status.progress}%"
        is OrderStatus.Pending ->
            "[ℹ] STATUS : MENUNGGU\n    Keterangan   : Menunggu Pembayaran atau Konfirmasi"
    }
}

// 3. Fungsi Utama (main)
fun main() {
    println("==================================================")
    println("              SISTEM PELACAKAN PESANAN            ")
    println("==================================================")

    // Membuat beberapa objek OrderStatus
    val pesanan1 = OrderStatus.Pending
    val pesanan2 = OrderStatus.Processing(50)
    val pesanan3 = OrderStatus.Success("Laptop Asus ROG", "ORD-12345")
    val pesanan4 = OrderStatus.Failed("Stok Habis", 404)

    val daftarPesanan = listOf(pesanan1, pesanan2, pesanan3, pesanan4)

    // Memanggil handleOrderStatus untuk masing-masing pesanan dengan format terstruktur
    for ((index, pesanan) in daftarPesanan.withIndex()) {
        println("\n--- Pelacakan Pesanan ke-${index + 1} ---")
        println(handleOrderStatus(pesanan))
    }

    println("\n==================================================")
    println("              PEMERIKSAAN SELESAI                 ")
    println("==================================================")
}
//
//
//
//
//
//
//// ==========================================
//// 6. SEALED CLASS EmployeeStatus
//// ==========================================
//sealed class EmployeeStatus {
//    object Active : EmployeeStatus()
//    object OnLeave : EmployeeStatus()
//    object Terminated : EmployeeStatus()
//
//    fun display(): String {
//        return when (this) {
//            is Active -> "Aktif"
//            is OnLeave -> "Cuti"
//            is Terminated -> "Berhenti"
//        }
//    }
//}
//
//// ==========================================
//// 1. KELAS INDUK: Employee (Karyawan)
//// ==========================================
//open class Employee(
//    val id: String,
//    val name: String,
//    val baseSalary: Double,
//    var status: EmployeeStatus = EmployeeStatus.Active
//) {
//    open fun calculateSalary(): Double = baseSalary
//    open fun calculateBonus(): Double = 0.0
//    open fun getRole(): String = "Employee"
//    open fun getSpecialInfo(): String = "-"
//
//    open fun displayInfo(): String {
//        val totalGaji = calculateSalary() + calculateBonus()
//        return String.format(
//            "| %-5s | %-12s | %-18s | %-10s | Rp %11d | %-20s |",
//            id, getRole(), name, status.display(), totalGaji.toLong(), getSpecialInfo()
//        )
//    }
//}
//
//// ==========================================
//// 2. SUBCLASS: FullTimeEmployee
//// ==========================================
//class FullTimeEmployee(
//    id: String, name: String, baseSalary: Double,
//    val allowance: Double, val annualBonus: Double,
//    status: EmployeeStatus = EmployeeStatus.Active
//) : Employee(id, name, baseSalary, status) {
//    override fun calculateSalary(): Double = baseSalary + allowance
//    override fun calculateBonus(): Double = annualBonus / 12
//    override fun getRole(): String = "Full-Time"
//    override fun getSpecialInfo(): String = "Tunjangan: Rp${allowance.toLong()}"
//}
//
//// ==========================================
//// 3. SUBCLASS: PartTimeEmployee
//// ==========================================
//class PartTimeEmployee(
//    id: String, name: String,
//    val hourlyRate: Double, val hoursWorked: Int,
//    status: EmployeeStatus = EmployeeStatus.Active
//) : Employee(id, name, 0.0, status) {
//    override fun calculateSalary(): Double = hourlyRate * hoursWorked
//    override fun calculateBonus(): Double = 0.0
//    override fun getRole(): String = "Part-Time"
//    override fun getSpecialInfo(): String = "Jam Kerja: ${hoursWorked} jam"
//}
//
//// ==========================================
//// 4. SUBCLASS: ContractEmployee
//// ==========================================
//class ContractEmployee(
//    id: String, name: String, baseSalary: Double,
//    val contractDuration: Int, val projectBonus: Double,
//    status: EmployeeStatus = EmployeeStatus.Active
//) : Employee(id, name, baseSalary, status) {
//    override fun calculateSalary(): Double = baseSalary
//    override fun calculateBonus(): Double = if (contractDuration > 0) projectBonus / contractDuration else 0.0
//    override fun getRole(): String = "Contract"
//    override fun getSpecialInfo(): String = "Kontrak: ${contractDuration} bln"
//}
//
//// ==========================================
//// 5. KELAS: Company
//// ==========================================
//class Company(val name: String) {
//    private val employees: MutableList<Employee> = mutableListOf()
//
//    fun addEmployee(employee: Employee) {
//        employees.add(employee)
//    }
//
//    fun getTotalSalary(): Double = employees.sumOf { it.calculateSalary() }
//    fun getTotalBonus(): Double = employees.sumOf { it.calculateBonus() }
//
//    private fun printTableHeader() {
//        println("+-------+--------------+--------------------+------------+---------------+----------------------+")
//        println("| ID    | Peran        | Nama Karyawan      | Status     | Total Gaji    | Info Khusus          |")
//        println("+-------+--------------+--------------------+------------+---------------+----------------------+")
//    }
//
//    private fun printTableFooter() {
//        println("+-------+--------------+--------------------+------------+---------------+----------------------+")
//    }
//
//    fun displayAllEmployees() {
//        println("\n=== DAFTAR SELURUH KARYAWAN ($name) ===")
//        printTableHeader()
//        for (e in employees) {
//            println(e.displayInfo())
//        }
//        printTableFooter()
//    }
//
//    fun displaySalaryReport() {
//        val totalGaji = getTotalSalary()
//        val totalBonus = getTotalBonus()
//        val totalPengeluaran = totalGaji + totalBonus
//
//        println("\n==========================================================================")
//        println("                       LAPORAN GAJI PERUSAHAAN                            ")
//        println("==========================================================================")
//        println(String.format("Perusahaan        : %s", name))
//        println(String.format("Total Karyawan    : %d Orang", employees.size))
//        println(String.format("Total Gaji Pokok  : Rp %,15.0f", totalGaji))
//        println(String.format("Total Bonus       : Rp %,15.0f", totalBonus))
//        println("--------------------------------------------------------------------------")
//        println(String.format("Total Pengeluaran : Rp %,15.0f", totalPengeluaran))
//        println("==========================================================================")
//    }
//}
//
//// ==========================================
//// 6. FUNGSI main()
//// ==========================================
//fun main() {
//    val company = Company("PT Teknologi Maju")
//
//    val e1: Employee = FullTimeEmployee("FT01", "Budi", 8000000.0, 1500000.0, 12000000.0, EmployeeStatus.Active)
//    val e2: Employee = FullTimeEmployee("FT02", "Siti", 9000000.0, 2000000.0, 18000000.0, EmployeeStatus.OnLeave)
//
//    val e3: Employee = PartTimeEmployee("PT01", "Andi", 50000.0, 80, EmployeeStatus.Active)
//    val e4: Employee = PartTimeEmployee("PT02", "Dewi", 60000.0, 50, EmployeeStatus.Terminated)
//
//    val e5: Employee = ContractEmployee("CT01", "Rian", 6000000.0, 6, 6000000.0, EmployeeStatus.Active)
//    val e6: Employee = ContractEmployee("CT02", "Maya", 6500000.0, 12, 12000000.0, EmployeeStatus.Active)
//
//    company.addEmployee(e1)
//    company.addEmployee(e2)
//    company.addEmployee(e3)
//    company.addEmployee(e4)
//    company.addEmployee(e5)
//    company.addEmployee(e6)
//
//    company.displayAllEmployees()
//    company.displaySalaryReport()
//}