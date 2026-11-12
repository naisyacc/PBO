import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Locale;
import java.util.Scanner;

/**
 * Program pengelolaan nilai siswa (gaya procedural/fungsional).
 * 1. Membaca data siswa dari konsol.
 * 2. Menulis data + ringkasan ke laporan_nilai.txt.
 * 3. Membaca kembali file dan menampilkannya ke konsol.
 */
public class NilaiSiswa {

    static final String NAMA_FILE = "laporan_nilai.txt";
    static final double NILAI_LULUS = 70.0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int jumlahSiswa = bacaJumlahSiswa(scanner);

        // Langkah 1: input dari konsol & tulis ke file
        boolean berhasilTulis = tulisLaporan(scanner, jumlahSiswa);

        // Langkah 2: baca kembali file & tampilkan ke konsol
        if (berhasilTulis) {
            bacaLaporan();
        }

        scanner.close();
    }

    // ---------- Input helper ----------

    static int bacaJumlahSiswa(Scanner scanner) {
        while (true) {
            System.out.print("Masukkan jumlah siswa: ");
            try {
                int n = Integer.parseInt(scanner.nextLine().trim());
                if (n > 0) {
                    return n;
                }
                System.out.println("Jumlah siswa harus lebih dari 0.");
            } catch (NumberFormatException e) {
                System.out.println("Input harus berupa bilangan bulat.");
            }
        }
    }

    static double bacaNilai(Scanner scanner, int nomor) {
        while (true) {
            System.out.print("  Nilai siswa ke-" + nomor + " (0-100): ");
            try {
                double nilai = Double.parseDouble(scanner.nextLine().trim().replace(',', '.'));
                if (nilai >= 0 && nilai <= 100) {
                    return nilai;
                }
                System.out.println("  Nilai harus di antara 0 dan 100.");
            } catch (NumberFormatException e) {
                System.out.println("  Input harus berupa angka.");
            }
        }
    }

    static String tentukanStatus(double nilai) {
        return nilai >= NILAI_LULUS ? "Lulus" : "Tidak Lulus";
    }

    // ---------- Langkah 1: tulis file ----------

    static boolean tulisLaporan(Scanner scanner, int jumlahSiswa) {
        // try-with-resources: writer otomatis ditutup (BufferedWriter -> FileWriter)
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(NAMA_FILE))) {

            writer.write("=== LAPORAN NILAI SISWA ===");
            writer.newLine();
            writer.write(String.format("%-4s %-25s %-8s %s", "No", "Nama", "Nilai", "Status"));
            writer.newLine();
            writer.write("-".repeat(52));
            writer.newLine();

            double totalNilai = 0;
            int jumlahLulus = 0;
            int jumlahTidakLulus = 0;

            for (int i = 1; i <= jumlahSiswa; i++) {
                System.out.println("Data siswa ke-" + i);
                System.out.print("  Nama siswa: ");
                String nama = scanner.nextLine().trim();
                double nilai = bacaNilai(scanner, i);

                String status = tentukanStatus(nilai);
                if (nilai >= NILAI_LULUS) {
                    jumlahLulus++;
                } else {
                    jumlahTidakLulus++;
                }
                totalNilai += nilai;

                writer.write(String.format(Locale.US, "%-4d %-25s %-8.2f %s",
                        i, nama, nilai, status));
                writer.newLine();
            }

            double rataRata = totalNilai / jumlahSiswa;

            writer.write("-".repeat(52));
            writer.newLine();
            writer.write("RINGKASAN");
            writer.newLine();
            writer.write(String.format(Locale.US, "Rata-rata nilai kelas : %.2f", rataRata));
            writer.newLine();
            writer.write("Jumlah siswa lulus    : " + jumlahLulus);
            writer.newLine();
            writer.write("Jumlah tidak lulus    : " + jumlahTidakLulus);
            writer.newLine();

            System.out.println("\nData berhasil ditulis ke file " + NAMA_FILE);
            return true;

        } catch (IOException e) {
            System.out.println("Gagal menulis file: " + e.getMessage());
            return false;
        }
    }

    // ---------- Langkah 2: baca file ----------

    static void bacaLaporan() {
        System.out.println("\n===== ISI FILE " + NAMA_FILE + " =====");

        // try-with-resources: reader otomatis ditutup (BufferedReader -> FileReader)
        try (BufferedReader reader = new BufferedReader(new FileReader(NAMA_FILE))) {
            String baris;
            while ((baris = reader.readLine()) != null) {
                System.out.println(baris);
            }
        } catch (IOException e) {
            System.out.println("Gagal membaca file: " + e.getMessage());
        }
    }
}
