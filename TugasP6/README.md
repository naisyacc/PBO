# Program Pengelolaan Nilai Siswa (Java)

Program Java sederhana bergaya procedural/fungsional untuk mengelola data nilai siswa menggunakan **File I/O**. Program membaca data dari konsol, menyimpannya ke file teks `laporan_nilai.txt`, lalu membaca kembali file tersebut dan menampilkannya di konsol.

## Fitur

- Input jumlah siswa, nama, dan nilai melalui `Scanner`.
- Validasi input (jumlah harus > 0, nilai harus angka 0-100).
- Penentuan status kelulusan otomatis:
  - Nilai >= 70 → **Lulus**
  - Nilai < 70 → **Tidak Lulus**
- Penulisan laporan ke `laporan_nilai.txt` menggunakan `FileWriter` + `BufferedWriter`.
- Ringkasan di akhir file: rata-rata nilai kelas, jumlah siswa lulus, dan jumlah tidak lulus.
- Pembacaan ulang file menggunakan `FileReader` + `BufferedReader` (`readLine()` sampai `null`).
- Exception handling dengan `try-catch` (`IOException`) dan **try-with-resources** sehingga stream otomatis ditutup.

## Konsep yang Dipakai

| Konsep | Implementasi |
|---|---|
| Input konsol | `Scanner` |
| Tulis file | `FileWriter`, `BufferedWriter` |
| Baca file | `FileReader`, `BufferedReader` |
| Exception handling | `try-catch`, `IOException` |
| Resource management | `try-with-resources` |
| Struktur kode | Method `static` (procedural) |

## Struktur Proyek

```
.
├── NilaiSiswa.java
├── laporan_nilai.txt   (dibuat otomatis saat program dijalankan)
└── README.md
```

## Cara Menjalankan

Pastikan JDK 11 atau lebih baru sudah terpasang.

```bash
javac NilaiSiswa.java
java NilaiSiswa
```

## Contoh Penggunaan

**Input:**

```
Masukkan jumlah siswa: 3
Data siswa ke-1
  Nama siswa: Budi
  Nilai siswa ke-1 (0-100): 85
Data siswa ke-2
  Nama siswa: Siti
  Nilai siswa ke-2 (0-100): 65.5
Data siswa ke-3
  Nama siswa: Andi
  Nilai siswa ke-3 (0-100): 70
```

**Output:**

```
Data berhasil ditulis ke file laporan_nilai.txt

===== ISI FILE laporan_nilai.txt =====
=== LAPORAN NILAI SISWA ===
No   Nama                      Nilai    Status
----------------------------------------------------
1    Budi                      85.00    Lulus
2    Siti                      65.50    Tidak Lulus
3    Andi                      70.00    Lulus
----------------------------------------------------
RINGKASAN
Rata-rata nilai kelas : 73.50
Jumlah siswa lulus    : 2
Jumlah tidak lulus    : 1
```

## Alur Program

1. `main()` meminta jumlah siswa.
2. `tulisLaporan()` membaca data tiap siswa, menentukan status, dan menulis ke file beserta ringkasannya.
3. `bacaLaporan()` membuka kembali file dan mencetak isinya baris demi baris.

## Catatan

- Setiap kali program dijalankan, isi `laporan_nilai.txt` akan ditimpa dengan data baru.
- File dibuat di direktori kerja (tempat program dijalankan).

