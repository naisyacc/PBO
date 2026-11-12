package app;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

import model.Mahasiswa;
import model.NilaiInvalidException;

/**
 * Kelas utama: membaca input, mengelola ArrayList<Mahasiswa>,
 * menghitung statistik, dan menyimpan laporan ke laporan_nilai.txt.
 *
 * Jalankan dengan flag -ea agar assertion aktif:
 *   java -ea app.Main
 */
public class Main {

    private static final String NAMA_FILE = "laporan_nilai.txt";
    private static final String NL = System.lineSeparator();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int jumlah = bacaJumlah(scanner);

        // Collection dinamis untuk menyimpan objek Mahasiswa
        ArrayList<Mahasiswa> daftarMahasiswa = new ArrayList<>();
        for (int i = 1; i <= jumlah; i++) {
            daftarMahasiswa.add(bacaMahasiswa(scanner, i));
        }
        System.out.println("\nTotal mahasiswa tersimpan di ArrayList: " + daftarMahasiswa.size());

        String laporan = buatLaporan(daftarMahasiswa);
        simpanLaporan(laporan);

        System.out.println("\n" + laporan);
        scanner.close();
    }

    // ---------- Input ----------

    private static int bacaJumlah(Scanner scanner) {
        while (true) {
            System.out.print("Masukkan jumlah mahasiswa: ");
            try {
                int n = Integer.parseInt(scanner.nextLine().trim());
                if (n > 0) {
                    return n;
                }
                System.out.println("Jumlah harus lebih dari 0.");
            } catch (NumberFormatException e) {
                System.out.println("Input harus berupa bilangan bulat.");
            }
        }
    }

    private static Mahasiswa bacaMahasiswa(Scanner scanner, int nomor) {
        while (true) {
            System.out.println("Data mahasiswa ke-" + nomor);

            System.out.print("  Nama  : ");
            String nama = scanner.nextLine();
            if (nama.trim().isEmpty()) {
                System.out.println("  Nama tidak boleh kosong. Ulangi input.");
                continue;
            }

            System.out.print("  NIM   : ");
            String nim = scanner.nextLine();
            if (!Mahasiswa.isNimValid(nim)) {
                System.out.println("  NIM harus berisi angka saja dan tidak boleh kosong. Ulangi input.");
                continue;
            }

            System.out.print("  Nilai : ");
            try {
                double nilai = Double.parseDouble(scanner.nextLine().trim().replace(',', '.'));
                return new Mahasiswa(nama, nim, nilai);
            } catch (NumberFormatException e) {
                System.out.println("  Nilai harus berupa angka. Ulangi input.");
            } catch (NilaiInvalidException e) {
                System.out.println("  " + e.getMessage() + ". Ulangi input.");
            }
        }
    }

    // ---------- Pengolahan data ----------

    private static String buatLaporan(ArrayList<Mahasiswa> daftar) {
        StringBuilder sb = new StringBuilder();
        String garis = "-".repeat(66);

        sb.append("=== LAPORAN NILAI MAHASISWA ===").append(NL);
        sb.append(String.format("%-4s %-25s %-12s %-8s %-6s %s",
                "No", "Nama", "NIM", "Nilai", "Grade", "Status")).append(NL);
        sb.append(garis).append(NL);

        double total = 0;
        int lulus = 0;
        int tidakLulus = 0;
        int no = 1;

        // for-each loop untuk menelusuri ArrayList
        for (Mahasiswa m : daftar) {
            sb.append(String.format(Locale.US, "%-4d %-25s %-12s %-8.2f %-6s %s",
                    no++, m.getNama(), m.getNim(), m.getNilai(), m.getGrade(), m.getStatus()))
                    .append(NL);

            total += m.getNilai();
            if (m.isLulus()) {
                lulus++;
            } else {
                tidakLulus++;
            }
        }

        double rataRata = total / daftar.size();

        sb.append(garis).append(NL);
        sb.append("STATISTIK KELAS").append(NL);
        sb.append("Jumlah mahasiswa      : ").append(daftar.size()).append(NL);
        sb.append(String.format(Locale.US, "Rata-rata nilai       : %.2f", rataRata)).append(NL);
        sb.append("Jumlah lulus          : ").append(lulus).append(NL);
        sb.append("Jumlah tidak lulus    : ").append(tidakLulus).append(NL);

        return sb.toString();
    }

    // ---------- File I/O ----------

    private static void simpanLaporan(String laporan) {
        // try-with-resources: writer otomatis ditutup, baik sukses maupun gagal
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(NAMA_FILE))) {
            writer.write(laporan);
            System.out.println("Laporan berhasil disimpan ke file " + NAMA_FILE);
        } catch (IOException e) {
            System.out.println("Gagal menyimpan file: " + e.getMessage());
        }
    }
}
