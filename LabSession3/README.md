# Kasir Kantin UNS (Java Multithreading & Swing GUI)

Aplikasi kasir sederhana berbasis jendela untuk Praktikum PBO Kelas 3B, Pertemuan 11 (Multithreading) dan Pertemuan 12 (GUI Programming). Kasir memasukkan menu, harga, dan jumlah, lalu aplikasi menghitung total bayar. Ada juga simulasi dua kasir yang melayani pelanggan bersamaan dan fitur cetak struk yang tidak membuat jendela macet.

## Fitur

- **Tahap 1:** Class `Pesanan` untuk menghitung subtotal dan total. Subtotal di atas Rp 50.000 mendapat diskon 10%.
- **Tahap 2:** Dua kasir (Dina dan Eko) sebagai `Thread` yang melayani pelanggan bersamaan, dengan `join()` agar `main` menunggu keduanya selesai.
- **Tahap 3:** GUI Java Swing (`JFrame`, layout `null` dengan `setBounds()`), validasi input dengan `JOptionPane` dan `try-catch` untuk `NumberFormatException`.
- **Tahap 4:** Tombol Cetak Struk yang menunggu 2 detik di thread baru, sehingga jendela tidak macet.

## Struktur Proyek

```
.
├── lab/
│   └── kasir/
│       ├── Pesanan.java
│       ├── Kasir.java
│       ├── MainKasir.java
│       └── KasirFrame.java
└── README.md
```

## Penjelasan Class

| Class | Peran |
|---|---|
| `Pesanan` | Logika pesanan: `tambahItem()`, `getSubtotal()`, `hitungTotal()`, `reset()`. Atribut `subtotal` bersifat `private`. |
| `Kasir` | Turunan `Thread`. Method `run()` mencetak "Kasir <nama> melayani pelanggan ke-i" sebanyak 5 kali. |
| `MainKasir` | Menguji Tahap 1 dan Tahap 2 di konsol, lalu membuka GUI. |
| `KasirFrame` | Jendela Swing untuk Tahap 3 dan Tahap 4. |

## Cara Menjalankan

Pastikan JDK 11 atau lebih baru sudah terpasang. Jalankan dari folder utama proyek:

```bash
javac lab/kasir/*.java
java lab.kasir.MainKasir
```

## Hasil yang Diharapkan

### Tahap 1: Uji `Pesanan`

| Menu | Harga | Jumlah |
|---|---|---|
| Nasi Goreng | 15.000 | 2 |
| Es Teh | 5.000 | 3 |
| Ayam Bakar | 25.000 | 1 |

```
=== TAHAP 1: Uji Pesanan ===
Subtotal : 45000.0
Total    : 45000.0
Subtotal : 70000.0
Total    : 63000.0
Setelah reset, subtotal: 0.0
```

### Tahap 2: Dua Kasir

Output Dina dan Eko muncul bergantian dan urutannya bisa berbeda setiap dijalankan, karena penjadwalan thread ditentukan oleh JVM dan sistem operasi. Kalimat berikut selalu muncul paling akhir karena `join()`:

```
Semua pelanggan selesai dilayani
```

### Tahap 3: GUI Kasir

Dengan data uji Tahap 1, label total menampilkan:

```
Total Bayar : Rp 63.000
```

Validasi yang ditangani:
- Field kosong menampilkan pesan "Semua field harus diisi!".
- Harga atau jumlah bukan angka menampilkan pesan "Harga dan jumlah harus berupa angka!".
- Harga negatif atau jumlah 0 ke bawah juga ditolak.

### Tahap 4: Cetak Struk

1. Tombol Cetak Struk ditekan, label status berubah menjadi "Mencetak struk...".
2. Penantian 2 detik berjalan di thread baru, sehingga tombol Tambah dan Hitung Total tetap bisa ditekan.
3. Setelah 2 detik, label berubah menjadi "Struk selesai".

## Catatan Teknis

- Perubahan komponen Swing dari thread lain dikembalikan ke Event Dispatch Thread dengan `SwingUtilities.invokeLater()`.
- Tombol Cetak Struk dinonaktifkan sementara selama proses cetak agar tidak terpicu berulang kali.
- Format rupiah memakai titik sebagai pemisah ribuan (contoh: `63.000`).

