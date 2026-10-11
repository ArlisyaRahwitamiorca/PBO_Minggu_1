# Sistem Manajemen Perpustakaan Digital (Kotlin OOP)

## Identitas Mahasiswa
- **Nama:** [Arlisya Rahwitami]
- **NIM:** [225443004]
- **Kelas:** [2 AEC1]

## Deskripsi Singkat Program
Program ini adalah aplikasi konsol berbasis Kotlin untuk mensimulasikan sistem manajemen perpustakaan digital secara modular. Aplikasi menerapkan 4 pilar Pemrograman Berorientasi Objek (OOP) — Inheritance, Encapsulation, Polymorphism, dan Abstraction — serta fitur modern Kotlin seperti *Sealed Class*, *Smart Casting*, dan *Null Safety*.

## Cara Menjalankan Program
1. Buka folder proyek ini melalui IDE seperti **IntelliJ IDEA**.
2. Jalankan fungsi `main()` yang berada di dalam file `src/Main.kt`.

## Struktur Kelas Utama
- **`Item`**: Kelas abstrak induk untuk item perpustakaan.
- **`Book` / `Journal` / `DVD`**: Kelas turunan (*subclass*) dari `Item`.
- **`TransactionStatus`**: *Sealed Class* untuk status transaksi peminjaman.
- **`Transaction`**: Kelas yang menghubungkan relasi antara Item dan Member.
- **`Member`**: Kelas pengelola data anggota dan riwayat transaksinya.
- **`Library`**: Kelas pusat manajemen perpustakaan secara keseluruhan.