# Program Pengelolaan Nilai Mahasiswa (Java OOP)

Program Java berorientasi objek untuk mengelola data nilai mahasiswa. Program membaca input dari konsol, memvalidasi data dengan **Assertion** dan **Custom Exception**, menyimpan objek dalam **ArrayList**, mengolah **String/Character**, lalu menyimpan rekapitulasi ke file `laporan_nilai.txt` menggunakan **File I/O**.

## Fitur

- Input jumlah mahasiswa, nama, NIM, dan nilai melalui `Scanner`.
- Validasi data:
  - Nilai di luar rentang 0-100 melempar `NilaiInvalidException`.
  - NIM tidak boleh kosong (`assert`) dan hanya boleh berisi angka (`Character.isDigit()`).
- Nama dirapikan otomatis dengan `trim()` dan `toUpperCase()`.
- Pemetaan nilai ke grade huruf dan status kelulusan.
- Statistik kelas: rata-rata, jumlah lulus, dan jumlah tidak lulus.
- Laporan disimpan ke `laporan_nilai.txt` dengan `BufferedWriter` + `FileWriter` dan ditampilkan juga di konsol.

## Pemetaan Materi

| Materi | Implementasi |
|---|---|
| Package, class, constructor, `final` | Package `model` dan `app`, kelas `Mahasiswa`, konstanta `FINAL_PASSING_GRADE = 70.0` |
| Kondisional, looping, tipe data | `if-else` pada `getGrade()`, for-each untuk rata-rata, `double`/`boolean` (primitive) vs `Mahasiswa`/`String` (reference) |
| Exception, assertion, String, Character | `NilaiInvalidException`, `assert !nim.isEmpty()`, `trim()`, `toUpperCase()`, `Character.isDigit()` |
| Collection | `ArrayList<Mahasiswa>` dengan `add()`, `size()`, for-each |
| Console & File I/O | `Scanner`, `BufferedWriter`, `FileWriter`, try-with-resources |

## Skala Grade

| Nilai | Grade | Status |
|---|---|---|
| >= 85 | A | Lulus |
| >= 75 | B | Lulus |
| >= 70 | C | Lulus |
| < 70 | D | Tidak Lulus |

## Struktur Proyek

```
.
├── app/
│   └── Main.java
├── model/
│   ├── Mahasiswa.java
│   └── NilaiInvalidException.java
├── laporan_nilai.txt   (dibuat otomatis saat program dijalankan)
└── README.md
```

## Cara Menjalankan

Pastikan JDK 11 atau lebih baru sudah terpasang. Jalankan dari folder utama proyek:

```bash
javac model/*.java app/*.java
java -ea app.Main
```

> Flag `-ea` (enable assertions) diperlukan agar `assert` aktif. Tanpa flag ini, Java mengabaikan semua assertion.

## Contoh Penggunaan

**Input:**

```
Masukkan jumlah mahasiswa: 3
Data mahasiswa ke-1
  Nama  : budi santoso
  NIM   : 2021001
  Nilai : 88
Data mahasiswa ke-2
  Nama  : siti aminah
  NIM   : 2021002
  Nilai : 150
  Nilai tidak valid: 150.0 (harus di antara 0 dan 100). Ulangi input.
Data mahasiswa ke-2
  Nama  : siti aminah
  NIM   : 2021002
  Nilai : 65.5
Data mahasiswa ke-3
  Nama  : andi wijaya
  NIM   : 2021003
  Nilai : 72
```

**Isi `laporan_nilai.txt`:**

```
=== LAPORAN NILAI MAHASISWA ===
No   Nama                      NIM          Nilai    Grade  Status
------------------------------------------------------------------
1    BUDI SANTOSO              2021001      88.00    A      Lulus
2    SITI AMINAH               2021002      65.50    D      Tidak Lulus
3    ANDI WIJAYA               2021003      72.00    C      Lulus
------------------------------------------------------------------
STATISTIK KELAS
Jumlah mahasiswa      : 3
Rata-rata nilai       : 75.17
Jumlah lulus          : 2
Jumlah tidak lulus    : 1
```

## Alur Program

1. `Main` meminta jumlah mahasiswa.
2. Untuk tiap mahasiswa, input divalidasi (nama tidak kosong, NIM hanya angka, nilai 0-100). Jika salah, input diulang.
3. Objek `Mahasiswa` dibuat dan ditambahkan ke `ArrayList`.
4. Statistik dihitung dengan for-each, laporan dibuat, lalu ditulis ke `laporan_nilai.txt` dan ditampilkan di konsol.

## Catatan

- Setiap kali program dijalankan, isi `laporan_nilai.txt` akan ditimpa.
- File laporan dibuat di direktori tempat program dijalankan.
