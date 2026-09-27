# Sistem Manajemen Perpustakaan Mini

## Fitur

- Tambah buku baru
- Lihat daftar buku
- Cari buku berdasarkan judul atau kategori
- Pinjam buku
- Kembalikan buku
- Lihat laporan perpustakaan
- Batas maksimal peminjaman 3 buku per anggota
- Menampilkan buku yang paling sering dipinjam, anggota paling aktif, dan kategori paling populer

## Struktur Folder

```text
library/
├── model/
│   ├── Book.java
│   └── Member.java
├── service/
│   └── LibraryService.java
├── exception/
│   ├── BookNotFoundException.java
│   ├── BookAlreadyBorrowedException.java
│   ├── BorrowLimitExceededException.java
│   └── MemberNotFoundException.java
└── main/
    └── MainApp.java
```

- `model` berisi class untuk data buku dan anggota.
- `service` berisi logika utama perpustakaan, misalnya peminjaman, pengembalian, pencarian, dan laporan.
- `exception` berisi custom exception untuk menangani kondisi tertentu.
- `main` berisi class utama dan menu program.

## Alur Program

1. Saat program dijalankan, sudah ada beberapa data awal berupa anggota dan buku.
2. Pengguna memilih menu yang tersedia.
3. Untuk menambah buku, pengguna memasukkan judul, penulis, tahun terbit, dan kategori.
4. Buku bisa dicari dengan memasukkan kata kunci judul atau kategori.
5. Untuk meminjam buku, pengguna memasukkan ID anggota dan judul buku.
6. Program akan mengecek apakah anggota terdaftar, buku tersedia, dan anggota belum melewati batas pinjaman.
7. Jika berhasil, status buku berubah menjadi dipinjam dan masuk ke daftar pinjaman anggota.
8. Saat buku dikembalikan, status buku kembali tersedia dan dihapus dari daftar pinjaman anggota.
9. Menu laporan menampilkan total pinjaman, buku terpopuler, anggota paling aktif, dan kategori paling banyak.

## Contoh Output

```text
===== SISTEM PERPUSTAKAAN MINI =====
1. Tambah Buku
2. Daftar Buku
3. Cari Buku
4. Pinjam Buku
5. Kembalikan Buku
6. Laporan Perpustakaan
7. Daftar Anggota
8. Keluar
Pilih menu: 2
```

Contoh daftar buku:

```text
=== DAFTAR BUKU ===
1. Belajar Java | Budi Santoso | 2021 | Pemrograman | Status: Tersedia | Dipinjam: 0x
2. Struktur Data | Rina Melati | 2020 | Informatika | Status: Tersedia | Dipinjam: 0x
3. Java OOP | Ahmad Fauzi | 2022 | Pemrograman | Status: Tersedia | Dipinjam: 0x
4. Laskar Pelangi | Andrea Hirata | 2005 | Novel | Status: Tersedia | Dipinjam: 0x
```

Contoh saat meminjam buku:

```text
=== PINJAM BUKU ===
ID anggota: M001
Judul buku: Belajar Java
Buku "Belajar Java" berhasil dipinjam oleh Andi.
```

Contoh saat buku sedang dipinjam:

```text
=== PINJAM BUKU ===
ID anggota: M002
Judul buku: Belajar Java
Error: Buku "Belajar Java" sedang dipinjam.
```

## Exception yang Digunakan

Program menggunakan beberapa custom exception supaya error lebih mudah dibedakan:

- `BookNotFoundException`, saat buku tidak ditemukan.
- `BookAlreadyBorrowedException`, saat buku sedang dipinjam.
- `BorrowLimitExceededException`, saat anggota sudah meminjam 3 buku.
- `MemberNotFoundException`, saat ID anggota tidak terdaftar.

## Konsep yang Dipakai

- Class, object, method, constructor, dan package
- Tipe data primitive seperti `int` dan `boolean`
- Tipe data reference seperti `String`, `ArrayList`, dan `HashMap`
- Percabangan `if-else` dan `switch-case`
- Perulangan `for`, `while`, dan `for-each`
- Exception handling menggunakan `try-catch`
- Assertion untuk validasi input sebelum transaksi
- Manipulasi String menggunakan `toLowerCase()`, `trim()`, `contains()`, dan `equals()`
